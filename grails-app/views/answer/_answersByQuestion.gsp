
<g:each in="${questionInstance?.answers}" var="answerInstance"> 
	<g:render template="/answer/answer" model="['answerInstance':answerInstance]" />		
	<br/>
</g:each>	
<g:if test="${flash.message}">
		<div class="message" role="status">${flash.message}</div>
</g:if>