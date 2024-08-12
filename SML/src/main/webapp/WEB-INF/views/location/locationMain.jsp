<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>지도 검색</title>
    <link rel="stylesheet" href="../resources/css/location/location.css">
</head>
<body>
<!-- 헤더 영역 포함 -->
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<main>
    <div class="map_title">지도 검색</div> <!-- 지도 검색 제목 -->
    <div class="map_wrap">
        <div id="map" style="width:100%;height:100%;position:relative;overflow:hidden;"></div>
        <ul id="category">
            <li id="HP8" data-order="0">
                <span class="category_bg hospital"></span>
                병원
            </li>
            <li id="PO3" data-order="1">
                <span class="category_bg public_office"></span>
                공공기관
            </li>
            <li id="PM9" data-order="2">
                <span class="category_bg pharmacy"></span>
                약국
            </li>
            <li id="SW8" data-order="3">
                <span class="category_bg subway"></span>
                지하철
            </li>
        </ul>

        <div id="menu_wrap" class="bg_white">
            <div class="option">
                <div>
                    <form onsubmit="return searchPlacesByKeyword();">
                        키워드 : <input type="text" value="" id="keyword" size="15" onfocus="showRecentSearches()" autocomplete="off">
                        <button type="submit">검색하기</button>
                    </form>
                    <ul id="recentSearches"></ul>
                </div>
            </div>
            <hr>
            <ul id="placesList"></ul>
            <div id="pagination"></div>
        </div>

    </div>
    
                       <!-- 지도 사용법 버튼 추가 -->
        <div id="mapInstructionsButton">
		    <button onclick="openMapInstructions()">지도 사용법</button>
		</div>

</main>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=36ac063fd0533ae2bce9b847fb4f47fe&libraries=services"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
    var markers = [];
    var currCategory = '';
    var mapContainer = document.getElementById('map');
    var mapOption = {
        center: new kakao.maps.LatLng(37.566826, 126.9786567),
        level: 3
    };
    var map = new kakao.maps.Map(mapContainer, mapOption);
    var ps = new kakao.maps.services.Places(map);
    var infowindow = new kakao.maps.InfoWindow({zIndex: 1});
    var placeOverlay = new kakao.maps.CustomOverlay({zIndex: 1}),
        contentNode = document.createElement('div');

    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(function(position) {
            var lat = position.coords.latitude;
            var lon = position.coords.longitude;
            var locPosition = new kakao.maps.LatLng(lat, lon);
            map.setCenter(locPosition);
        });
    }

    contentNode.className = 'placeinfo_wrap';
    addEventHandle(contentNode, 'mousedown', kakao.maps.event.preventMap);
    addEventHandle(contentNode, 'touchstart', kakao.maps.event.preventMap);
    placeOverlay.setContent(contentNode);  

    function addEventHandle(target, type, callback) {
        if (target.addEventListener) {
            target.addEventListener(type, callback);
        } else {
            target.attachEvent('on' + type, callback);
        }
    }

    addCategoryClickEvent();

    function searchPlacesByKeyword() {
        var keyword = document.getElementById('keyword').value.trim();
        if (!keyword) {
            alert('키워드를 입력해주세요!');
            return false;
        }

        $.ajax({
            type: "GET",
            url: "/location/getRecentSearches",
            success: function(data) {
                var exists = data.some(function(item) {
                    return item.searchKeyword === keyword;
                });

                if (!exists) {
                    $.ajax({
                        type: "POST",
                        url: "/location/saveSearchKeyword",
                        contentType: "application/json",
                        data: JSON.stringify({searchKeyword: keyword}),
                        success: function() {
                            console.log("Search keyword saved.");
                            // 장소 검색 함수 호출
                            currCategory = '';
                            changeCategoryClass();
                            ps.keywordSearch(keyword, placesSearchCB);
                        }
                    });
                } else {
                    // 장소 검색 함수 호출
                    currCategory = '';
                    changeCategoryClass();
                    ps.keywordSearch(keyword, placesSearchCB);
                }
            }
        });
        return false; // 폼 제출을 막기 위해 false 반환
    }

    function searchPlaces() {
        if (!currCategory) return;
        placeOverlay.setMap(null);
        removeMarker();

        var center = map.getCenter();
        var options = {
            location: center,
            radius: 2000
        };

        ps.categorySearch(currCategory, placesSearchCB, options);
    }

    function placesSearchCB(data, status, pagination) {
        console.log('검색 상태:', status); // 디버깅용 로그
        console.log('검색 결과:', data); // 디버깅용 로그

        if (status === kakao.maps.services.Status.OK) {
            displayPlaces(data);
            displayPagination(pagination);
        } else if (status === kakao.maps.services.Status.ZERO_RESULT) {
            alert('검색 결과가 존재하지 않습니다.');
        } else if (status === kakao.maps.services.Status.ERROR) {
            alert('검색 결과 중 오류가 발생했습니다.');
        }
    }

    function displayPlaces(places) {
        var listEl = document.getElementById('placesList'),
            fragment = document.createDocumentFragment(),
            bounds = new kakao.maps.LatLngBounds();

        removeAllChildNods(listEl);
        removeMarker();

        for (var i = 0; i < places.length; i++) {
            var placePosition = new kakao.maps.LatLng(places[i].y, places[i].x),
                marker = addMarker(placePosition, i),
                itemEl = getListItem(i, places[i]);

            bounds.extend(placePosition);

            (function(marker, place) {
                kakao.maps.event.addListener(marker, 'click', function() {
                    displayPlaceInfo(place);
                });
            })(marker, places[i]);

            fragment.appendChild(itemEl);
        }

        listEl.appendChild(fragment);
        map.setBounds(bounds);
    }

    function getListItem(index, places) {
        var el = document.createElement('li'),
            itemStr = '<span class="markerbg marker_' + (index + 1) + '"></span>' +
                '<div class="info">' +
                '   <h5>' + places.place_name + '</h5>';

        if (places.road_address_name) {
            itemStr += '    <span>' + places.road_address_name + '</span>' +
                '   <span class="jibun gray">' + places.address_name + '</span>';
        } else {
            itemStr += '    <span>' + places.address_name + '</span>';
        }

        itemStr += '  <span class="tel">' + places.phone + '</span>' +
            '</div>';

        el.innerHTML = itemStr;
        el.className = 'item';
        return el;
    }

    function addMarker(position, idx) {
        var imageSrc = 'https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_number_blue.png',
            imageSize = new kakao.maps.Size(36, 37),
            imgOptions = {
                spriteSize: new kakao.maps.Size(36, 691),
                spriteOrigin: new kakao.maps.Point(0, (idx * 46) + 10),
                offset: new kakao.maps.Point(13, 37)
            },
            markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imgOptions),
            marker = new kakao.maps.Marker({
                position: position,
                image: markerImage
            });

        marker.setMap(map);
        markers.push(marker);
        return marker;
    }

    function removeMarker() {
        for (var i = 0; i < markers.length; i++) {
            markers[i].setMap(null);
        }
        markers = [];
    }

    function displayPlaceInfo(place) {
        var content = '<div class="placeinfo">' +
            '   <a class="title" href="' + place.place_url + '" target="_blank" title="' + place.place_name + '">' + place.place_name + '</a>';

        if (place.road_address_name) {
            content += '    <span title="' + place.road_address_name + '">' + place.road_address_name + '</span>' +
                '  <span class="jibun" title="' + place.address_name + '">(지번 : ' + place.address_name + ')</span>';
        } else {
            content += '    <span title="' + place.address_name + '">' + place.address_name + '</span>';
        }

        content += '    <span class="tel">' + place.phone + '</span>' +
            '</div>' +
            '<div class="after"></div>';

        contentNode.innerHTML = content;
        placeOverlay.setPosition(new kakao.maps.LatLng(place.y, place.x));
        placeOverlay.setMap(map);
    }

    function addCategoryClickEvent() {
        var category = document.getElementById('category'),
            children = category.children;

        for (var i = 0; i < children.length; i++) {
            children[i].onclick = onClickCategory;
        }
    }

    function onClickCategory() {
        var id = this.id,
            className = this.className;

        placeOverlay.setMap(null);

        if (className === 'on') {
            currCategory = '';
            changeCategoryClass();
            removeMarker();
        } else {
            currCategory = id;
            changeCategoryClass(this);
            searchPlaces();
        }
    }

    function changeCategoryClass(el) {
        var category = document.getElementById('category'),
            children = category.children,
            i;

        for (i = 0; i < children.length; i++) {
            children[i].className = '';
        }

        if (el) {
            el.className = 'on';
        }
    }

    function displayPagination(pagination) {
        var paginationEl = document.getElementById('pagination'),
            fragment = document.createDocumentFragment(),
            i;

        while (paginationEl.hasChildNodes()) {
            paginationEl.removeChild(paginationEl.lastChild);
        }

        for (i = 1; i <= pagination.last; i++) {
            var el = document.createElement('a');
            el.href = "#";
            el.innerHTML = i;

            if (i === pagination.current) {
                el.className = 'on';
            } else {
                el.onclick = (function(i) {
                    return function() {
                        pagination.gotoPage(i);
                    }
                })(i);
            }

            fragment.appendChild(el);
        }
        paginationEl.appendChild(fragment);
    }

    function removeAllChildNods(el) {
        while (el.hasChildNodes()) {
            el.removeChild(el.lastChild);
        }
    }

    function showRecentSearches() {
        $.ajax({
            type: "GET",
            url: "/location/getRecentSearches",
            success: function(data) {
                var recentSearches = document.getElementById('recentSearches');
                recentSearches.innerHTML = '';
                data.forEach(function(item) {
                    var li = document.createElement('li');
                    li.textContent = item.searchKeyword;
                    li.onclick = function() {
                        document.getElementById('keyword').value = item.searchKeyword;
                        recentSearches.style.display = 'none';
                    };
                    recentSearches.appendChild(li);
                });
                recentSearches.style.display = 'block';
            }
        });
    }

    document.addEventListener('click', function(event) {
        var recentSearches = document.getElementById('recentSearches');
        if (!recentSearches.contains(event.target) && event.target.id !== 'keyword') {
            recentSearches.style.display = 'none';
        }
    });

    kakao.maps.event.addListener(map, 'idle', function() {
        if (!currCategory) {
            return;
        }
        searchPlaces();
    });
    
    function openMapInstructions() {
        window.location.href = '/location/guide'; // 컨트롤러에 정의된 엔드포인트로 이동
    }

</script>
<!-- 푸터 영역 포함 -->
<%@ include file="/WEB-INF/views/common/footer.jsp" %>
</body>
</html>
