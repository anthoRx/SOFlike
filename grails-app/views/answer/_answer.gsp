<div>
<div id="vote" style="float: left;margin-left: 5%; margin-top: 2%;">
	<table>
	<tr><td><a class="home" href="${createLink(uri: '/')}"><img src="${resource(dir: 'images', file: 'Up.png')}" alt="Home"/></a></td></tr>
	<tr><td style="font-size: 26px;text-align: center;">${answerInstance?.getValeurVotes()}</td></tr>
	<tr><td><a class="home" href="${createLink(uri: '/')}"><img src="${resource(dir: 'images', file: 'Down.png')}" alt="Home"/></a></td></tr>
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
		<p><g:fieldValue bean="${answerInstance}" field="content"/></p>
</g:if>
</div>
	
<div id="bottom" align="center" style="margin-left: 20%;">
	<table>
	<tr>
	<td style="width: 33%;">Edit</td>
	<td style="width: 33%;">
		<g:if test="${answerInstance?.creationDate}">
			<span><g:fieldValue bean="${answerInstance}" field="creationDate"/></span>
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
<br/>
</div>