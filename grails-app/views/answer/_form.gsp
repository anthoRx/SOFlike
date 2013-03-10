<%@ page import="org.isima.Answer" %>
<head>
	<resource:richTextEditor type="advanced" />
</head>

<div class="fieldcontain ${hasErrors(bean: answerInstance, field: 'content', 'error')} ">
	<richui:richTextEditor name="content" value="${answerInstance?.content}"/>
</div>
