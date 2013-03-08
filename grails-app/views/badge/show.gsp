
<%@ page import="org.isima.Badge" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'badge.label', default: 'Badge')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-badge" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		
		<div id="show-badge" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list badge">
			
				<g:if test="${badgeInstance?.name}">
				<li class="fieldcontain">
					<span id="name-label" class="property-label"><g:message code="badge.name.label" default="Name" /></span>
					
						<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${badgeInstance}" field="name"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${badgeInstance?.pointsToObtain}">
				<li class="fieldcontain">
					<span id="pointsToObtain-label" class="property-label"><g:message code="badge.pointsToObtain.label" default="Points To Obtain" /></span>
					
						<span class="property-value" aria-labelledby="pointsToObtain-label"><g:fieldValue bean="${badgeInstance}" field="pointsToObtain"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${badgeInstance?.users}">
				<li class="fieldcontain">
					<span id="users-label" class="property-label"><g:message code="badge.users.label" default="Users" /></span>
					
						<g:each in="${badgeInstance.users}" var="u">
						<span class="property-value" aria-labelledby="users-label"><g:link controller="user" action="show" id="${u.id}">${u?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${badgeInstance?.id}" />
					<g:link class="edit" action="edit" id="${badgeInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
