
<%@ page import="org.isima.Question" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'question.label', default: 'Question')}" />
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'tag.css')}" type="text/css" />
		
		<title><g:message code="default.show.label" args="[entityName]" /></title>	
		<g:javascript library="jquery" />
		
	</head>
	<body>
		<a href="#show-question" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>

		<div id="show-question" class="content scaffold-show" role="main">
			<h1>${questionInstance?.title}</h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>

				<g:if test="${questionInstance?.comments}">
						<g:each in="${questionInstance.comments}" var="c">
						<span class="property-value" aria-labelledby="comments-label"><g:link controller="comment" action="show" id="${c.id}">${c?.encodeAsHTML()}</g:link></span>
						</g:each>
				</g:if>
			
				<div id="vote" style="float: left;margin-left: 5%; margin-top: 2%;">
					<table>
					<tr><td>
						<sec:ifLoggedIn>
		              	<g:formRemote name="myForm" on404="alert('not found!')" update="noteValue"
		              	url="[controller: 'vote', action:'saveInShow']">
                    	<g:hiddenField name="user.id" value="" />
                    	<g:hiddenField name="value" value="1" />
                    	<g:hiddenField name="interactionContent.id" value="${questionInstance?.id}" />
		              	<button style="border:none; background-color:transparent;" type="submit" name="method_premium" value="">
  							<img src="${resource(dir: 'images', file: 'Up.png')}" alt="Home"/>
						</button>
						</g:formRemote>
						</sec:ifLoggedIn>
						</td></tr>
					<tr><td id="noteValue" style="font-size: 26px;text-align: center;">${questionInstance?.getValeurVotes()}</td></tr>
					<tr><td>
					<sec:ifLoggedIn>
						<g:formRemote name="myForm" on404="alert('not found!')" update="noteValue"
		              	url="[controller: 'vote', action:'saveInShow']">
	                    	<g:hiddenField name="user.id" value="" />
	                    	<g:hiddenField name="value" value="-1" />
	                    	<g:hiddenField name="interactionContent.id" value="${questionInstance?.id}" />
			              	<button style="border:none; background-color:transparent;" type="submit" name="method_premium" value="">
	  							<img src="${resource(dir: 'images', file: 'Down.png')}" alt="Home"/>
							</button>
						</g:formRemote>
					</sec:ifLoggedIn>
					</td></tr>
					</table>
				</div>
				
				<div id="contentQuest" style="background-color: #eee;
				border: .2em solid #fff;
				margin: 2em 2em 1em;
				padding: 1em;
			
				-moz-box-shadow: 0px 0px 1.25em #ccc;
				-webkit-box-shadow: 0px 0px 1.25em #ccc;
				box-shadow: 0px 0px 1.25em #ccc;
				-moz-border-radius: 0.6em;
				-webkit-border-radius: 0.6em;
				border-radius: 0.6em;
				margin-left: 20%;">
				<g:if test="${questionInstance?.content}">
						<p>${questionInstance?.content.decodeHTML()}</p>
				</g:if>
				</div>
				
				<div id="tags" style="margin-left: 20%;">
					<g:if test="${questionInstance?.tags}">
							<g:each in="${questionInstance.tags}" var="t">
								<span class="usual_tag"><g:link controller="tag" action="show" id="${t.id}">${t?.encodeAsHTML()}</g:link></span>
							</g:each>
					</g:if>
				</div>
				
				<div id="bottom" align="center" style="margin-left: 20%;">
				<table>
				<tr>
				<td style="width: 33%;">
					<sec:ifAuthorized value="${questionInstance}">
						<g:form>
							<fieldset class="buttons">
								<g:hiddenField name="id" value="${questionInstance?.id}" />
								<g:link class="edit" action="edit" id="${questionInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
								<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
							</fieldset>
						</g:form>
					</sec:ifAuthorized>
				</td>
				<td style="width: 33%;">
					<g:if test="${questionInstance?.creationDate}">
						<span>asked <g:formatDate format="MMM	''yy 'at' k:m" date="${questionInstance?.creationDate}" locale="EN_en"/></span>
					</g:if>
				</td>
				<td style="width: 33%;text-align:center;">
				<g:if test="${questionInstance?.user}">
					<g:link controller="user" action="show" id="${questionInstance?.user?.id}">${questionInstance?.user?.encodeAsHTML()}</g:link>		
				</g:if>
			
				</td>
				</tr>
				</table>
				</div>
				<br/>
				
				<g:if test="${questionInstance?.answers}">
					<h1>Answers</h1>
					<div id="answers">
						<g:render template="/answer/answersByQuestion" model="['questionInstance':questionInstance]" />			
					</div>
				</g:if>
				<br/>
				
				<sec:ifLoggedIn>
					<g:formRemote name="myForm" on404="alert('not found!')" update="answers" url="[controller: 'answer', action:'saveInShow']">						
						<g:render template="/answer/createInShow"  />
						<g:hiddenField name="questionInstance" value="${questionInstance?.id}" />
						<fieldset class="buttons">
							<g:submitButton name="create" class="save" value="Add Answer" />
						</fieldset>
					</g:formRemote>
				</sec:ifLoggedIn>		
		</div>
	</body>
</html>
