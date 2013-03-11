
<%@ page import="org.isima.Versioning" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'versioning.label', default: 'Versioning')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-versioning" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>

		<div id="show-versioning" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list versioning">
			
				<g:if test="${versioningInstance?.content}">
				<li class="fieldcontain">
					<span id="content-label" class="property-label"><g:message code="versioning.content.label" default="Content" /></span>
					
						<span class="property-value" aria-labelledby="content-label">${versioningInstance.content.decodeHTML()}</span>
					
				</li>
				</g:if>
			
				<g:if test="${versioningInstance?.modificationDate}">
				<li class="fieldcontain">
					<span id="modificationDate-label" class="property-label"><g:message code="versioning.modificationDate.label" default="Modification Date" /></span>
					
						<span class="property-value" aria-labelledby="modificationDate-label">
							<g:formatDate format="MMM ''dd 'at' k:mm" date="${versioningInstance?.modificationDate}" locale="EN_en"/>
						</span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${versioningInstance?.id}" />
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
