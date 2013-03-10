
<%@ page import="org.isima.User" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'user.label', default: 'User')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'tag.css')}" type="text/css"/>
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
  					<img height="42" width="42" class="avatar" src="${createLink(controller:'user', action:'avatar_image', id:userInstance.ident())}" />
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
		    <li><a href="#tabs-2">Answers</a></li>
		    <li><a href="#tabs-3">Questions</a></li>
		    <li><a href="#tabs-4">Tags</a></li>
		    <li><a href="#tabs-5">Badges</a></li>
		    
		  </ul>
		  <div id="tabs-2">
		  <g:if test="${answers?.size() > 0}">
		   <g:each var="answer" in="${answers}">
		  		
					<g:link controller="question" action="show" id="${answer.question.id}">${answer?.getResume()}  </g:link><i><g:formatDate format="'the' dd/MM/yyyy 'at' hh:ss" date="${answer.creationDate}"/></i></span>
					</br>
				
			</g:each>
		</g:if>
		<g:else>
			No answers available
		</g:else>
		  </div>
		  
		  <div id="tabs-3">
		  <g:if test="${questions?.size() > 0}">
		  	<g:each var="question" in="${questions}">
				<g:link controller="question" action="show" id="${question.id}"><span>${question.title}</g:link> <i><g:formatDate format="'the' dd/MM/yyyy 'at' hh:ss" date="${question.creationDate}"/></i></span>
				</br>
			</g:each>
		  </g:if>
		  <g:else>
		  	No questions available
		  </g:else>
		  </div>
		  <div id="tabs-4">
		  <g:if test="${userInstance?.getUserTags().size() > 0}">
		  <table>
				<tbody>
					<tr>
					    <g:each var="tag" in="${userInstance?.getUserTags()}">
								<span class="usual_tag">
									<g:link controller="tag" action="show" id="${tag.key?.id}">${tag.key?.encodeAsHTML()}</g:link>
								</span>	
								<span style="font-size: small;color: gray;">&nbsp;x&nbsp;${tag.value}</span>
								&nbsp;&nbsp;&nbsp;&nbsp;
						</g:each>	  
					</tr>
				</tbody>
			</table>
			</g:if>
			<g:else>
		  		No tags available
		  	</g:else>
		  </div>
		  
		  <div id="tabs-5">
		   <g:if test="${userInstance?.badges.size() > 0}">
		  	<g:each var="badge" in="${userInstance?.badges}">
				<span>${badge.name}</span>
				</br>
			</g:each>
		  </g:if>
		  <g:else>
		  	No badges available
		  </g:else>
		  </div>
		</div>

	</body>
</html>
