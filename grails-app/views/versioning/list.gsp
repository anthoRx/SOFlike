<%@ page import="org.isima.Versioning" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'versioning.label', default: 'Versioning')}" />
		<title><g:message code="default.title.versioning" default="History"/></title>
	</head>
	<body>
		<a href="#list-versioning" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
	
		<div id="list-versioning" class="content scaffold-list" role="main">
			<h1>${message(code: 'default.title.versioning', default: 'History')}</h1>
				<g:if test="${questionInstance}"> 
					<span style="float: right">
						<g:link controller="question" action="show" id="${questionInstance.id}">${message(code: 'versioning.back.label', default: 'Back')}</g:link>
					</span>
				</g:if>
			<br/>
			<table>
				<thead>
					<tr>
						<g:sortableColumn property="modificationDate" title="${message(code: 'versioning.modificationDate.label', default: 'Modification Date')}" />
						<g:sortableColumn property="content" title="${message(code: 'versioning.content.label', default: 'Content')}" />						
					</tr>
				</thead>
				<tbody>
				<g:each in="${versioningInstanceList}" status="i" var="versioningInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">						
						<td><g:formatDate format="MMM	''yy 'at' k:mm" date="${versioningInstance?.modificationDate}" locale="EN_en"/></td>	
						<td><g:link action="show" id="${versioningInstance.id}">${versioningInstance.content.substring(0, 40).decodeHTML()}...</g:link></td>				
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${versioningInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
