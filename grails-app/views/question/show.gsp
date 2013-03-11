
<%@ page import="org.isima.Question" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'question.label', default: 'Question')}" />
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'tag.css')}" type="text/css" />
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'question.css')}" type="text/css" />
		<resource:richTextEditor type="advanced" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>	
		<g:javascript library="jquery" />		
	</head>
	<body>
		<a href="#show-question" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>

		<div id="show-question" class="content scaffold-show" role="main">
			<div >
				<h1>${questionInstance?.title}</h1>
				
				<span style="float: right;)">
					<sec:ifAuthorized value="${questionInstance}">
						<g:link class="edit" action="edit" id="${questionInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
						<g:remoteLink action="delete" id="${questionInstance?.id}" before="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');">${message(code: 'default.button.delete.label', default: 'Delete')}</g:remoteLink>
					</sec:ifAuthorized>			
					<sec:ifLoggedIn>
						<g:link controller="versioning" action="listByQuestion" id="${questionInstance?.id}"><g:message code="default.button.versioning.label" default="History" /></g:link>
					</sec:ifLoggedIn>
				</span>
				
			</div>
			<br/>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
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
				
				<div class="contentQuest" style="margin-left: 20%;">
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
				<br/>
				<div id="bottom" align="center" style="margin-left: 20%;">
				<table>
				<tr>
					<td style="width: 33%;">						
						<sec:ifLoggedIn>
							<a href="#qComments${questionInstance?.id}" onclick="showAddQcomment(${questionInstance?.id})">Comment</a>
						</sec:ifLoggedIn>
					</td>
					<td style="width: 33%;">
						<g:if test="${questionInstance?.creationDate}">
							<span>asked <g:formatDate format="MMM	''dd 'at' k:m" date="${questionInstance?.creationDate}" locale="EN_en"/></span>
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
				
				
				<div id="qComments${questionInstance?.id}">
					<g:render template="/comment/commentsByIE" model="['ieInstance':questionInstance]" />				
				</div>
				
				<div id="addQcomment${questionInstance?.id}" style="display: none">
					<g:formRemote name="myForm" on404="alert('not found!')" update="qComments${questionInstance?.id}"
				            	url="[controller: 'comment', action:'saveInShow', params: [ieId: "${questionInstance?.id}"]]">
						<g:render template="/comment/createInShow"  />
						<fieldset class="buttons">
							<g:submitButton name="create" class="save" value="Add Comment" />
						</fieldset>
					</g:formRemote>
				</div>
	

	<script>
		function showAddQcomment(id)
		{
			document.getElementById('addQcomment'+id).style.display='block';
		}
	</script>
				<h1>Answers</h1>	
				<div id="answers">
					<g:if test="${questionInstance?.answers}">					
						<g:render template="/answer/answersByQuestion" model="['questionInstance':questionInstance]" />
					</g:if>
					<g:else>
						<br/><br/><br/><br/>
					</g:else>
				</div>
				<br/>
				
				<sec:ifLoggedIn>
					<g:formRemote name="myForm" on404="alert('not found!')" update="answers" url="[controller: 'answer', action:'saveInShow']">
						<div>						
							<h1>Rapid Answer</h1>
							<span style="float:right">
								<g:link controller="answer" action="create" params="['id':questionInstance?.id]">Advanced answer >>></g:link>
							</span>
							<g:textArea name="contentAnswer" value="" style="width: 95%;margin-left:20px"/>
						</div>
						<g:hiddenField name="questionInstance" value="${questionInstance?.id}" />
						<br/>
						<fieldset class="buttons">
							<g:submitButton name="create" class="save" value="Add Answer" />
						</fieldset>
					</g:formRemote>
				</sec:ifLoggedIn>		
		</div>
	</body>
</html>
