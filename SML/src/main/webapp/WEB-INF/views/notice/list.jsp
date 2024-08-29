<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>공지사항 목록페이지</title>
    <link rel="stylesheet" href="${webappRoot}/resources/css/notice/main.css">
    <link rel="stylesheet" href="${webappRoot}/resources/css/common/common.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
    <jsp:include page="/WEB-INF/views/common/header.jsp" />
    <main>
        <div class="notice-container">
            <div class="notice-search">
                <h1>SML 소식!</h1>
                <p>다양한 소식을 전달해 드립니다</p>
                
                <form id="searchForm" action="/notice/list" method="get">
                    <div class="search-box">
                        <select name="type">
                            <option value="">전체</option>
                            <option value="T" ${pageMaker.cri.type eq 'T' ? 'selected' : ''}>제목</option>
                            <option value="C" ${pageMaker.cri.type eq 'C' ? 'selected' : ''}>내용</option>
                            <option value="TC" ${pageMaker.cri.type eq 'TC' ? 'selected' : ''}>제목+내용</option>
                        </select>
                        <input type="text" placeholder="검색어를 입력하세요" name="keyword" value='<c:out value="${pageMaker.cri.keyword}"/>'/>
                            
                        <input type="hidden" name="pageNum" value='<c:out value="${pageMaker.cri.pageNum}"/>'/>
                        <input type="hidden" name="amount" value='${pageMaker.cri.amount}'/>
                        <button type='button' class='btn search_btn'>검색하기</button>
                    </div>
            </div>
               <div class="category">
               <select name="categoryCode" id="categoryFilter">
                            <option value="">전체 카테고리</option>
                            <c:forEach var="category" items="${categories}">
                                <option value="${category.categoryCode}" ${pageMaker.cri.categoryCode eq category.categoryCode ? 'selected' : ''}>${category.categoryName}</option>
                            </c:forEach>
                        </select>
                        </div>
                    </form>
                
               

            <c:if test="${listCheck != 'empty'}">
                <table>
                    <thead>
                        <tr>
                            <th>번호</th>
                            <th>제목</th>
                            <th>등록일</th>
                            <th>조회</th>
                            <th>좋아요</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${list}" var="notice">
                            <tr>
                                <td><c:out value="${notice.noticeCode}" /></td>
                            
                                <td>
                                    <a class="move" href="<c:out value='${notice.noticeCode}'/>">
                                        <c:out value="${notice.noticeTitle}" />
                                    </a>
                                </td>
                                <td><fmt:formatDate value="${notice.noticeEnroll}" pattern="yyyy-MM-dd" /></td>
                                <td><c:out value="${notice.noticeCount}" /></td>
                                <td><c:out value="${notice.noticeLike}" /></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:if>
            
            <c:if test="${listCheck == 'empty'}">
                <div class="table_empty">
                    등록된 글이 없습니다.
                </div>
            </c:if>
                   
            <c:if test="${isLoggedIn && isAdmin}">
                <div class="button-container">
                    <button id="write">글쓰기</button>
                </div>
            </c:if>
        </div>
        
        <div class="pageMaker_wrap">
            <ul class="pageMaker">
                <c:if test="${pageMaker.prev}">
                    <li class="pageMaker_btn prev">
                        <a href="${pageMaker.pageStart - 1}">이전</a>
                    </li>
                </c:if>
                <c:forEach begin="${pageMaker.pageStart}" end="${pageMaker.pageEnd}" var="num">
                    <li class="pageMaker_btn ${pageMaker.cri.pageNum == num ? 'active':''}">
                        <a href="${num}">${num}</a>
                    </li>
                </c:forEach>
                <c:if test="${pageMaker.next}">
                    <li class="pageMaker_btn next">
                        <a href="${pageMaker.pageEnd + 1 }">다음</a>
                    </li>
                </c:if>
            </ul>         
        </div>
        
        <form id="moveForm" action="/notice/list" method="get">
            <input type="hidden" name="pageNum" value="${pageMaker.cri.pageNum}">
            <input type="hidden" name="amount" value="${pageMaker.cri.amount}">
            <input type="hidden" name="keyword" value="${pageMaker.cri.keyword}">
            <input type="hidden" name="type" value="${pageMaker.cri.type}">
            <input type="hidden" name="categoryCode" value="${pageMaker.cri.categoryCode}">
        </form>
    </main>
    
    <jsp:include page="/WEB-INF/views/common/footer.jsp" />

    <script>
        $(document).ready(function() {
            let searchForm = $('#searchForm');
            let moveForm = $('#moveForm');

            /* 글쓰기 버튼 */
            $("#write").click(function() {
                location.href = "/notice/enroll";
            });

            /* 검색 버튼 클릭 이벤트 */
            $(".search_btn").on("click", function(e) {
                e.preventDefault();
                
                let type = $("select[name='type']").val();
                let keyword = $("input[name='keyword']").val();
                let categoryCode = $("#categoryFilter").val();

                if(!type && !categoryCode){
                    alert("검색 종류를 선택하거나 카테고리를 선택하세요");
                    return false;
                }

                if(type && !keyword){
                    alert("키워드를 입력하세요");
                    return false;
                }

                searchForm.find("input[name='pageNum']").val("1");
                searchForm.submit();
            });

            /* 페이지 이동 버튼 */
            $(".pageMaker_btn a").on("click", function(e) {
                e.preventDefault();
                moveForm.find("input[name='pageNum']").val($(this).attr("href"));
                moveForm.submit();
            });

            /* 상세조회 페이지 이동 */
            $(".move").on("click", function(e) {
                e.preventDefault();    
                moveForm.append("<input type='hidden' name='noticeCode' value='" + $(this).attr("href") + "'>");
                moveForm.attr("action", "/notice/detail");
                moveForm.submit();    
            });

            /* 카테고리 필터 변경 이벤트 */
            $("#categoryFilter").change(function() {
                searchForm.find("input[name='pageNum']").val("1");
                searchForm.submit();
            });

            /* 알람 창 */
            let enroll = '<c:out value="${enroll_result}"/>';
            let modify = '<c:out value="${modify_result}"/>';
        
            checkResult(enroll);
            checkResult(modify);

            function checkResult(result) {
                if(result === '') {
                    return;
                }
                
                if(result === '1') {
                    alert("글 수정을 완료하였습니다.");
                } else if(result === '0') {
                    alert("글 수정을 실패하였습니다.");
                } else {
                    alert("글 '" + result + "' 을 등록하였습니다.");
                }
            }
        });
    </script>
</body>
</html>