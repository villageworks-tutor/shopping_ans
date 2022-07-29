<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
	<meta charset="UTF-8">
	<title>Welcome shopping!</title>
	<link rel="stylesheet" href="assets/css/style.css">
</head>
<body>

<!-- ヘッダ表示領域 -->
<jsp:include page="parts/header.jsp" />

<!-- メインコンテンツ表示領域 -->
<main>
	<article id="cart">
		<h2>現在のカートの中身</h2>
		<c:choose>
		<c:when test="${empty sessionScope.cart.items}">
		<p>現在、カートは空です。</p>
		</c:when>
		<c:otherwise>
		<table border="1">
			<tr>
				<th>商品番号</th>
				<th>商品名</th>
				<th>単価（税込）</th>
				<th>個数</th>
				<th>小計</th>
				<th>削除</th>
			</tr>
			<c:forEach items="${sessionScope.cart.items}" var="item">
			<tr>
				<td>${item.code}</td>
				<td>${item.name}</td>
				<td>${item.price}円</td>
				<td>${item.quantity}個</td>
				<td>${item.price * item.quantity}円</td>
				<td>
					<form action="CartServlet" method="post">
						<button type="submit" name="action" value="delete">削除</button>
						<input type="hidden" name="code" value="${item.code}" />
					</form>
				</td>
			</tr>
			</c:forEach>
			<tr>
				<td colspan="6" class="right-align">総計：${sessionScope.cart.totalPrice}円</td>
			</tr>
			<tr>
				<td colspan="6" class="button">
					<form name="order">
						<button formaction="OrderServlet" formmethod="post" name="action" value="entry">注文する</button>
					</form>
				</td>
			</tr>
		</table>
		</c:otherwise>
		</c:choose>
	</article>
</main>

<!-- フッタ表示領域 -->
<jsp:include page="parts/footer.jsp" />

</body>
</html>