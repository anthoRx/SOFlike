
<%@ page import="org.isima.Comment" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'comment.label', default: 'Comment')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-comment" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		

		<div id="show-comment" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list comment">
			
				<g:if test="${commentInstance?.content}">
				<li class="fieldcontain">
					<span id="content-label" class="property-label"><g:message code="comment.content.label" default="Content" /></span>
					
						<span class="property-value" aria-labelledby="content-label"><g:fieldValue bean="${commentInstance}" field="content"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${commentInstance?.creationDate}">
				<li class="fieldcontain">
					<span id="creationDate-label" class="property-label"><g:message code="comment.creationDate.label" default="Creation Date" /></span>
					
						<span class="property-value" aria-labelledby="creationDate-label">						
							<g:formatDate format="MMM ''dd 'at' k:mm" date="${commentInstance?.creationDate}" locale="EN_en"/>
						</span>
					
				</li>
				</g:if>
			
				<g:if test="${commentInstance?.interactionContent}">
				<li class="fieldcontain">
					<span id="interactionContent-label" class="property-label"><g:message code="comment.interactionContent.label" default="Interaction Content" /></span>
					
						<span class="property-value" aria-labelledby="interactionContent-label">
							<g:link controller="interactionContent" action="show" id="${commentInstance?.interactionContent?.id}">${commentInstance?.interactionContent?.content.decodeHTML()}</g:link>
						</span>
					
				</li>
				</g:if>
			
			</ol>
			<sec:ifAllGranted roles="ROLE_ADMIN">
				<g:form>
					<fieldset class="buttons">
						<g:hiddenField name="id" value="${commentInstance?.id}" />
						<g:link class="edit" action="edit" id="${commentInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
						<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
					</fieldset>
				</g:form>
			</sec:ifAllGranted>
		</div>
	</body>
</html>
