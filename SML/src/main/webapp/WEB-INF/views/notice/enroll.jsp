<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 등록페이지</title>

<!-- common.css 로드 -->
<link rel="stylesheet"
	href="${webappRoot}/resources/css/common/common.css">

<!-- enroll.css 로드 -->
<link rel="stylesheet"
	href="${webappRoot}/resources/css/notice/enroll.css">

<!-- jQuery 로드 -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>

	<!-- 헤더 영역 포함 -->
	<jsp:include page="/WEB-INF/views/common/header.jsp" />

	<main>
		<div class="enroll-container">
			<form action="/notice/enroll.do" method="post" id="enrollForm">
				<input type="hidden" name="memCode"
					value="${sessionScope.member.memCode}">
				
			 <div class="category">
                    <label for="categoryCode">☰ 카테고리:</label>
                    <select id="categoryCode" name="categoryCode">
                        <option value="">카테고리 선택 (선택사항)</option>
                        <c:forEach var="category" items="${categories}">
                            <option value="${category.categoryCode}">${category.categoryName}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="new-category">
                    <label for="newCategory">새 카테고리:</label>
                    <input type="text" id="newCategory" name="newCategory" placeholder="새 카테고리 입력 (선택사항)">
                </div>
              
				<div class="title">
					<!-- 제목 -->
					<span id="alram_title" style="display: none;">제목을 입력해 주세요</span> <input
						name="noticeTitle" type="text" size="30" class="titletext">
				</div>
				<div class="content">
					<span id="alram_body" style="display: none;">내용을 입력해 주세요</span>
					<textarea name="noticeBody" class="bodytext"></textarea>
					<!-- 글내용 -->

					<div class="form_section">
						<div class="form_section_title">
							<label>사진선택하기</label>
						</div>
						<div class="form_section_content">
							<input type="file" multiple id="fileItem" name='uploadFile'
								style="height: 30px;">
							<div id="uploadResult">
							<div id="result_card">
									<div class="imgDeleteBtn">x</div>
								</div> 
							</div>

						</div>
					</div>

				</div>
				<div class="actions">
					<button id="enrollbtn" class="update">등록</button>
					<button id="cancelbtn" class="cancel">취소</button>
				</div>
			</form>
		</div>

	</main>

	<!-- 푸터 영역 포함 -->
	<jsp:include page="/WEB-INF/views/common/footer.jsp" />

	<script>
		$(document).ready(function() {
			/* 등록 버튼 유효성검사 */
			$("#enrollbtn").click(function(event) {
				event.preventDefault(); // 기본 제출 동작을 막음

				/* 검사 통과 유무 변수 */
				let titleCheck = false; // 제목
				let bodyCheck = false; // 본문 내용

				/* 입력값 변수 */
				let noticeTitle = $('input[name=noticeTitle]').val(); // 제목
				let noticeBody = $('textarea[name=noticeBody]').val(); // 본문내용

				/* 공란 경고 span태그 */
				let altitle = $('#alram_title');
				let albody = $('#alram_body');

				/* 제목 공란 체크 */
				if (noticeTitle === '') {
					altitle.css('display', 'block');
					titleCheck = false;
				} else {
					altitle.css('display', 'none');
					titleCheck = true;
				}

				/* 본문내용 공란 체크 */
				if (noticeBody === '') {
					albody.css('display', 'block');
					bodyCheck = false;
				} else {
					albody.css('display', 'none');
					bodyCheck = true;
				}

				/* 최종 검사 */
				if (titleCheck && bodyCheck) {
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

		
		/* 이미지 업로드 */
		$("input[type='file']").on("change", function(e) {

			/* 이미지 존재시 삭제 */
			if($(".imgDeleteBtn").length > 0){
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
		
		function deleteFile(){
		    let targetFile = $(".imgDeleteBtn").data("file");		
		    let targetDiv = $("#result_card");

		    console.log("Attempting to delete file:", targetFile);
		    
		    $.ajax({
		        url: '/notice/deleteFile',
		        data: { fileName: encodeURIComponent(targetFile) },    // 변경: data를 객체로 직접 전달
		        type: 'POST',
		        success: function(result){
		            console.log(result);
		            targetDiv.remove();
		            $("input[type='file']").val("");
		        },
		        error: function(xhr, status, error){
		            console.log(xhr.responseText);
		            alert("파일을 삭제하지 못하였습니다.");
		        }
		    });
		}
	</script>
</body>
</html>
