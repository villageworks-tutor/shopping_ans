<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav id="menu">
	<ol>
		<%-- 【トップページへのリンク】ジャンプ先にコンテキストパスを指定：コンテキストパスが違ってもエラーとはならない --%>
		<li><a href="<%= getServletContext().getContextPath() %>">ようこそ</a></li>
		
		<%-- 【商品カテゴリーリンク】アプリケーションスコープのcategoriesキーの値を反復表示 --%>
		<c:forEach items="${applicationScope.categories}" var="category">
		<li><a href="./list.html">${category.name}</a></li>
		</c:forEach>
		
		<li><a href="./cart.html">カートを見る</a></li>
	</ol>
</nav>
