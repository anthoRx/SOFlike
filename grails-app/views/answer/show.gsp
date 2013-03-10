
<%@ page import="org.isima.Answer" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'answer.label', default: 'Answer')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-answer" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
	
		<div id="show-answer" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list answer">
										
				<g:if test="${answerInstance?.content}">
				<li class="fieldcontain">
					<span id="content-label" class="property-label"><g:message code="answer.content.label" default="Content" /></span>
					
						<span class="property-value" aria-labelledby="content-label">${fieldValue(bean:answerInstance, field:'content').decodeHTML()}</span>
					
				</li>
				</g:if>
			
				<g:if test="${answerInstance?.creationDate}">
				<li class="fieldcontain">
					<span id="creationDate-label" class="property-label"><g:message code="answer.creationDate.label" default="Creation Date" /></span>
					
						<span class="property-value" aria-labelledby="creationDate-label">
							<g:formatDate format="MMM ''yy 'at' k:mm" date="${answerInstance?.creationDate}" locale="EN_en"/>
						</span>
					
				</li>
				</g:if>
			
				<g:if test="${answerInstance?.user}">
				<li class="fieldcontain">
					<span id="user-label" class="property-label"><g:message code="answer.user.label" default="User" /></span>
					
						<span class="property-value" aria-labelledby="user-label"><g:link controller="user" action="show" id="${answerInstance?.user?.id}">${answerInstance?.user?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${answerInstance?.question}">
				<li class="fieldcontain">
					<span id="question-label" class="property-label"><g:message code="answer.question.label" default="Question" /></span>
					
						<span class="property-value" aria-labelledby="question-label">
							<g:link controller="question" action="show" id="${answerInstance?.question?.id}">${answerInstance?.question?.title}</g:link>
						</span>
					
				</li>
				</g:if>
				
				<g:if test="${answerInstance?.comments}">
				<li class="fieldcontain">
					<span id="comments-label" class="property-label"><g:message code="answer.comments.label" default="Comments" /></span>
					
						<g:each in="${answerInstance.comments}" var="c">
						<span class="property-value" aria-labelledby="comments-label"><g:link controller="comment" action="show" id="${c.id}">${c?.content.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
				
				<g:if test="${answerInstance?.versionings}">
				<li class="fieldcontain">
					<span id="versionings-label" class="property-label"><g:message code="answer.versionings.label" default="Versionings" /></span>
					
						<g:each in="${answerInstance.versionings}" var="v">
						<span class="property-value" aria-labelledby="versionings-label"><g:link controller="versioning" action="show" id="${v.id}">${v?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${answerInstance?.votes}">
				<li class="fieldcontain">
					<span id="votes-label" class="property-label"><g:message code="answer.votes.label" default="Votes" /></span>
					
					<span class="property-value" aria-labelledby="votes-label">
						${answerInstance.getValeurVotes()}
					</span>
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${answerInstance?.id}" />
					<g:link class="edit" action="edit" id="${answerInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
