<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>
<head>
<title>수업 상세 페이지 - 취미 교실</title>
<link rel="stylesheet" href="${webappRoot}/resources/css/common/common.css">
<link rel="stylesheet" href="../resources/css/courseNcommunity/courseNcommunity.css">

<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
	<!-- 헤더 영역 포함 -->
	<jsp:include page="/WEB-INF/views/common/header.jsp" />

	<main>
		<div class="course_container">
			<jsp:include page="/WEB-INF/views/course/courseMenu.jsp"/>
			<div class="course_main_content">
			<h2>수업 상세 페이지</h2>
				<table class="course_table">
					<tr>
						<td>분류</td>
						<td>
							<input name="ccatName" readonly="readonly" value="<c:out value='${detail.ccatName}'/>">
							<!-- <span>대분류</span>
                			<select class="cate1" disabled>
                				<option value="none">선택</option>
                			</select>
                			<br>
                			<span>소분류</span>
                			<select class="cate2" name="ccatCode" disabled>
                				<option value="none">선택</option>
                			</select> -->
						</td>
					</tr>
					<tr>
						<td>강좌명</td>
						<td>
                    		<input name="courseName" readonly="readonly" value="<c:out value='${detail.courseName}'/>">
                		</td>
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
							<input type="date" readonly="readonly" value="<c:out value='${detail.startDate}'/>">
							~
							<input type="date" readonly="readonly" value="<c:out value='${detail.endDate}'/>">
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
						<td>수강 인원</td>
						<td>
							<span>0</span>
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
					<tr>
						<td>수업 설명</td>
						<td>
							<input class="input_block" name="courseContent" readonly="readonly" value="<c:out value='${detail.courseContent}'/>"/>
						</td>
					</tr>
				</table>
				<div class="btn_section">
					<c:choose>
            			<c:when test="${sessionScope.member.memStatus == 1}">
            				<button id="deleteBtn" class="btn">삭제</button>
                			<button id="listBtn" class="btn">목록</button>
                			<button id="modifyBtn" class="btn">수정</button>
            			</c:when>
            			<c:otherwise>
            				<button id="listBtn" class="btn">목록</button>
                			<button id="applyBtn" class="apply_btn">수강신청</button>
            			</c:otherwise>
        			</c:choose>
				</div>
			</div>
		</div>

	</main>

	<!-- 푸터 영역 포함 -->
	<%@ include file="/WEB-INF/views/common/footer.jsp"%>
	
	<script>
	$(document).ready(function(){
		/* 카테고리 */
		let cateList = JSON.parse('${cateList}');

		let cate1Array = new Array();
		let cate2Array = new Array();
		let cate1Obj = new Object();
		let cate2Obj = new Object();
		
		let cateSelect1 = $(".cate1");		
		let cateSelect2 = $(".cate2");
		
		/* 카테고리 배열 초기화 메서드 */
		function makeCateArray(obj,array,cateList, tier){
			for(let i = 0; i < cateList.length; i++){
				if(cateList[i].tier === tier){
					obj = new Object();
					
					obj.ccatName = cateList[i].ccatName;
					obj.ccatCode = cateList[i].ccatCode;
					obj.parentCode = cateList[i].parentCode;
					
					array.push(obj);					
				}
			}
		}		
		
		/* 배열 초기화 */
		makeCateArray(cate1Obj,cate1Array,cateList,1);
		makeCateArray(cate2Obj,cate2Array,cateList,2);	
		
		// 중분류
		for(let i = 0; i < cate2Array.length; i++){
			if(targetCate3.parentCode === cate2Array[i].ccatCode){
				targetCate2 = cate2Array[i];	
			}
		}
		for(let i = 0; i < cate2Array.length; i++){
			if(targetCate2.parentCode === cate2Array[i].parentCode){
				cateSelect2.append("<option value='"+cate2Array[i].ccatCode+"'>" + cate2Array[i].ccatName + "</option>");
			}
		}		
		
		$(".cate2 option").each(function(i,obj){
			if(targetCate2.ccatCode === obj.value){
				$(obj).attr("selected", "selected");
			}
		});	
		// 대분류
		for(let i = 0; i < cate1Array.length; i++){
			cateSelect1.append("<option value='"+cate1Array[i].ccatCode+"'>" + cate1Array[i].ccatName + "</option>");
		}
		$(".cate1 option").each(function(i,obj){
			if(targetCate2.parentCode === obj.value){
				$(obj).attr("selected", "selected");
			}
		});
	});
	
	/* 목록 이동 버튼 */
	var moveForm = $("#moveForm");
	$("#listBtn").on("click", function(e){
		e.preventDefault();
		$("input[name=courseCode]").remove();
		moveForm.attr("action", "/course/boardList");
		moveForm.submit();
	});	
	
	/* 수정 페이지 이동 */
	$("#modifyBtn").on("click", function(e){
		e.preventDefault();
		let addInput = '<input type="hidden" name="courseCode" value="${detail.courseCode}">';
		$("#moveForm").append(addInput);
		$("#moveForm").attr("action", "/course/modify");
		$("#moveForm").submit();
	});
	</script>
</body>
</html>
