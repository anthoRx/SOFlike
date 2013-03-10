
<%@ page import="org.isima.Answer" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'answer.label', default: 'Answer')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-answer" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		
		<div id="list-answer" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<th><g:message code="answer.content.label" default="Content" /></th>
					
						<g:sortableColumn property="creationDate" title="${message(code: 'answer.creationDate.label', default: 'Creation Date')}" />
					
						<th><g:message code="answer.question.label" default="Question" /></th>
					
						<th><g:message code="answer.user.label" default="User" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${answerInstanceList}" status="i" var="answerInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${answerInstance.id}">${answerInstance?.content.decodeHTML()}</g:link></td>
					
						<td>${fieldValue(bean: answerInstance, field: "creationDate")}</td>
					
						<td>${fieldValue(bean: answerInstance, field: "question.title")}</td>
					
						<td>${fieldValue(bean: answerInstance, field: "user")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${answerInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
