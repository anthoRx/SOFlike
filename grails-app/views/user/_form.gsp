<%@ page import="org.isima.User" %>



<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'username', 'error')} required">
	<label for="username">
		<g:message code="user.username.label" default="Username" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="username" required="" value="${userInstance?.username}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'password', 'error')} required">
	<label for="password">
		<g:message code="user.password.label" default="Password" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="password" required="" value="${userInstance?.password}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="user.name.label" default="Name" />
		
	</label>
	<g:textField name="name" value="${userInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'lastName', 'error')} ">
	<label for="lastName">
		<g:message code="user.lastName.label" default="Last Name" />
		
	</label>
	<g:textField name="lastName" value="${userInstance?.lastName}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'points', 'error')} required">
	<label for="points">
		<g:message code="user.points.label" default="Points" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="points" type="number" value="${userInstance.points}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'accountExpired', 'error')} ">
	<label for="accountExpired">
		<g:message code="user.accountExpired.label" default="Account Expired" />
		
	</label>
	<g:checkBox name="accountExpired" value="${userInstance?.accountExpired}" />
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'accountLocked', 'error')} ">
	<label for="accountLocked">
		<g:message code="user.accountLocked.label" default="Account Locked" />
		
	</label>
	<g:checkBox name="accountLocked" value="${userInstance?.accountLocked}" />
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'badges', 'error')} ">
	<label for="badges">
		<g:message code="user.badges.label" default="Badges" />
		
	</label>
	<g:select name="badges" from="${org.isima.Badge.list()}" multiple="multiple" optionKey="id" size="5" value="${userInstance?.badges*.id}" class="many-to-many"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'enabled', 'error')} ">
	<label for="enabled">
		<g:message code="user.enabled.label" default="Enabled" />
		
	</label>
	<g:checkBox name="enabled" value="${userInstance?.enabled}" />
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'interactionContents', 'error')} ">
	<label for="interactionContents">
		<g:message code="user.interactionContents.label" default="Interaction Contents" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${userInstance?.interactionContents?}" var="i">
    <li><g:link controller="interactionContent" action="show" id="${i.id}">${i?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="interactionContent" action="create" params="['user.id': userInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'interactionContent.label', default: 'InteractionContent')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'passwordExpired', 'error')} ">
	<label for="passwordExpired">
		<g:message code="user.passwordExpired.label" default="Password Expired" />
		
	</label>
	<g:checkBox name="passwordExpired" value="${userInstance?.passwordExpired}" />
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'avatar', 'error')} ">
	<fieldset>
  <legend>Avatar Upload</legend>
  
    <label for="avatar">Avatar (16K)</label>
    <input type="file" name="avatar" id="avatar" />
    <div style="font-size:0.8em; margin: 1.0em;">
      For best results, your avatar should have a width-to-height ratio of 4:5.
      For example, if your image is 80 pixels wide, it should be 100 pixels high.
    </div>
	</fieldset>
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'votes', 'error')} ">
	<label for="votes">
		<g:message code="user.votes.label" default="Votes" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${userInstance?.votes?}" var="v">
    <li><g:link controller="vote" action="show" id="${v.id}">${v?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="vote" action="create" params="['user.id': userInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'vote.label', default: 'Vote')])}</g:link>
</li>
</ul>

</div>

