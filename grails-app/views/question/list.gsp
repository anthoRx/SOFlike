
<%@ page import="org.isima.Question" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'question.label', default: 'Question')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'tag.css')}" type="text/css"/>
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'question.css')}" type="text/css" />
	</head>
	<body>
		<a href="#list-question" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		
		<div id="list-question" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
		</div>
		
		<div> 	
			<g:each in="${questionInstanceList}" status="i" var="questionInstance">
				<table class="contentQuest" style="width: 92%;">	
					<tr>
						<td>${questionInstance.getVotes().size()} Votes</td>
					
						<td>${fieldValue(bean: questionInstance, field: "nbView")} view(s)</td>
					
						<td>${questionInstance.getAnswers().size()} answer(s)</td>
					
						<td width="60%"><g:link action="show" id="${questionInstance.id}">${fieldValue(bean: questionInstance, field: "title")}</g:link></td>
					</tr>
					<tr>
						<td colspan="3">
							<span>asked <g:formatDate format="MMM ''dd 'at' k:mm" date="${questionInstance?.creationDate}" locale="EN_en"/></span>
							by ${fieldValue(bean: questionInstance, field: "user")}
						</td>
						<td>
							<g:if test="${questionInstance?.tags}">
								<g:each in="${questionInstance.tags}" var="t">
									<span class="usual_tag"><g:link controller="tag" action="show" id="${t.id}">${t?.encodeAsHTML()}</g:link></span>
								</g:each>
							</g:if>
						</td>
					</tr>
				</table>
			</g:each>
			
		</div>
		
		<div class="pagination">
				<g:paginate total="${questionInstanceTotal}" />
		</div>
	</body>
</html>
