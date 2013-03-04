
<%@ page import="org.isima.Versioning" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'versioning.label', default: 'Versioning')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-versioning" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-versioning" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="content" title="${message(code: 'versioning.content.label', default: 'Content')}" />
					
						<th><g:message code="versioning.interactionContent.label" default="Interaction Content" /></th>
					
						<g:sortableColumn property="modificationDate" title="${message(code: 'versioning.modificationDate.label', default: 'Modification Date')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${versioningInstanceList}" status="i" var="versioningInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${versioningInstance.id}">${fieldValue(bean: versioningInstance, field: "content")}</g:link></td>
					
						<td>${fieldValue(bean: versioningInstance, field: "interactionContent")}</td>
					
						<td>${fieldValue(bean: versioningInstance, field: "modificationDate")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${versioningInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
