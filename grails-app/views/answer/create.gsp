<%@ page import="org.isima.Answer" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'answer.label', default: 'Answer')}" />
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'question.css')}" type="text/css" />
		<resource:richTextEditor type="advanced" />
		<title><g:message code="default.create.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#create-answer" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		
		<h1>${questionInstance?.title}</h1>
		<div class="contentQuest">
			<g:if test="${questionInstance?.content}">
					<p>${questionInstance?.content.decodeHTML()}</p>
			</g:if>
		</div>
		
		<br/>
		
		<div id="create-answer" class="content scaffold-create" role="main">
			<h1><g:message code="answer.create.label"/></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:hasErrors bean="${answerInstance}">
			<ul class="errors" role="alert">
				<g:eachError bean="${answerInstance}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
			</g:hasErrors>
	
			<g:form action="save" >
				<g:hiddenField name="questionId" value="${questionInstance?.id}" />
				<fieldset class="form">
					<div class="fieldcontain ${hasErrors(bean: answerInstance, field: 'content', 'error')} ">						
						<richui:richTextEditor name="contentAnswer" value=""/>
					</div>
				</fieldset>
				<fieldset class="buttons">
					<g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
