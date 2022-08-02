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
	<article id="entry">
		<section id="cart">
			<h3>ご注文商品</h3>
			<table border="1">
				<tr>
					<th>商品番号</th>
					<th>商品名</th>
					<th>単価（税込）</th>
					<th>個数</th>
					<th>小計</th>
				</tr>
				<c:forEach items="${sessionScope.cart.items}" var="item">
				<tr>
					<td>${item.code}</td>
					<td>${item.name}</td>
					<td>${item.price}円</td>
					<td>${item.quantity}</td>
					<td>${item.price * item.quantity}円</td>
				</tr>
				</c:forEach>
				<tr>
					<td colspan="6" class="right-align">総計：${sessionScope.cart.totalPrice}円</td>
				</tr>
			</table>
		</section>
		<section id="customer">
			<h3>お客様情報を入力してください。</h3>
			<form name="entry">
				<table border="1">
					<tr>
						<th>お名前</th>
						<td>
							<input type="text" name="name" value="石川五エ門" />
						</td>
					</tr>
					<tr>
						<th>住所</th>
						<td>
							<input type="text" name="address" value="東京都中央区" />
						</td>
					</tr>
					<tr>
						<th>電話番号</th>
						<td>
							<input type="text" name="tel" value="03-3333-2222" />
						</td>
					</tr>
					<tr>
						<th>e-mail</th>
						<td>
							<input type="text" name="email" value="ichiro@abc.com" />
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<button formaction="OrderServlet" formmethod="post" name="action" value="confirm">確認画面へ</button>
						</td>
					</tr>
				</table>
			</form>
		</section>
	</article>
</main>

<!-- フッタ表示領域 -->
<jsp:include page="parts/footer.jsp" />

</body>
</html>