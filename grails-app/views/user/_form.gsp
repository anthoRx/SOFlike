<%@ page import="org.isima.User" %>


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
	<g:passwordField name="password" type="password" required="" value="${userInstance?.password}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'avatar', 'error')} ">
	<fieldset>
  <legend>Avatar Upload</legend>
  
    <label for="avatar">Avatar (3Mo)</label>
    <input type="file" name="avatarFile" id="avatarFile" />
    <div style="font-size:0.8em; margin: 1.0em;">
      For best results, your avatar should have a width-to-height ratio of 4:5.
      For example, if your image is 80 pixels wide, it should be 100 pixels high.
    </div>
	</fieldset>
</div>

<!-- If it's the admin, we can do other things -->
<sec:ifAllGranted roles="ROLE_ADMIN">
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
		
	<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'passwordExpired', 'error')} ">
		<label for="passwordExpired">
			<g:message code="user.passwordExpired.label" default="Password Expired" />
			
		</label>
		<g:checkBox name="passwordExpired" value="${userInstance?.passwordExpired}" />
	</div>
	
	
</sec:ifAllGranted>

