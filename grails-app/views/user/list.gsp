
<%@ page import="org.isima.User" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'user.label', default: 'User')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-user" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-user" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /> (${userInstanceList.size()})</h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
				<div style="width: 20%;">
				<table>
				<tr>
				<g:each in="${userInstanceList}" status="i" var="userInstance">
						
						<td>
							<g:if test="${userInstance?.avatar}">
	  							<img src="${createLink(controller:'user', action:'avatar_image', id:userInstance.ident())}" />
							</g:if>
						</td>
						<td>
							<table>
	
								<tr><td><g:link action="show" id="${userInstance.id}">${fieldValue(bean: userInstance, field: "username")}</g:link></td></tr>
							
								<tr style="height: 3px;"><td style="height: 3px;">${fieldValue(bean: userInstance, field: "points")}</td></tr>
							
								<tr><td>${fieldValue(bean: userInstance, field: "badges")}</td></tr>
							</table>
						</td>
						<td></td>
						<g:if test="${i != 0 && i%4==0}">
							</tr><tr>
						</g:if>
				</g:each>
				</tr>	
				</table>
				</div>	
			<div class="pagination">
				<g:paginate total="${userInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
