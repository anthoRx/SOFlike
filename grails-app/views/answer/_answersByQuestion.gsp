<g:if test="${questionInstance?.answers}">
		<g:each in="${questionInstance.answers}" var="answerInstance">
			<g:render template="/answer/answer" model="['answerInstance': answerInstance]" />
			<br/>
		</g:each>
</g:if>