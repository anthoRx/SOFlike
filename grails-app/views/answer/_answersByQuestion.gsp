<g:if test="${questionInstance?.answers}">
		<g:each in="${questionInstance.answers}" var="answerInstance">
			<g:render template="/answer/answer" model="['answerInstance': answerInstance]" />
		</g:each>
</g:if>