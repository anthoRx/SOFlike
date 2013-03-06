
<div>
	<h1>Your Answer</h1>
	<g:hasErrors bean="${answerInstance}">
	<ul class="errors" role="alert">
		<g:eachError bean="${answerInstance}" var="error">
			<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
		</g:eachError>
	</ul>
	</g:hasErrors>	
	<fieldset class="form">
		<div class="fieldcontain ${hasErrors(bean: answerInstance, field: 'content', 'error')} ">		
			<g:textField name="contentAnswer"/>
		</div>
	</fieldset>
</div>
