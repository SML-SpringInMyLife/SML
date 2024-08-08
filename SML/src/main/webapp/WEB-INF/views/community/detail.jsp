<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>
<head>
<title>게시글 상세 페이지</title>
<link rel="stylesheet" href="${webappRoot}/resources/css/common/common.css">
<link rel="stylesheet" href="../resources/css/community/community.css">

<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script	src="https://cdn.ckeditor.com/ckeditor5/41.4.2/classic/ckeditor.js"></script>
<style>
        /* 게시글 상세 내용 스타일 */
        .community-container {
            flex: 1;
            padding: 20px;
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }

        .community-boardDetail-wrap {
            margin-top: 20px;
        }

        .community_boardDetail {
            width: 100%;
            border-collapse: collapse;
        }

        .community_boardDetail td {
            padding: 12px;
            border: 1px solid #ddd;
            vertical-align: top;
        }

        .community_boardDetail .label {
            font-weight: bold;
            width: 150px; /* 레이블 열의 폭을 설정합니다. */
            background-color: #f2f2f2;
        }

        .community_boardDetail input.input_block {
            width: 100%;
            padding: 10px;
            font-size: 16px;
            border: 1px solid #ddd;
            border-radius: 4px;
            background-color: #fff;
            box-sizing: border-box;
        }

        .community_boardDetail small {
            display: block;
            font-size: 14px; /* 수정일의 폰트 크기 */
            color: #666; /* 수정일의 색상 */
            margin-top: 5px; /* 수정일과 다른 내용 간의 간격 */
        }

        .btn_section {
            margin-top: 20px;
            text-align: right;
        }

        .btn {
            padding: 10px 20px;
            font-size: 16px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            margin-left: 10px;
            background-color: #007bff;
            color: white;
        }

        .btn:hover {
            background-color: #0056b3;
        }

        .btn:disabled {
            background-color: #cccccc;
            cursor: not-allowed;
        }

        /* 게시글 내용 크게 */
        .community_boardDetail .content {
            font-size: 16px;
            height: 200px; /* 원하는 높이로 조정 */
            overflow-y: auto; /* 내용이 넘칠 경우 스크롤 추가 */
        }
    </style>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
	<!-- 헤더 영역 포함 -->
	<jsp:include page="/WEB-INF/views/common/header.jsp" />

	<main>
		<h1>게시글 상세 페이지</h1>
		<div class="community-container">
			<jsp:include page="/WEB-INF/views/community/communityMenu.jsp"/>
			<div class="community-boardDetail-wrap">
				<table class="community_boardDetail">
					<tr>
						<td>글 제목</td>
						<td>
							<input class="input_block" name="commTitle" readonly="readonly" value="<c:out value='${communityDetail.commTitle}'/>"/>
						</td>
					</tr>
					<tr>
						<td>작성자</td>
						<td>
							<input class="input_block" name="commWriter" readonly="readonly" value="<c:out value='${communityDetail.commWriter}'/>"/>
						</td>
					</tr>
					<tr>
						<td>등록일</td>
						<td>
							<fmt:formatDate value="${communityDetail.enrollDate}" pattern="yyyy-MM-dd" var="formattedEnrollDate"/>
							<input class="input_block" name="enrollDate" readonly="readonly" value="<c:out value='${formattedEnrollDate}'/>"/>
							<br>
							<c:if test="${not empty communityDetail.modifyDate}">
                                <fmt:formatDate value="${communityDetail.modifyDate}" pattern="yyyy-MM-dd" var="formattedModifyDate"/>
                                <small class="small-text">마지막 수정일 : <c:out value="${formattedModifyDate}"/></small>
                            </c:if>
						</td>
						<td>조회수</td>
						<td>
							<input class="input_block" name="commCount" readonly="readonly" value="<c:out value='${communityDetail.commCount}'/>"/>
						</td>
					</tr>
					<tr>
						<td>게시글 내용</td>
						<td>
							<input class="input_block" name="commContent" readonly="readonly" value="<c:out value='${communityDetail.commContent}'/>"/>
						</td>
					</tr>
				</table>
				<div class="btn_section">
					<button id="deleteBtn" class="btn">삭제</button>
					<button id="BoardListBtn" class="btn">목록</button>
					<button id="modifyBtn" class="btn">수정</button>
				</div>
			</div>
			<div class="community-reply">
				<h3>댓글</h3>
				<form id="reply">
					<textarea id="replyContent" name="content" rows="4" cols="50" placeholder="댓글을 입력하세요..."></textarea>
        			<button type="button" id="submitReply" class="btn">댓글 등록</button>
    			</form>
   					<div id="replyList">
        				<!-- 댓글 목록이 여기에 표시됩니다 -->
    				</div>
			</div>
		
		<form id="moveForm" method="get">
        	<input type="hidden" name="commCode" value='<c:out value="${communityDetail.commCode }"/>'>
            <input type="hidden" name="pageNum" value='<c:out value="${cri.pageNum }"/>'>
            <input type="hidden" name="amount" value='<c:out value="${cri.amount }"/>' >
            <input type="hidden" name="keyword" value='<c:out value="${cri.keyword }"/>'>
        </form>
	</main>

	<!-- 푸터 영역 포함 -->
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
	
	<script>
		let moveForm = $("#moveForm");
		
		// delete 버튼
		$("deleteBtn").on("click", function(e){
			alert("삭제 버튼");
			/* e.preventDefault();
			moveForm.find("input").remove();
			moveForm.append('<input type="hidden" name="commCode" value="${communityDetail.commCode}">');
			moveForm.attr("action", "/community/delete");
			moveForm.attr("method", "post");
			moveForm.submit(); */
		});

		// 목록으로 이동 버튼
		$("#BoardListBtn").on("click", function(e){
			alert("없어져라!");
			e.preventDefault();
		
			$("input[name=commCode]").remove();
			moveForm.attr("action", "/community/boardList")
			moveForm.submit();
		});
	
		// 수정 페이지 이동 버튼 
		$("#modifyBtn").on("click", function(e){
			e.preventDefault();
			moveForm.attr("action", "/community/modify");
			moveForm.submit();
		});
		$(document).ready(function() {
	        // 댓글 등록 버튼 클릭 이벤트
	        $("#submitReply").on("click", function(e) {
	            e.preventDefault();
	            
	            // 댓글 내용
	            let replyContent = $("#replyContent").val();
	            
	            // 댓글 내용이 비어있지 않은지 확인
	            if ($.trim(replyContent) === "") {
	                alert("댓글 내용을 입력하세요.");
	                return;
	            }
	            
	            $.ajax({
	                url: '/reply/enroll',
	                type: 'POST',
	                contentType: 'application/json; charset=utf-8',
	                data: JSON.stringify({
	                    commCode: $('input[name="commCode"]').val(),
	                    content: replyContent
	                }),
	                success: function(response) {
	                    // 댓글이 성공적으로 등록되면 댓글 목록을 업데이트
	                    loadReplies();
	                },
	                error: function(xhr, status, error) {
	                    alert("댓글 등록 실패: " + error);
	                }
	            });
	        });

	        // 댓글 목록을 로드하는 함수
	        function loadReplies() {
	            $.ajax({
	                url: '/reply/list',
	                type: 'GET',
	                data: { commCode: $('input[name="commCode"]').val() },
	                success: function(response) {
	                    let replyList = $("#replyList");
	                    replyList.empty();
	                    
	                    // 댓글 목록을 동적으로 생성
	                    $.each(response.replies, function(index, reply) {
	                        replyList.append(`
	                            <div class="reply-item">
	                                <p>${reply.content}</p>
	                                <small>작성일: ${reply.date}</small>
	                            </div>
	                        `);
	                    });
	                },
	                error: function(xhr, status, error) {
	                    alert("댓글 목록 로드 실패: " + error);
	                }
	            });
	        }

	        // 페이지 로드 시 댓글 목록을 로드
	        loadReplies();
	    });
		
	</script>
</body>
</html>
