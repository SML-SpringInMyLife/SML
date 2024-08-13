<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 등록페이지</title>

<!-- common.css 로드 -->
<link rel="stylesheet" href="${webappRoot}/resources/css/common/common.css">

<!-- enroll.css 로드 -->
<link rel="stylesheet" href="${webappRoot}/resources/css/notice/enroll.css">

<!-- jQuery 로드 -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>

<!-- 헤더 영역 포함 -->
<jsp:include page="/WEB-INF/views/common/header.jsp" />

<main>
    <div class="enroll-container">
        <form action="/notice/enroll.do" method="post" id="enrollForm">
        <input type="hidden" name="memCode" value="${sessionScope.member.memCode}">
            <div class="category">
                <select>
                    <option>☰ 게시글카테고리</option>
                    <option>수강안내</option>
                    <option>전체공지</option>
                    <option>이벤트</option>
                </select>
            </div>
            
            <div class="title"> <!-- 제목 -->
                <span id="alram_title" style="display:none;">제목을 입력해 주세요</span>
                <input name="noticeTitle" type="text" size="30" class="titletext">
            </div>
            <div class="content"> 
                <span id="alram_body" style="display:none;">내용을 입력해 주세요</span>
                <textarea name="noticeBody" class="bodytext"></textarea>  <!-- 글내용 -->
                <div class="file-upload">
                    <input name="fileCode" type="file">
                </div>
            </div>
            <div class="actions">
                <button id="enrollbtn" class="update">등록</button>
                <button id="cancelbtn" class="cancel">취소</button>
            </div>
        </form>
    </div>
    <div class="pagination">
        <button class="page-btn active">1</button>
        <button class="page-btn">2</button>
        <button class="page-btn">3</button>
    </div>
</main>

<!-- 푸터 영역 포함 -->
<jsp:include page="/WEB-INF/views/common/footer.jsp" />

<script>
$(document).ready(function() {
    /* 등록 버튼 유효성검사 */
    $("#enrollbtn").click(function(event){	
        event.preventDefault(); // 기본 제출 동작을 막음

        /* 검사 통과 유무 변수 */
        let titleCheck = false;			// 제목
        let bodyCheck = false;		// 본문 내용

        /* 입력값 변수 */
        let noticeTitle = $('input[name=noticeTitle]').val();		// 제목
        let noticeBody = $('textarea[name=noticeBody]').val();		// 본문내용

        /* 공란 경고 span태그 */
        let altitle = $('#alram_title');
        let albody = $('#alram_body');

        /* 제목 공란 체크 */
        if(noticeTitle === ''){
            altitle.css('display', 'block');
            titleCheck = false;
        } else {
            altitle.css('display', 'none');
            titleCheck = true;
        }

        /* 본문내용 공란 체크 */
        if(noticeBody === ''){
            albody.css('display', 'block');
            bodyCheck = false;
        } else {
            albody.css('display', 'none');
            bodyCheck = true;
        }

        /* 최종 검사 */
        if(titleCheck && bodyCheck) {
            $("#enrollForm").submit();
        } else {
            return;
        }
    });

    /* 취소 버튼 */
    $("#cancelbtn").click(function() {
        location.href = "/notice/list";
    });
});
</script>
</body>
</html>
