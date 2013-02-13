<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>Welcome to Grails</title>
		
	</head>
	<body>
		<a href="#page-body" class="skip"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="questions">
			<g:each var="question" in="${questions}">
				<span>${question.content}</span>
			</g:each>
		</div>
	</body>
</html>
