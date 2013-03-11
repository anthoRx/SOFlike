<div >
	<g:hasErrors bean="${commentInstance}">
	<ul class="errors" role="alert">
		<g:eachError bean="${commentInstance}" var="error">
		<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
		</g:eachError>
	</ul>
	</g:hasErrors>
	<fieldset class="form">
		<g:textField name="content" value=""/>
	</fieldset>
</div>