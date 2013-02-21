<%@ page import="org.isima.Answer" %>
<head>
	<resource:richTextEditor type="advanced" />
</head>


<div class="fieldcontain ${hasErrors(bean: answerInstance, field: 'comments', 'error')} ">
	<label for="comments">
		<g:message code="answer.comments.label" default="Comments" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${answerInstance?.comments?}" var="c">
    <li><g:link controller="comment" action="show" id="${c.id}">${c?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="comment" action="create" params="['answer.id': answerInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'comment.label', default: 'Comment')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: answerInstance, field: 'content', 'error')} ">
	<label for="content">
		<g:message code="answer.content.label" default="Content" />
		
	</label>
	<richui:richTextEditor name="content" value="${fieldValue(bean:answerInstance, field:'content').decodeHTML()}" width="525" />
</div>

<div class="fieldcontain ${hasErrors(bean: answerInstance, field: 'creationDate', 'error')} required">
	<label for="creationDate">
		<g:message code="answer.creationDate.label" default="Creation Date" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="creationDate" value="${answerInstance?.creationDate}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: answerInstance, field: 'question', 'error')} required">
	<label for="question">
		<g:message code="answer.question.label" default="Question" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="question" name="question.id" from="${org.isima.Question.list()}" optionKey="id" required="" value="${answerInstance?.question?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: answerInstance, field: 'user', 'error')} required">
	<label for="user">
		<g:message code="answer.user.label" default="User" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="user" name="user.id" from="${org.isima.User.list()}" optionKey="id" required="" value="${answerInstance?.user?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: answerInstance, field: 'versionings', 'error')} ">
	<label for="versionings">
		<g:message code="answer.versionings.label" default="Versionings" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${answerInstance?.versionings?}" var="v">
    <li><g:link controller="versioning" action="show" id="${v.id}">${v?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="versioning" action="create" params="['answer.id': answerInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'versioning.label', default: 'Versioning')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: answerInstance, field: 'votes', 'error')} ">
	<label for="votes">
		<g:message code="answer.votes.label" default="Votes" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${answerInstance?.votes?}" var="v">
    <li><g:link controller="vote" action="show" id="${v.id}">${v?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="vote" action="create" params="['answer.id': answerInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'vote.label', default: 'Vote')])}</g:link>
</li>
</ul>

</div>

