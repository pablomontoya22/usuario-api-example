<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<nav id="sidebar" class="sidebar-wrapper">
	<div id="sidebar-menu">
		<a><spring:message code="welcome.menu.client"/></a>
		<ul>
			<li><spring:message code="welcome.menu.client.admin"/></li>
		</ul>
		<a><spring:message code="welcome.menu.client.reports"/></a>
	</div>
</nav>
