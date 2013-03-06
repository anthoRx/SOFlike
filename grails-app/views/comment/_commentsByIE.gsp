<div>
	<g:each in="${ieInstance?.comments}" var="commentInstance">
		<g:render template="/comment/comment" model="['commentInstance':commentInstance]" />
	</g:each>
</div>
<br/>

<g:if test="${flash.message}">
		<div class="message" role="status">${flash.message}</div>
</g:if>