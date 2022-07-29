<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
	<meta charset="UTF-8">
	<title>Sample Shopping Site</title>
	<link rel="stylesheet" href="assets/css/style.css">
</head>
<body>
<!-- ヘッダー表示領域 -->
<jsp:include page="parts/header.jsp" /><%-- 絶対パスによる指定 --%>

<!-- コンテンツ表示領域 -->
<main>
	<article id="top">
		<h3>ようこそ！サンプルショッピングサイトへ</h3>
		<p>このサイトは教材用として作成されています。<br />デザインなどは各自工夫してみましょう。</p>
	</article>
</main>

<!-- フッター表示領域 -->
<jsp:include page="/pages/parts/footer.jsp" /><%-- 相対パスによる指定 --%>

</body>
</html>