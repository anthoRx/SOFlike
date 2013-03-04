<%@ page import="org.isima.Versioning" %>



<div class="fieldcontain ${hasErrors(bean: versioningInstance, field: 'content', 'error')} ">
	<label for="content">
		<g:message code="versioning.content.label" default="Content" />
		
	</label>
	<g:textField name="content" value="${versioningInstance?.content}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: versioningInstance, field: 'interactionContent', 'error')} required">
	<label for="interactionContent">
		<g:message code="versioning.interactionContent.label" default="Interaction Content" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="interactionContent" name="interactionContent.id" from="${org.isima.InteractionContent.list()}" optionKey="id" required="" value="${versioningInstance?.interactionContent?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: versioningInstance, field: 'modificationDate', 'error')} required">
	<label for="modificationDate">
		<g:message code="versioning.modificationDate.label" default="Modification Date" />
		<span class="required-indicator">*</span>
	</label>
	
</div>

