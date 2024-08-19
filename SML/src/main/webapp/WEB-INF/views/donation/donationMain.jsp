<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Donation Main</title>
    <style>
        #donationButtonContainer {
            margin: 20px 0;
            text-align: center; /* 버튼들을 가운데 정렬 */
        }
        #donationButtonContainer button {
            background-color: #4CAF50;
            border: none;
            color: white;
            padding: 10px 20px;
            font-size: 16px;
            border-radius: 5px;
            cursor: pointer;
            box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.2);
            transition: background-color 0.3s ease;
            margin: 0 10px; /* 버튼 간의 간격 추가 */
        }
        #donationButtonContainer button:hover {
            background-color: #45a049;
        }
        #donationImage {
            cursor: pointer;
            display: block;
            margin: 20px auto;
            max-width: 400px;
            height: auto;
        }
        #encouragementMessage {
            text-align: center;
            font-size: 18px;
            margin-top: 20px;
            color: #333;
        }
        #noticeSection {
            text-align: center;
            font-size: 16px;
            margin-top: 20px;
            color: #555;
            line-height: 1.6;
        }
    </style>
</head>
<body>
<%@ include file="/WEB-INF/views/common/header.jsp" %>

<main>
    <h1 style="text-align: center;">SML</h1>
    <div style="text-align: center;">
        <img id="additionalImage" src="/resources/images/location/sml1.png" alt="SML Support Image"/>
    </div>
    <div id="encouragementMessage">
        "SML은 노인을 위한 따뜻한 봄을 만들어갑니다. 여러분의 후원은 인생의 봄을 선물합니다."
    </div>
    
    <div id="noticeSection">
        <h3>후원 유의사항</h3>
        <p>1. 후원은 최대 1만원 까지 가능합니다.</p>
        <p>2. 후원금은 노인을 위한 교육 지원금으로 사용되며 강좌는 취미교실 게시판에서 확인할 수 있습니다.</p>
        <p>3. 1만원 이상의 후원 또는 다른 문의가 있을시 아래 후원문의 게시판에 글을 등록해주세요.</p>
    </div>

    <div id="donationButtonContainer">
        <button onclick="requestPay()">후원하기</button>
        <button onclick="location.href='/donation/list'">후원문의</button>
    </div>
</main>

<!-- jQuery -->
<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<!-- iamport.payment.js -->
<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.2.0.js"></script>
<script>
    var IMP = window.IMP;
    IMP.init("imp37506434"); // 여기에 가맹점 식별코드를 넣으세요

    function requestPay() {
        IMP.request_pay({
            pg: "kakaopay", // 카카오페이 사용
            pay_method: "card", // 결제 수단
            merchant_uid: "ORD20240819-0000011", // 주문번호
            name: "SML 후원", // 상품명
            amount: 10000, // 결제 금액
            buyer_email: "sml@gmail.com",
            buyer_name: "sml1",
            buyer_tel: "010-1234-2345",
            buyer_addr: "서울특별시 강남구 신사동",
            buyer_postcode: "01181"
        }, function (rsp) { // 콜백 함수
            if (rsp.success) {
                $.ajax({
                    type: 'POST',
                    url: '/donation/verify/' + rsp.imp_uid
                }).done(function (data) {
                    if (rsp.paid_amount === data.response.amount) {
                        alert("결제 성공");
                    } else {
                        alert("결제 금액 불일치");
                    }
                }).fail(function () {
                    alert("결제 검증 실패");
                });
            } else {
                alert("결제에 실패하였습니다: " + rsp.error_msg);
            }
        });
    }
</script>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>
</body>
</html>
