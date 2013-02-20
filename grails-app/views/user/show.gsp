
<%@ page import="org.isima.User" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'user.label', default: 'User')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
		<style type="text/css" media="screen">
			#Avatar {
				float: left;
				margin-left: 10%;
				margin-top: 5%;
			}
			
			#Infos {
				margin-left: 20%;
			}
			
			#tabs {
				font-size: 14px;
			}
			
		</style>
		
		<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.1/themes/base/jquery-ui.css" />
		<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
		<script src="http://code.jquery.com/ui/1.10.1/jquery-ui.js"></script>
		<link rel="stylesheet" href="/resources/demos/style.css" />
		<script>
		$(function() {
		  $( "#tabs" ).tabs();
		});
		</script>
	</head>
	<body>
		<a href="#show-user" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div id="show-user" class="content scaffold-show" role="main">
			<h1><g:fieldValue bean="${userInstance}" field="username"/></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			
			<!--  Avatar -->
			<div id="Avatar">
				<g:if test="${userInstance?.avatar}">
  					<img class="avatar" src="${createLink(controller:'user', action:'avatar_image', id:userInstance.ident())}" />
				</g:if>
			</div>
			
			<div id="Infos">
				<ol class="property-list user">			
					<g:if test="${userInstance?.username}">
					<li class="fieldcontain">
						<span id="username-label" class="property-label"><g:message code="user.username.label" default="Username" /></span>
						
							<span class="property-value" aria-labelledby="username-label"><g:fieldValue bean="${userInstance}" field="username"/></span>
						
					</li>
					</g:if>
				
					<g:if test="${userInstance?.name}">
					<li class="fieldcontain">
						<span id="name-label" class="property-label"><g:message code="user.name.label" default="Name" /></span>
						
							<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${userInstance}" field="name"/></span>
						
					</li>
					</g:if>
				
					<g:if test="${userInstance?.lastName}">
					<li class="fieldcontain">
						<span id="lastName-label" class="property-label"><g:message code="user.lastName.label" default="Last Name" /></span>
						
							<span class="property-value" aria-labelledby="lastName-label"><g:fieldValue bean="${userInstance}" field="lastName"/></span>
						
					</li>
					</g:if>
				
					<g:if test="${userInstance?.points}">
					<li class="fieldcontain">
						<span id="points-label" class="property-label"><g:message code="user.points.label" default="Points" /></span>
						
							<span class="property-value" aria-labelledby="points-label"><g:fieldValue bean="${userInstance}" field="points"/></span>
						
					</li>
					</g:if>
				
					<g:if test="${userInstance?.accountExpired}">
					<li class="fieldcontain">
						<span id="accountExpired-label" class="property-label"><g:message code="user.accountExpired.label" default="Account Expired" /></span>
						
							<span class="property-value" aria-labelledby="accountExpired-label"><g:formatBoolean boolean="${userInstance?.accountExpired}" /></span>
						
					</li>
					</g:if>
				
					<g:if test="${userInstance?.accountLocked}">
					<li class="fieldcontain">
						<span id="accountLocked-label" class="property-label"><g:message code="user.accountLocked.label" default="Account Locked" /></span>
						
							<span class="property-value" aria-labelledby="accountLocked-label"><g:formatBoolean boolean="${userInstance?.accountLocked}" /></span>
						
					</li>
					</g:if>				
				</ol>
			</div>
			<g:form>
			<g:if test="${userInstance?.id.toString() == sec.loggedInUserInfo(field:'id').toString()}">		
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${userInstance?.id}" />
					<g:link class="edit" action="edit" id="${userInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<sec:ifAllGranted roles="ROLE_ADMIN">
						<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />			
					</sec:ifAllGranted>
				</fieldset>
			</g:if>
			<g:else>
			<br/><br/>
			</g:else>
			</g:form>
		</div>
		
		<div id="tabs">
		  <ul>
		  	<li><a href="#tabs-1">Summary</a></li>
		    <li><a href="#tabs-2">Answers</a></li>
		    <li><a href="#tabs-3">Questions</a></li>
		    <li><a href="#tabs-4">Tags</a></li>
		    <li><a href="#tabs-5">Badges</a></li>
		    
		  </ul>
		  <div id="tabs-1">
		    <p>Proin elit arcu, rutrum commodo, vehicula tempus, commodo a, risus. Curabitur nec arcu. Donec sollicitudin mi sit amet mauris. Nam elementum quam ullamcorper ante. Etiam aliquet massa et lorem. Mauris dapibus lacus auctor risus. Aenean tempor ullamcorper leo. Vivamus sed magna quis ligula eleifend adipiscing. Duis orci. Aliquam sodales tortor vitae ipsum. Aliquam nulla. Duis aliquam molestie erat. Ut et mauris vel pede varius sollicitudin. Sed ut dolor nec orci tincidunt interdum. Phasellus ipsum. Nunc tristique tempus lectus.</p>
		  </div>
		  
		  <div id="tabs-2">
		 
		  </div>
		  
		  <div id="tabs-3">
		  <g:if test="${questions?.size() > 0}">
		  	<g:each var="question" in="${questions}">
				<g:link controller="question" action="show" id="${question.id}"><span>${question.content}</g:link> <i><g:formatDate format="'the' MM/dd/yyyy 'at' hh:ss" date="${question.creationDate}"/></i></span>
			</g:each>
		  </g:if>
		  <g:else>
		  	No questions available
		  </g:else>
		  </div>
		  <div id="tabs-4">
		    <p>Mauris eleifend est et turpis. Duis id erat. Suspendisse potenti. Aliquam vulputate, pede vel vehicula accumsan, mi neque rutrum erat, eu congue orci lorem eget lorem. Vestibulum non ante. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Fusce sodales. Quisque eu urna vel enim commodo pellentesque. Praesent eu risus hendrerit ligula tempus pretium. Curabitur lorem enim, pretium nec, feugiat nec, luctus a, lacus.</p>
		    <p>Duis cursus. Maecenas ligula eros, blandit nec, pharetra at, semper at, magna. Nullam ac lacus. Nulla facilisi. Praesent viverra justo vitae neque. Praesent blandit adipiscing velit. Suspendisse potenti. Donec mattis, pede vel pharetra blandit, magna ligula faucibus eros, id euismod lacus dolor eget odio. Nam scelerisque. Donec non libero sed nulla mattis commodo. Ut sagittis. Donec nisi lectus, feugiat porttitor, tempor ac, tempor vitae, pede. Aenean vehicula velit eu tellus interdum rutrum. Maecenas commodo. Pellentesque nec elit. Fusce in lacus. Vivamus a libero vitae lectus hendrerit hendrerit.</p>
		  </div>
		  
		  <div id="tabs-5">
		   <g:if test="${userInstance?.badges.size() > 0}">
		  	<g:each var="badge" in="${userInstance?.badges}">
				<span>${badge.name}</span>
			</g:each>
		  </g:if>
		  <g:else>
		  	No questions available
		  </g:else>
		  </div>
		</div>

	</body>
</html>
