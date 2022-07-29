<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- jsp:paramの利用：ShowItemServlet?action=topと同様の効果 -->
<jsp:forward page="ShowItemServlet">
	<jsp:param name="action" value="top" /> 
</jsp:forward>
