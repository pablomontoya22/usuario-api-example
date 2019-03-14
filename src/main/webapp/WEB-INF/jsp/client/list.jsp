<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:main>
	<jsp:body>
        <h1>
	        <spring:message code="index.menu.client"/>
	        <button id="add-client" class="btn btn-primary">
        		<spring:message code="client.add"/>
        	</button>
        </h1>
        <table id="clients">
        	<thead>
	        	<tr>
	        		<th><spring:message code="client.name"/></th>
	        		<th><spring:message code="client.surname"/></th>
	        		<th><spring:message code="client.DNI"/></th>
	        		<th><spring:message code="client.email"/></th>
	        		<th><spring:message code="client.actions"/></th>
	       		</tr>
       		</thead>
       		<tbody>
	       		<c:choose>
		        	<c:when test="${empty clients}">
	       		<tr>
	       			<td colspan="4"><spring:message code="client.emptyClients"/></td>
	       		</tr>
		        	</c:when>
		        	<c:otherwise>
		        		<c:forEach var="client" items="${clients}">
	      		<tr id="${client.id}">
	      			<td>${client.name}</td>
	      			<td>${client.surname}</td>
	      			<td>${client.DNI}</td>
	      			<td>${client.email}</td>
	      			<td>
	      				<span class="show"
	      				title="<spring:message code="common.show"/>"></span>
	      				<span class="edit"
	      				title="<spring:message code="common.edit"/>"></span>
	      				<span class="delete"
	      				title="<spring:message code="common.delete"/>"></span>
      				</td>
	      		</tr>
		        		</c:forEach>
		        	</c:otherwise>
	        	</c:choose>
        	</tbody>
        </table>
        <jsp:include page="form.jsp"/>
    </jsp:body>
</t:main>