<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
	<meta charset="UTF-8">
	<title>Input Error</title>
	<link rel="stylesheet" href="assets/css/style.css">
</head>
<body>

<!-- ヘッダ表示領域 -->
<jsp:include page="parts/header.jsp" />

<main>
	<article id="error">
		<section id="message">
			<!-- セッションがない場合：セッションが切れていると判断 -->
			<h3>セッションが切れています。もう一度トップページから操作して下さい。</h3>
			<!-- カートがない場合、actionキーが不正な場合：不正な操作と判断 -->
			<h3>正しい操作をして下さい。</h3>
			<!-- DAO例外が発生した場合：内部エラー（想定外のエラー）と判断 -->
			<h3>内部エラーが発生しました。</h3>
		</section>
	</article>
</main>

<!-- フッタ表示領域 -->
<jsp:include page="/pages/parts/footer.jsp" />

</body>
</html>