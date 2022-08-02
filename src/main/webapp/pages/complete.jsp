<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
	<meta charset="UTF-8">
	<title>商品一覧</title>
	<link rel="stylesheet" href="assets/css/style.css">
</head>
<body>

<!-- ヘッダ表示領域 -->
<jsp:include page="parts/header.jsp" />

<!-- メインコンテンツ表示領域 -->
<main>
	<article id="complete">
		<h3 class="message">ご注文ありがとうございました。</h3>
		<p>お客様の注文番号は <em>${requestScope.orderNumber}</em> になります。</p>
	</article>
</main>

<!-- フッタ表示領域 -->
<jsp:include page="parts/footer.jsp" />

</body>
</html>