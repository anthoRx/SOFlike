
<%@ page import="org.isima.Tag" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'tag.label', default: 'Tag')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'tag.css')}" type="text/css"/>
	</head>
	
	<body>
		
		<h1><g:message code="default.list.label" args="[entityName]" /></h1>
		<g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link>
		<div id="list-tag" style="width:100%; text-align:center;">
			<br/>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<tbody>
					<tr>
					<g:each in="${tagInstanceList}" status="i" var="tagInstance">
						${ (i % 5) == 0 ? '</tr><tr>' : ''}			
						<td>
							<span class="usual_tag">
								<g:link action="show" id="${tagInstance.id}">${fieldValue(bean: tagInstance, field: "name")}</g:link>
							</span>						
							<span style="font-size: small;color: gray;">&nbsp;x&nbsp;${tagInstance.questions.size()}</span>
						</td>
					</g:each>
					</tr>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${tagInstanceTotal}" />
			</div>
		</div>
		
	</body>
</html>
