<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Donation List</title>
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
        }
        table, th, td {
            border: 1px solid black;
        }
        th, td {
            padding: 10px;
            text-align: center;
        }
        th {
            background-color: #f2f2f2;
        }
    </style>
</head>
<body>
    <h1>Donation List</h1>
    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Merchant UID</th>
                <th>Imp UID</th>
                <th>Amount</th>
                <th>Buyer Name</th>
                <th>Buyer Email</th>
                <th>Buyer Tel</th>
                <th>Registration Date</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="donation" items="${donations}">
                <tr>
                    <td>${donation.id}</td>
                    <td>${donation.merchantUid}</td>
                    <td>${donation.impUid}</td>
                    <td>${donation.amount}</td>
                    <td>${donation.buyerName}</td>
                    <td>${donation.buyerEmail}</td>
                    <td>${donation.buyerTel}</td>
                    <td>${donation.regDate}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
