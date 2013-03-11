<%@ page import="org.isima.Badge" %>



<div class="fieldcontain ${hasErrors(bean: badgeInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="badge.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" required="" value="${badgeInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: badgeInstance, field: 'pointsToObtain', 'error')} required">
	<label for="pointsToObtain">
		<g:message code="badge.pointsToObtain.label" default="Points To Obtain" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="pointsToObtain" type="number" value="${badgeInstance.pointsToObtain}" required=""/>
</div>


