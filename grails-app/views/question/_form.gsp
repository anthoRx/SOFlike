<%@ page import="org.isima.Question" %>
<head>
	
	<g:javascript library="jquery" />
    <resource:richTextEditor type="advanced" />
	<resource:autoComplete skin="default" />
</head>

<table>
<tr>
	<td>${hasErrors(bean: questionInstance, field: 'title', 'error')}</td>
	<td><g:message code="question.title.label" default="Title" /></td>
	<td><g:textField name="title" value="${questionInstance?.title}"/></td>
</tr>
<tr>
	<td>${hasErrors(bean: questionInstance, field: 'content', 'error')}</td>
	<td></td>
	<td><richui:richTextEditor name="content" value="${questionInstance?.content}"/></td>
</tr>
<tr>
	<td>${hasErrors(bean: questionInstance, field: 'tags', 'error')}</td>
	<td><g:message code="question.tags.label" default="Tags" /></td>
	<td>
	<g:select name="tags" from="${org.isima.Tag.list()}" multiple="multiple" optionKey="id" size="5" value="${questionInstance?.tags*.id}" class="many-to-many"/>
</td>
</tr>
</table>