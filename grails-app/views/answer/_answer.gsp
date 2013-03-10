<div>
	<div id="vote" style="float: left;margin-left: 5%; ">
		<table>
			<tr>
				<td>
					<sec:ifLoggedIn>
			            	<g:formRemote name="myForm" on404="alert('not found!')" update="noteValue${answerInstance?.id}"
			            	url="[controller: 'vote', action:'saveInShow']">
			                	<g:hiddenField name="user.id" value="" />
			                	<g:hiddenField name="value" value="1" />
			                	<g:hiddenField name="interactionContent.id" value="${answerInstance?.id}" />
			            	<button style="border:none; background-color:transparent;" type="submit" name="method_premium" value="">
								<img src="${resource(dir: 'images', file: 'Up.png')}" alt="Home"/>
							</button>
							</g:formRemote>
					</sec:ifLoggedIn>
				</td>
			</tr>
			<tr>
				<td id="noteValue${answerInstance?.id}" style="font-size: 26px;text-align: center;">${answerInstance?.getValeurVotes()}</td>
			</tr>
			<tr>
				<td>
					<sec:ifLoggedIn>
						<g:formRemote name="myForm" on404="alert('not found!')" update="noteValue${answerInstance?.id}"
				            	url="[controller: 'vote', action:'saveInShow']">
				                 	<g:hiddenField name="user.id" value="" />
				                 	<g:hiddenField name="value" value="-1" />
				                 	<g:hiddenField name="interactionContent.id" value="${answerInstance?.id}" />
				             	<button style="border:none; background-color:transparent;" type="submit" name="method_premium" value="">
										<img src="${resource(dir: 'images', file: 'Down.png')}" alt="Home"/>
							</button>
						</g:formRemote>
					</sec:ifLoggedIn>
				</td>
			</tr>
		</table>
		
	</div>
					
	<div id="content" style="background-color: #eee;
	border: .2em solid #fff;
	margin: 2em 2em 1em;
	padding: 1em;
	
	-moz-box-shadow: 0px 0px 1.25em #ccc;
	-webkit-box-shadow: 0px 0px 1.25em #ccc;
	box-shadow: 0px 0px 1.25em #ccc;
	-moz-border-radius: 0.6em;
	-webkit-border-radius: 0.6em;
	border-radius: 0.6em;
	margin-left: 20%;">
	<g:if test="${answerInstance?.content}">
			<p>${answerInstance?.content.decodeHTML()}</p>
	</g:if>
	</div>
		
	<div id="bottom" align="center" style="margin-left: 20%;">
		<table>
		<tr>
			<td style="width: 33%;">
				<sec:ifAuthorized value="${answerInstance}">			
					<g:form controller="answer" method="post">
						<fieldset class="buttons">
							<g:hiddenField name="id" value="${answerInstance?.id}" />
							<g:actionSubmit class="edit" action="edit" value="${message(code: 'default.button.edit.label', default: 'Edit')}" />
							<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
						</fieldset>
					</g:form>
				</sec:ifAuthorized>
				<sec:ifLoggedIn>
					<button  name="addComment" value="Comment" onclick="showAddComment(${answerInstance?.id})">Comment</button>
				</sec:ifLoggedIn>
			</td>
			<td style="width: 33%;">
				<g:if test="${answerInstance?.creationDate}">
					<span>answered <g:formatDate format="MMM ''yy 'at' k:m" date="${answerInstance?.creationDate}" locale="EN_en"/></span>
				</g:if>
			</td>
			<td style="width: 33%;text-align:center;">
				<g:if test="${answerInstance?.user}">
					<g:link controller="user" action="show" id="${answerInstance?.user?.id}">${answerInstance?.user?.encodeAsHTML()}</g:link>		
				</g:if>		
			</td>
		</tr>
		</table>
	</div>		
	
	<div id="comments${answerInstance?.id}">
		<g:render template="/comment/commentsByIE" model="['ieInstance':answerInstance]" />				
	</div>		
	
	
	<div id="addComment${answerInstance?.id}" style="display: none">
		<g:formRemote name="myForm" on404="alert('not found!')" update="comments${answerInstance?.id}"
	            	url="[controller: 'comment', action:'saveInShow', params: [ieId: "${answerInstance?.id}"]]">
			<g:render template="/comment/createInShow"  />
			<fieldset class="buttons">
				<g:submitButton name="create" class="save" value="Add Comment" />
			</fieldset>
		</g:formRemote>
	</div>
	

	<script>
		function showAddComment(id)
		{
			document.getElementById('addComment'+id).style.display='block';
		}
	</script>
</div>


		