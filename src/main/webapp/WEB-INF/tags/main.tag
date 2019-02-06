<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8"/>
		<c:url value="/css/main.css" var="jstlCss"/>
		<link href="${jstlCss}" rel="stylesheet"/>
		<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
		<link rel="stylesheet"
		href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"/>
		<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"></script>
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"></script>
		<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
	  	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
		<c:url value="/js/main.js" var="jstlJs"/>
		<script defer src="${jstlJs}"></script>
	</head>
	<body>
		<nav class="navbar navbar-expand-lg navbar-light bg-light">
			<a class="navbar-brand" href="/">
				<spring:message code="header.appTitle"/>
			</a>
		</nav>
		<nav id="sidebar" class="sidebar-wrapper">
			<div id="sidebar-menu">
				<a><spring:message code="index.menu.client"/></a>
				<ul>
					<li>
						<a href="/client"><spring:message code="index.menu.client.admin"/></a>
					</li>
				</ul>
				<a><spring:message code="index.menu.client.reports"/></a>
			</div>
		</nav>
		<div id="main">
			<jsp:doBody/>
		</div>
	</body>
</html>