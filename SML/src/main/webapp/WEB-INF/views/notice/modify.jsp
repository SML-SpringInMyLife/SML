<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 수정페이지</title>

<!-- common.css 로드 -->
<link rel="stylesheet"
	href="${webappRoot}/resources/css/common/common.css">

<!-- enroll.css 로드 -->
<link rel="stylesheet"
	href="${webappRoot}/resources/css/notice/enroll.css">

<!-- jQuery 로드 -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
 <style type="text/css">
#result_card img{
		max-width: 100%;
	    height: auto;
	    display: block;
	    padding: 5px;
	    margin-top: 10px;
	    margin: auto;	
	}
	#result_card {
		position: relative;
	}
	.imgDeleteBtn{
	    position: absolute;
	    top: 0;
	    right: 5%;
	    background-color: #ef7d7d;
	    color: wheat;
	    font-weight: 900;
	    width: 30px;
	    height: 30px;
	    border-radius: 50%;
	    line-height: 26px;
	    text-align: center;
	    border: none;
	    display: block;
	    cursor: pointer;	
	}
</style>
</head>
<body>

	<!-- 헤더 영역 포함 -->
	<jsp:include page="/WEB-INF/views/common/header.jsp" />

	<main>
		<form action="/notice/modify" method="post" id="modifyForm">
<input type="hidden" name="noticeCode"
						value="<c:out value='${noticeDetail.noticeCode}'/>">
<input type="hidden" name="memCode" value="${noticeDetail.memCode}" />						
						
	
						
			<div class="enroll-container">
				<div class="category">
					<select>
						<option>☰ 게시글카테고리</option>
						<option>수강안내</option>
						<option>전체공지</option>
						<option>이벤트</option>
					</select>
				</div>
				<div class="title">
					<span id="alram_title">제목을 입력해 주세요</span> <input name="noticeTitle"
						type="text" size="30" class="titletext"
						value=<c:out value="${noticeDetail.noticeTitle}" />>
				</div>

				<div class="content">
					<span id="alram_body">내용을 입력해 주세요</span>
					<textarea name="noticeBody" class="bodytext">
          <c:out value="${noticeDetail.noticeBody}" />
          </textarea>

					<div class="form_section">
                    			<div class="form_section_title">
                    				<label>상품 이미지</label>
                    			</div>
                    			<div class="form_section_content">
									<input type="file" id ="fileItem" name='uploadFile' style="height: 30px;">
									<div id="uploadResult">
																		
									</div>									
                    			</div>
                    		</div>

					<input type="hidden" name="noticeCode"
						value="<c:out value='${noticeDetail.noticeCode}'/>">
				</div>
				<div class="actions">
					<button id="modifybtn" class="update">수정</button>
					<button id="cancelbtn" class="cancel">취소</button>
				</div>
			</div>
		</form>


	</main>
	<!-- 푸터 영역 포함 -->
	<jsp:include page="/WEB-INF/views/common/footer.jsp" />

	<script>
	
	 /* 기존이미지 출력  */
	let noticeCode = '<c:out value="${noticeDetail.noticeCode}"/>';
	let uploadResult = $("#uploadResult");			
	
	$.getJSON("/notice/getAttachList", {noticeCode : noticeCode}, function(arr){	
		
		if(arr.length === 0){			
			return;
		}
		
		let str = "";
		let obj = arr[0];	
		
		let fileCallPath = encodeURIComponent(obj.filePath + "/s_" + obj.fileUuid + "_" + obj.fileName);
		str += "<div id='result_card'";
		str += "data-path='" + obj.filePath + "' data-uuid='" + obj.fileUuid + "' data-filename='" + obj.fileName + "'";
		str += ">";
		str += "<img src='/notice/display?fileName=" + fileCallPath +"'>";
		str += "<div class='imgDeleteBtn' data-file='" + fileCallPath + "'>x</div>";
		str += "<input type='hidden' name='imageList[0].fileName' value='"+ obj.fileName +"'>";
		str += "<input type='hidden' name='imageList[0].uuid' value='"+ obj.fileUuid +"'>";
		str += "<input type='hidden' name='imageList[0].uploadPath' value='"+ obj.filePath +"'>";
		str += "</div>";		
		
		uploadResult.html(str);						
		
	});
	
	/* 이미지 업로드 */
	$("input[type='file']").on("change", function(e) {

		/* 이미지 존재시 삭제 */
		if($("#result_card").length > 0){
			deleteFile();
		}
		
		let formData = new FormData();
		let fileInput = $('input[name="uploadFile"]');
		let fileList = fileInput[0].files;
		let fileObj = fileList[0];

		if (!fileCheck(fileObj.name, fileObj.size)) {
			return false;
		}

		for (let i = 0; i < fileList.length; i++) {
			formData.append("uploadFile", fileList[i]);
		} // 여러개의 파일을 선택할 수 있게 함

		//서버로 첨부파일 전송
		$.ajax({
			url : '/notice/uploadAjaxAction',
			processData : false,
			contentType : false,
			data : formData,
			type : 'POST',
			dataType : 'json',
			success : function(result) {
				console.log(result);
				showUploadImage(result);
			},
			error : function(result) {
				alert("이미지 파일이 아닙니다.");
			}
		});

	});

	/* 파일종류, 크기 제한 */
	let regex = new RegExp("(.*?)\.(jpg|png)$");
	let maxSize = 1048576; //1MB	

	function fileCheck(fileName, fileSize) {

		if (fileSize >= maxSize) {
			alert("파일 사이즈 초과");
			return false;
		}

		if (!regex.test(fileName)) {
			alert("해당 종류의 파일은 업로드할 수 없습니다.");
			return false;
		}

		return true;

	}
	
	/* 이미지 출력 */
	function showUploadImage(uploadResultArr){
	
		/* 전달받은 데이터 검증 */
		if(!uploadResultArr || uploadResultArr.length == 0){return}
		
		let uploadResult = $("#uploadResult");
		
		let obj = uploadResultArr[0];
		
		let str = "";
		
		let fileCallPath = obj.filePath.replace(/\\/g, '/') + "/s_" + obj.fileUuid + "_" + obj.fileName;
		
		str += "<div id='result_card'>";
		str += "<img src='/notice/display?fileName=" + fileCallPath +"'>";
		str += "<div class='imgDeleteBtn' data-file='" + fileCallPath + "'>x</div>";
		str += "<input type='hidden' name='imageList[0].fileName' value='"+ obj.fileName +"'>";
		str += "<input type='hidden' name='imageList[0].fileUuid' value='"+ obj.fileUuid +"'>";
		str += "<input type='hidden' name='imageList[0].filePath' value='"+ obj.filePath +"'>";		
		str += "</div>";		
		
   		uploadResult.append(str);
	}
 
	
	/* 이미지 삭제 버튼 동작 */
	$("#uploadResult").on("click", ".imgDeleteBtn", function(e){
		deleteFile();	
	});
	 /* 파일 삭제 메서드 */
    function deleteFile(){
        let targetFile = $(".imgDeleteBtn").data("file");
        let targetDiv = $("#result_card");
        
        $.ajax({
            url: '/notice/deleteFile',
            data: {fileName : targetFile},
            dataType: 'text',
            type: 'POST',
            success: function(result){
                console.log(result);
                if(result === "success"){
                    targetDiv.remove();
                    $("input[type='file']").val("");
                    // 이미지 관련 hidden input 제거
                    $("input[name^='imageList']").remove();
                } else {
                    alert("파일 삭제 실패");
                }
            },
            error: function(jqXHR, textStatus, errorThrown){
                console.log(textStatus);
                alert("파일을 삭제하는 데 실패했습니다.");
            }
        });
    }
	
	 
		let moveForm = $("#modifyForm");

		/* 글 수정버튼, 유효성 검사 */
		$("#modifybtn").on("click", function(e) {

			let noticeTitle = $(".title input[name='noticeTitle']").val();
			let noticeBody = $(".content textarea[name='noticeBody']").val();

			let TitleCk = false;
			let BodyCk = false;

			e.preventDefault();

			if (!noticeTitle) {
				$("#alram_title").css("display", "block");
			} else {
				$("#alram_title").css("display", "none");
				TitleCk = true;
			}
			if (!noticeBody) {
				$("#alram_body").css("display", "block");
			} else {
				$("#alram_body").css("display", "none");
				BodyCk = true;
			}

			if (TitleCk && BodyCk) {
				modifyForm.submit();
			} else {
				return false;
			}

		});

		 /* 공지사항 취소버튼 페이지 이동 */
	    $("#cancelbtn").on("click", function(e) {
	        e.preventDefault();
	        
	        // noticeCode를 hidden input으로 추가
	        let noticeCode = $("input[name='noticeCode']").val();
	        moveForm.empty(); // 기존 폼 내용을 비웁니다.
	        moveForm.append("<input type='hidden' name='noticeCode' value='" + noticeCode + "'>");
	        
	        moveForm.attr("method", "GET");
	        moveForm.attr("action", "/notice/detail");
	        moveForm.submit();
	    });

	</script>


</body>
</html>
