
<%@ page import="org.isima.Badge" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'badge.label', default: 'Badge')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-badge" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
	
		<div id="list-badge" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="name" title="${message(code: 'badge.name.label', default: 'Name')}" />
					
						<g:sortableColumn property="pointsToObtain" title="${message(code: 'badge.pointsToObtain.label', default: 'Points To Obtain')}" />
						
						<th>${message(code: 'badge.nbUsers.label', default: 'Number of owners')} </th>
					</tr>
				</thead>
				<tbody>
				<g:each in="${badgeInstanceList}" status="i" var="badgeInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${badgeInstance.id}">${fieldValue(bean: badgeInstance, field: "name")}</g:link></td>
					
						<td>${fieldValue(bean: badgeInstance, field: "pointsToObtain")}</td>
						
						<td>${badgeInstance.users.size()}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${badgeInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
