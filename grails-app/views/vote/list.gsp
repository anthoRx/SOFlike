
<%@ page import="org.isima.Vote" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'vote.label', default: 'Vote')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-vote" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		
		<div id="list-vote" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<th><g:message code="vote.interactionContent.label" default="Interaction Content" /></th>
					
						<th><g:message code="vote.user.label" default="User" /></th>
					
						<g:sortableColumn property="value" title="${message(code: 'vote.value.label', default: 'Value')}" />
						
						<th>Action</th>
					</tr>
				</thead>
				<tbody>
				<g:each in="${voteInstanceList}" status="i" var="voteInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td>${fieldValue(bean: voteInstance, field: "interactionContent")}</td>
					
						<td>${fieldValue(bean: voteInstance, field: "user")}</td>
					
						<td>${fieldValue(bean: voteInstance, field: "value")}</td>
						
						<td>
							<g:form controller="vote" method="post">
							<fieldset class="buttons">
								<g:hiddenField name="id" value="${voteInstance?.id}" />
								<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
							</fieldset>
							</g:form>
						</td>
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${voteInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
