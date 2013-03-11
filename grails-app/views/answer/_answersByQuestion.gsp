
<g:each in="${questionInstance?.answers.sort()}" var="answerInstance"> 
	<g:render template="/answer/answer" model="['answerInstance':answerInstance]" />		
	<br/>
</g:each>	