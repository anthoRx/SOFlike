
<%@ page import="org.isima.Question" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'question.label', default: 'Question')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
		<style type="text/css" media="screen">
			#question {
				background-color: #eee;
				border: .2em solid #fff;
				margin: 2em 2em 1em;
				padding: 1em;
				width: 80%;
				float: left;
				-moz-box-shadow: 0px 0px 1.25em #ccc;
				-webkit-box-shadow: 0px 0px 1.25em #ccc;
				box-shadow: 0px 0px 1.25em #ccc;
				-moz-border-radius: 0.6em;
				-webkit-border-radius: 0.6em;
				border-radius: 0.6em;
			}

			.ie6 #question {
				display: inline; /* float double margin fix http://www.positioniseverything.net/explorer/doubled-margin.html */
			}

			#question ul {
				font-size: 0.9em;
				list-style-type: none;
				margin-bottom: 0.6em;
				padding: 0;
			}
            
			#question li {
				line-height: 1.3;
			}

			#question h1 {
				text-transform: uppercase;
				font-size: 1.1em;
				margin: 0 0 0.3em;
			}
			
			#page-body {
				margin: 2em 1em 1.25em 18em;
			}

			h2 {
				margin-top: 1em;
				margin-bottom: 0.3em;
				font-size: 1em;
			}

			p {
				line-height: 1.5;
				margin: 0.25em 0;
			}
		</style>
	</head>
	<body>
		<a href="#list-question" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div id="list-question" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:each in="${questionInstanceList}" status="i" var="questionInstance">
			<table id="question">	
					<tr>
						<td>${questionInstance.getVotes().size()} Votes</td>
					
						<td>${fieldValue(bean: questionInstance, field: "nbView")} view(s)</td>
					
						<td>${questionInstance.getAnswers().size()} answer(s)</td>
					
						<td><g:link action="show" id="${questionInstance.id}">${fieldValue(bean: questionInstance, field: "title")}</g:link></td>
					</tr>
					<tr>
						<td/><td/><td/>
						<td><table><tr>
						<td>Tags : </td>
						<td>
						<g:if test="${questionInstance?.tags}">
						<g:each in="${questionInstance.tags}" var="t">
							<span><g:link controller="tag" action="show" id="${t.id}">${t?.encodeAsHTML()}</g:link></span>
						</g:each>
						<td>
						</g:if>
						</tr></table></td>
						<td>by ${fieldValue(bean: questionInstance, field: "user")}</td>
					</tr>
					<tr style="background-color: transparent"><td></br></td></tr>
			</table>
			</g:each>
		</div>
		<div class="pagination">
				<g:paginate total="${questionInstanceTotal}" />
		</div>
	</body>
</html>
