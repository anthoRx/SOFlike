<head>
	<g:javascript library="jquery" />
</head>

<g:if test="${questionInstance?.answers}">
		<g:each in="${questionInstance.answers}" var="answerInstance">
			<g:render template="/answer/answer" model="['answerInstance': answerInstance]" />
			<div id="comments${answerInstance?.id}">
				<g:render template="/comment/commentsByQuestion" model="['answerInstance':answerInstance]" />
			</div>
			
			<sec:ifLoggedIn>
					<button  name="addComment" value="Add Comment" onclick="showAddComment(${answerInstance?.id})">Add Comment</button>
			</sec:ifLoggedIn>	
		<br/>
		</g:each>
</g:if>

<script>
	function showAddComment(id)
	{
		document.getElementById('addComment'+id).style.display='block';
	}
</script>