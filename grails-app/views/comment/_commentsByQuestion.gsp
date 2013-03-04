<g:if test="${answerInstance?.comments}">
	<g:each in="${answerInstance.comments}" var="commentInstance">
		<g:render template="/comment/comment" model="['commentInstance': commentInstance]" />
		<br/>
	</g:each>
</g:if>

<div id="addComment${answerInstance?.id}" style="display: none">
	<g:formRemote name="myForm" on404="alert('not found!')" update="comments${answerInstance?.id}"
            	url="[controller: 'comment', action:'saveInShow', params: [answerInstance: "${answerInstance?.id}"]]">
				<g:include controller="comment" action="create" />
				<fieldset class="buttons">
					<g:submitButton name="create" class="save" value="Add Comment" />
				</fieldset>
		</g:formRemote>
</div>