<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/product/productList.css" />
<title>Insert title here</title>
</head>
<body>
	<!-- header추가 -->
	<%@ include file="/WEB-INF/views/header/header.jsp"%>

	<!-- productList 시작  -->
	<div class="productlist-wrap">
		<div class="products">
			<div class="keyword-wrap">
				<div class="keyword">
					<span>${keyword }</span>
					<p>
						<span>검색결과</span> 상품 <em id="totalCnt">${ products.size() }</em> 개
					</p>
				</div>
			</div>
			<div class="product-list">
				<c:forEach items="${products}" var="p">
					<a href="#" class="product"> 
						<img src="${p.productImgurl}" width="256px" height="256px">
						<div class="product-price">${p.productPrice}</div>
						<div class="product-name">${p.productName}</div>
					</a>
				</c:forEach>
			</div>

			<!-- <a href="#" class="product"> <img
				src="https://image.thehyundai.com/static/9/0/3/47/A0/40A0473095_0_480.jpg"
				width="256" height="256px">
				<div class="product-price">40,000원</div>
				<div class="product-name">[나스] 벨벳 매트 립 펜슬</div> -->

		</div>
	</div>

	<script
		src="${pageContext.request.contextPath}/resources/js/product/productList.js"></script>
</body>
</html>