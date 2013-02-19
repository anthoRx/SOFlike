<%@ page import="org.isima.Question" %>

<div class="fieldcontain ${hasErrors(bean: questionInstance, field: 'title', 'error')} ">
	<label for="title">
		<g:message code="question.title.label" default="Title" />		
	</label>
	<g:textField name="title" value="${questionInstance?.title}"/>
</div>


<div class="fieldcontain ${hasErrors(bean: questionInstance, field: 'content', 'error')} ">
	<label for="content">
		<g:message code="question.content.label" default="Content" />		
	</label>
	<g:textArea name="content" value="${questionInstance?.content}"/>
</div>



<div class="fieldcontain ${hasErrors(bean: questionInstance, field: 'tags', 'error')} ">
	<label for="tags">
		<g:message code="question.tags.label" default="Tags" />
		
	</label>
	<g:select name="tags" from="${org.isima.Tag.list()}" multiple="multiple" optionKey="id" size="5" value="${questionInstance?.tags*.id}" class="many-to-many"/>
</div>




