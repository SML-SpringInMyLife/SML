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
			<h2>수강 신청</h2>
				<table class="course_table">
					<tr>
						<td>분류</td>
						<td>
							<select class="cate1" disabled>
								<option value="none">선택</option>
							</select>
							<span> > </span>
							<select class="cate2" disabled>
								<option value="none">선택</option>
							</select>
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
    						<input type="text" value="<fmt:formatDate value='${detail.startDate}' pattern='yyyy-MM-dd'/>" readonly="readonly"/>

    						~
    						<input type="text" value="<fmt:formatDate value='${detail.endDate}' pattern='yyyy-MM-dd'/>" readonly="readonly"/>
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
            			<c:when test="${sessionScope.member.memAdminCheck == 1}">
            				<button id="deleteBtn" class="btn">삭제</button>
                			<button id="listBtn" class="btn">목록</button>
                			<button id="modifyBtn" class="btn modify_btn">수정</button>
            			</c:when>
            			<c:otherwise>
            				<button id="listBtn" class="btn">목록</button>
                			<button id="applyBtn" class="apply_btn">수강신청</button>
            			</c:otherwise>
        			</c:choose>
				</div>
			</div>
		</div>
		<form id="moveForm" method="get">
               	<input type="hidden" name="courseCode" value='<c:out value="${detail.courseCode}"/>'>
               	<input type="hidden" name="pageNum" value='<c:out value="${cri.pageNum }"/>'>
               	<input type="hidden" name="amount" value='<c:out value="${cri.amount }"/>' >
               	<input type="hidden" name="keyword" value='<c:out value="${cri.keyword }"/>'>
		</form>
	</main>

	<!-- 푸터 영역 포함 -->
	<%@ include file="/WEB-INF/views/common/footer.jsp"%>
	
	<script>
	
	/* 목록 이동 버튼 */
	var moveForm = $("#moveForm");
	$("#listBtn").on("click", function(e){
		alert("목록 페이지 이동");
		e.preventDefault();
		$("input[name=courseCode]").remove();
		moveForm.attr("action", "/course/boardList");
		moveForm.submit();
	});	
	
	/* 수정 페이지 이동 */
	$("#modifyBtn").on("click", function(e){
		alert("수정 페이지 이동");
		e.preventDefault();
		
		moveForm.attr("action", "/course/modify");
		moveForm.submit();
	});
	
	$('.apply_btn').on("click",function(e){	
		e.preventDefault();
		
		var courseCode = $(this).attr("href");

	    // 팝업 옵션
	    var popUrl = "/course/apply?courseCode=" + courseCode;
	    var popOption = "width=650, height=550, top=300, left=300, scrollbars=yes";
		
		window.open(popUrl,"수강신청",popOption);
	});
	
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
		
		// 대분류
		for (let i = 0; i < cate1Array.length; i++) {
    		cateSelect1.append("<option value='" + cate1Array[i].cateCode + "'>" + cate1Array[i].cateName + "</option>");
		}

		$(".cate1 option").each(function (i, obj) {
    		if (targetCate2.cateParent === obj.value) {
        	$(obj).attr("selected", "selected");
    		}
		});
		
		let targetCate2 = '${detail.ccatCode}';
		
		// 중분류
		for(let i = 0; i < cate2Array.length; i++){
			if(targetCate2.parentCode === cate2Array[i].ccatCode){
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
		
	});
	</script>
</body>
</html>
