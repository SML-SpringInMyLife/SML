<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>수강 신청</title>
<link rel="stylesheet" href="${webappRoot}/resources/css/common/common.css">
<link rel="stylesheet" href="../resources/css/courseNcommunity/courseNcommunity.css">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>

	<jsp:include page="/WEB-INF/views/common/header.jsp" />

	<main>
		<div>
		<h2>수강신청</h2>
			<table class="course_content">
				<tr>
					<td>신청한 과목</td>
					<td><input name="courseName" readonly="readonly"
						value="<c:out value='${detail.courseName}'/>"></td>
				</tr>
				<tr>
					<td>강사명</td>
					<td>
						<input name="teaName" readonly="readonly" value="<c:out value='${detail.teaName}'/>">
					</td>
				</tr>
				<tr>
					<td>수강 기간</td>
					<td>
						<fmt:formatDate value="${detail.startDate}" pattern="yyyy-MM-dd"/> 
						~ 
						<fmt:formatDate value="${detail.endDate}" pattern="yyyy-MM-dd"/>
					</td>
				</tr>
				<tr>
					<td>수업 시간/요일</td>
					<td>
						<input type="time" readonly="readonly" value="<c:out value='${detail.startTime}'/>"> 
						~ 
						<input type="time" readonly="readonly" value="<c:out value='${detail.endTime}'/>"> 
						<br> 
						<input type="text" readonly="readonly" value="<c:out value='${detail.courseDay}'/>">
					</td>
				</tr>
				<tr>
					<td>현재 수강 신청 인원</td>
					<td>
						<span>현재 수강 인원</span>
						/
						<input name="courseLimit" readonly="readonly" value="<c:out value='${detail.courseLimit}'/>">
						명
					</td>
				</tr>
				<tr>
					<td>차감 포인트</td>
					<td>
						<input name="course" readonly="readonly" value="<c:out value='${detail.coursePoint}'/>">
					</td>
				</tr>
			</table>
			<div class="btn_section">
				<button id="cancelBtn" class="btn">취소</button>
				<button id="applyBtn" class="btn">신청</button>
			</div>
		</div>
	</main>

	<%@ include file="/WEB-INF/views/common/footer.jsp"%>
	
	<script>
	$(document).ready(function() {
	    $("#applyBtn").on("click", function() {
	        var form = $("#applyForm");
	        var formData = form.serialize();

	        $.ajax({
	            url: form.attr('action'),
	            type: 'POST',
	            data: formData,
	            success: function(response) {
	                if (confirm("수강신청이 완료되었습니다. 확인을 클릭하면 회원 수강 목록으로 이동합니다.")) {
	                    window.location.href = "/member/courses";
	                }
	            },
	            error: function(xhr, status, error) {
	                alert("신청 중 오류가 발생했습니다: " + error);
	            }
	        });
	    });

	    $("#cancelBtn").on("click", function() {
	        window.close();
	    });
	});
	</script>

</body>
</html>