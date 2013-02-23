<%@ page import="org.isima.Vote" %>



<div class="fieldcontain ${hasErrors(bean: voteInstance, field: 'interactionContent', 'error')} required">
	<label for="interactionContent">
		<g:message code="vote.interactionContent.label" default="Interaction Content" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="interactionContent" name="interactionContent.id" from="${org.isima.InteractionContent.list()}" optionKey="id" required="" value="${voteInstance?.interactionContent?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: voteInstance, field: 'user', 'error')} required">
	<label for="user">
		<g:message code="vote.user.label" default="User" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="user" name="user.id" from="${org.isima.User.list()}" optionKey="id" required="" value="${voteInstance?.user?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: voteInstance, field: 'value', 'error')} required">
	<label for="value">
		<g:message code="vote.value.label" default="Value" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="value" type="number" value="${voteInstance.value}" required=""/>
</div>

