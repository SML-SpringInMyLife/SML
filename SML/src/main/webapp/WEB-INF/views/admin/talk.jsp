<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Header</title>

<!-- 공통 스타일 시트 -->
<link rel="stylesheet"
	href="${webappRoot}/resources/css/common/common.css">
<!-- 채팅 스타일 시트 -->
<link rel="stylesheet" type="text/css"
	href="${webappRoot}/resources/css/talk.css">
<!-- 아이콘 -->
<link rel='stylesheet'
	href='https://cdn-uicons.flaticon.com/2.5.1/uicons-bold-rounded/css/uicons-bold-rounded.css'>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript">
	function play() {
		var audio = document.getElementById('audio_play');
		if (audio.paused) {
			audio.play();
		} else {
			audio.pause();
			audio.currentTime = 0;
		}
	}
</script>
</head>
<body>
	<!-- 헤더 영역 포함 -->
	<jsp:include page="/WEB-INF/views/common/header.jsp" />

	<!-- 해당 페이지의 메인내용을 여기에 작성하세요. -->
	<main>

		<!-- 채팅 아이콘 -->
		<div class="chatIcon font_jua">
			<img src="${webappRoot}/resources/images/chat-icon.png"
				class="iconImg" alt="Chat Icon">
		</div>

		<!-- 채팅창 -->
		<div class="chatContainer display-none">
			<div class="chatTop">
				<div class="floatLeft" id="loginOn">
					<img class="profile_img" id="setPic" alt="Profile Picture">
				</div>
				<div class="name_container font_noto" id="setName"></div>
				<div class="floatRight">
					<img src="${webappRoot}/resources/img/chat-close.png"
						class="btnImg close" alt="Close">
				</div>
				<div class="floatRight">
					<img src="${webappRoot}/resources/img/chat-minus.png"
						class="btnImg down" alt="Minimize">
				</div>
			</div>
			<div class="chatMiddle">
				<ul></ul>
			</div>
			<div class="chatBottom">
				<textarea placeholder="메세지를 입력해 주세요."></textarea>
			</div>
		</div>

		<!-- 포맷용 채팅 목록 -->
		<div class="chatMiddle format">
			<ul>
				<li>
					<div class="sender">
						<span></span>
					</div>
					<div class="message">
						<span></span>
					</div>
				</li>
			</ul>
		</div>

		<!-- 채팅 리스트 -->
		<div class="chatListContainer font_jua display-none">
			<div class="chatTop">
				<div style="padding: 10px; margin-left: 4px;">상담 리스트</div>
			</div>
			<div class="chatList"></div>
		</div>

		<!-- 채팅 팝업 설정 -->
		<div id="chat-container" class="hidden">
			<div id="chat-header">
				<span><< 채팅 상담 >></span>
				<button onclick="minimizeChat()">➖</button>
				<!-- 최소화 버튼 -->
				<button onclick="closeChat()">❌</button>
			</div>
			<div id="chat-box"></div>
			<div id="chat-input">
				<input type="text" id="message-input" placeholder="메시지를 입력하세요.">
				<button onclick="sendMessage()">전송</button>
			</div>
		</div>

		<div id="close-chat-modal" class="modal hidden">
			<div class="modal-content">
				<p>채팅(상담)을 종료하시겠습니까?</p>
				<button onclick="confirmCloseChat()">종료</button>
				<button onclick="cancelCloseChat()">취소</button>
			</div>
		</div>
	</main>
	<!-- 자바스크립트 코드 -->
	  <script>
        // 채팅 아이콘 클릭 시 채팅창 표시/숨기기
        $(document).on("click", ".chatIcon", function() {
            if ($('.chatContainer').hasClass("display-none")) {
                $('.chatListContainer').toggleClass('display-none');
            } else {
                $('.chatContainer').toggleClass('display-none');
                websocket.close();
            }
            if (!$('.chatListContainer').hasClass('display-none')) {
                getRoomList();
            }
        });

        // 채팅창 닫기 버튼 클릭 시 채팅창 숨기기
        $(document).on("click", "img.close", function() {
            $('.chatContainer').toggleClass('display-none');
            websocket.close();
        });

        // 채팅창 최소화 버튼 클릭 시 채팅창과 리스트 숨기기
        $(document).on("click", "img.down", function() {
            $('.chatContainer').toggleClass('display-none');
            $('.chatListContainer').toggleClass('display-none');
            websocket.close();
        });
        <%-- 
        // 전체 읽지 않은 메시지 수
        let countAll = 0;

        // 채팅방 리스트 가져오기
        function getRoomList() {
            $.ajax({
                url: "chatRoomList.do",
                data: { userEmail: "${sessionScope.member.memEmail}" },
                dataType: "json",
                async: false,
                success: function(data) {
                    let loginList = "";

                    $.ajax({
                        url: "chatSession.do",
                        data: {},
                        async: false,
                        dataType: "json",
                        success: function(data) {
                            for (var i = 0; i < data.length; i++) {
                                loginList += data[i];
                            }
                        }
                    });

                    $chatWrap = $(".chatList");
                    $chatWrap.html("");

                    if (data.length > 0) {
                        countAll = 0;
                        for (var i in data) {
                            let $div, $img, $divs, $span;

                            if (data[i].userEmail == "${sessionScope.member.memEmail}") {
                                if (loginList.indexOf(data[i].masterEmail) != -1) {
                                    $div = $("<div class='chatList_box enterRoomList'>").attr("id", data[i].roomId).attr("email", data[i].masterEmail);
                                } else {
                                    $div = $("<div class='chatList_box2 enterRoomList'>").attr("id", data[i].roomId).attr("email", data[i].masterEmail);
                                }
                                $img = $("<img class='profile_img'>").attr("src", "${webappRoot}/resources/masterImg/" + data[i].masterPic);
                                $divs = $("<div class='userNameId'>").text(data[i].masterName);
                            } else {
                                if (loginList.indexOf(data[i].userEmail) != -1) {
                                    $div = $("<div class='chatList_box enterRoomList'>").attr("id", data[i].roomId).attr("email", data[i].userEmail);
                                } else {
                                    $div = $("<div class='chatList_box2 enterRoomList'>").attr("id", data[i].roomId).attr("email", data[i].userEmail);
                                }
                                $img = $("<img class='profile_img'>").attr("src", "${webappRoot}/resources/img/" + data[i].userPic);
                                $divs = $("<div class='userNameId'>").text(data[i].userName);
                            }

                            if (data[i].unReadCount != 0) {
                                $span = $("<span class='notRead'>").text(data[i].unReadCount);
                            } else {
                                $span = $("<span>");
                            }

                            $div.append($img);
                            $div.append($divs);
                            $div.append($span);

                            $chatWrap.append($div);

                            countAll += parseInt(data[i].unReadCount);
                        }
                    }
                }
            });
        }

        // 페이지 로드 시 채팅 리스트 주기적으로 업데이트
        $(window).on('load', function() {
            setInterval(function() {
                getRoomList();
                if (countAll != 0) {
                    $('.chatIcon').addClass('iconBlink');
                    play();
                } else {
                    $('.chatIcon').removeClass('iconBlink');
                }
            }, 2000);
        });

        // 엔터키로 메시지 전송
        $(document).on('keydown', 'div.chatBottom textarea', function(e) {
            if (e.keyCode == 13 && !e.shiftKey) {
                e.preventDefault();
                const message = $(this).val();

                if (message.replace(/\s|  /gi, "").length == 0) {
                    return false;
                }

                sendMessage(message);
                clearTextarea();
            }
        });

        function enterRoom(obj) {
            $('div.chatMiddle:not(.format) ul').html("");
            roomId = obj.getAttribute("id");

            $.ajax({
                url: roomId + ".do",
                data: { userEmail: "${sessionScope.member.memEmail}" },
                async: false,
                dataType: "json",
                success: function(data) {
                    for (var i = 0; i < data.length; i++) {
                        CheckLR(data[i]);
                    }
                }
            });

            connect();
        }

        $(document).on("click", ".enterRoomList", function() {
            $(".chatContainer").toggleClass("display-none");
            $(this).parent().parent().toggleClass("display-none");
            $("#setName").html($(this).children('div').html());
            $("#setPic").attr("src", $(this).children('img').attr('src'));
            $('div.chatMiddle').scrollTop($('div.chatMiddle').prop('scrollHeight'));

            if ($(this).hasClass('chatList_box')) {
                $('#loginOn').addClass('profile_img_Container');
            } else {
                $('#loginOn').removeClass('profile_img_Container');
            }
        });

        let websocket;

        function connect() {
            var wsUri = "ws://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/websocket/echo.do";
            websocket = new WebSocket(wsUri);
            websocket.onopen = onOpen;
            websocket.onmessage = onMessage;
        }

        function onOpen() {
            const data = {
                "roomId": roomId,
                "name": "${sessionScope.member.memName}",
                "email": "${sessionScope.member.memEmail}",
                "message": "ENTER-CHAT"
            };
            let jsonData = JSON.stringify(data);
            websocket.send(jsonData);
        }

        function sendMessage(message) {
            const data = {
                "roomId": roomId,
                "name": "${sessionScope.member.memName}",
                "email": "${sessionScope.member.memEmail}",
                "message": message
            };

            CheckLR(data);

            let jsonData = JSON.stringify(data);
            websocket.send(jsonData);
        }

        function onMessage(evt) {
            let receive = evt.data.split(",");

            const data = {
                "name": receive[0],
                "email": receive[1],
                "message": receive[2]
            };

            if (data.email != "${sessionScope.member.memEmail}") {
                CheckLR(data);
            }
        }

        function CheckLR(data) {
            const LR = (data.email != "${sessionScope.member.memEmail}") ? "left" : "right";
            appendMessageTag(LR, data.email, data.message, data.name);
        }

        function appendMessageTag(LR_className, email, message, name) {
            const chatLi = createMessageTag(LR_className, email, message, name);
            $('div.chatMiddle:not(.format) ul').append(chatLi);
            $('div.chatMiddle').scrollTop($('div.chatMiddle').prop('scrollHeight'));
        }

        function createMessageTag(LR_className, email, message, name) {
            let chatLi = $('div.chatMiddle.format ul li').clone();
            chatLi.addClass(LR_className);
            chatLi.find('.sender span').text(name);
            chatLi.find('.message span').text(message);
            return chatLi;
        }

        function clearTextarea() {
            $('div.chatBottom textarea').val('');
            return false;
        }--%>
    </script>
    <audio id='audio_play' src='${webappRoot}/resources/new-message.mp3'></audio>
    <script src="${webappRoot}/resources/js/common.js"></script> 
</body>
</html>
