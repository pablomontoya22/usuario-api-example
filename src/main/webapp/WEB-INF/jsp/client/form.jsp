<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<div id="client-form">
	<form>
		<div class="form-row">
			<div class="form-group col-6">
				<label for="name"><spring:message code="client.name"/></label>
				<input type="text" class="form-control" id="name"
					placeholder="<spring:message code="client.name"/>">
			</div>
			<div class="form-group col-6">
				<label for="surname"><spring:message code="client.surname"/></label>
				<input type="text" class="form-control" id="surname"
					placeholder="<spring:message code="client.surname"/>">
			</div>
		</div>
		<div class="form-row">
			<div class="form-group col-6">
				<label for="dni"><spring:message code="client.DNI"/></label>
				<input type="text" class="form-control" id="dni"
					placeholder="<spring:message code="client.DNI"/>">
			</div>
			<div class="form-group col-6">
				<label for="email"><spring:message code="client.email"/></label>
				<input type="email" class="form-control" id="email"
					placeholder="<spring:message code="client.email"/>">
			</div>
		</div>
	</form>
</div>