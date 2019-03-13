<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:main>
	<jsp:body>
        <h1><spring:message code="index.menu.client"/></h1>
        <table id="clients">
        	<thead>
	        	<tr>
	        		<th><spring:message code="client.list.name"/></th>
	        		<th><spring:message code="client.list.surname"/></th>
	        		<th><spring:message code="client.list.DNI"/></th>
	        		<th><spring:message code="client.list.email"/></th>
	        		<th><spring:message code="client.list.actions"/></th>
	       		</tr>
       		</thead>
       		<tbody>
	       		<c:choose>
		        	<c:when test="${empty clients}">
	       		<tr>
	       			<td colspan="4"><spring:message code="client.list.emptyClients"/></td>
	       		</tr>
		        	</c:when>
		        	<c:otherwise>
		        		<c:forEach var="client" items="${clients}">
	      		<tr>
	      			<td>${client.name}</td>
	      			<td>${client.surname}</td>
	      			<td>${client.DNI}</td>
	      			<td>${client.email}</td>
	      			<td>
	      				<span class="show"></span>
	      				<span class="edit"></span>
	      				<span class="delete"></span>
      				</td>
	      		</tr>
		        		</c:forEach>
		        	</c:otherwise>
	        	</c:choose>
        	</tbody>
        </table>
    </jsp:body>
</t:main>