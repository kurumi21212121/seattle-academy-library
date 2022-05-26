<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<%@ page contentType="text/html; charset=utf8"%>
<%@ page import="java.util.*"%>
<html>
<head>
<title>ホーム｜シアトルライブラリ｜シアトルコンサルティング株式会社</title>
<link href="<c:url value="/resources/css/reset.css" />" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Noto+Sans+JP" rel="stylesheet">
<link href="<c:url value="/resources/css/default.css" />" rel="stylesheet" type="text/css">
<link href="https://use.fontawesome.com/releases/v5.6.1/css/all.css" rel="stylesheet">
<link href="<c:url value="/resources/css/home.css" />" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
</head>
<body class="wrapper">
    <header>
        <div class="left">
            <img class="mark" src="resources/img/logo.png" />
            <div class="logo">Seattle Library</div>
        </div>
        <div class="right">
            <ul>
                <li><a href="<%=request.getContextPath()%>/home" class="menu">Home</a></li>
                <li><a href="<%=request.getContextPath()%>/">ログアウト</a></li>
            </ul>
        </div>
    </header>
    <main>
       <div class="container">
            <h5>貸出履歴一覧</h5>
           
            <table class="table table-bordered">
            
              <thead>
                    <tr class="table-primary">
                        <th>書籍名</th>
                        <th>貸出日</th>
                        <th>返却日</th>
                    </tr>
                </thead>
                <c:forEach var="DateBookinfo" items="${historybook}">
                    <tbody>
                        <tr>
                            <td><form method="post" class="book_title" action="<%=request.getContextPath()%>/details">
                                    <a href="javascript:void(0)" onclick="this.parentNode.submit();">${DateBookinfo.title}</a> <input type="hidden" name="bookId" value="${DateBookinfo.id}">
                                </form></td>
                            <td>${DateBookinfo.rent_date}</td>
                            <td>${DateBookinfo.return_date}</td>
                        </tr>
                    </tbody>
                </c:forEach>
            </table>
         
        </div>
    </main>
</body>
</html>