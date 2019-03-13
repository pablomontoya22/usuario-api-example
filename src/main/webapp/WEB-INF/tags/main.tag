<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8"/>
		<link rel="shortcut icon" type="image/x-icon" href="/images/car.png" />
		<c:url value="/css/main.css" var="jstlCss"/>
		<link href="${jstlCss}" rel="stylesheet"/>
		<link href="/css/jquery-ui.min.css" rel="stylesheet">
		<link href="/css/bootstrap.min.css" rel="stylesheet"/>
		<link href="/css/jquery.dataTables.min.css" rel="stylesheet"/>
		<script src="/js/jquery-3.3.1.min.js"></script>
		<script src="/js/jquery-ui.min.js"></script>
		<script src="/js/popper.min.js"></script>
		<script src="/js/bootstrap.min.js"></script>
		<script src="/js/jquery.dataTables.min.js"></script>
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