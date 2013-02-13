<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>Welcome to Grails</title>
		<style type="text/css" media="screen">
			#question {
				background-color: #eee;
				border: .2em solid #fff;
				margin: 2em 2em 1em;
				padding: 1em;
				width: auto;
				float: left;
				-moz-box-shadow: 0px 0px 1.25em #ccc;
				-webkit-box-shadow: 0px 0px 1.25em #ccc;
				box-shadow: 0px 0px 1.25em #ccc;
				-moz-border-radius: 0.6em;
				-webkit-border-radius: 0.6em;
				border-radius: 0.6em;
			}

			.ie6 #question {
				display: inline; /* float double margin fix http://www.positioniseverything.net/explorer/doubled-margin.html */
			}

			#question ul {
				font-size: 0.9em;
				list-style-type: none;
				margin-bottom: 0.6em;
				padding: 0;
			}
            
			#question li {
				line-height: 1.3;
			}

			#question h1 {
				text-transform: uppercase;
				font-size: 1.1em;
				margin: 0 0 0.3em;
			}
			
			#page-body {
				margin: 2em 1em 1.25em 18em;
			}

			h2 {
				margin-top: 1em;
				margin-bottom: 0.3em;
				font-size: 1em;
			}

			p {
				line-height: 1.5;
				margin: 0.25em 0;
			}

			#controller-list ul {
				list-style-position: inside;
			}

			#controller-list li {
				line-height: 1.3;
				list-style-position: inside;
				margin: 0.25em 0;
			}
			
			#TopQuestions {
				float: left;
				width: 90%;
			}
			
			#Tags {
				margin-left: 90%;
				width: 10%
			}
			

			@media screen and (max-width: 480px) {
				#question {
					display: none;
				}

				#page-body {
					margin: 0 1em 1em;
				}

				#page-body h1 {
					margin-top: 0;
				}
			}
		</style>
	</head>
	<body>
		<a href="#page-body" class="skip"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div id="TopQuestions" role="complementary">
			<h1>Top Questions</h1>			
			<g:each var="plugin" in="${applicationContext.getBean('pluginManager').allPlugins}">
			<table id="question">
				<tr>
					<td>0 votes</td>
					<td>0 answers</td>
					<td>0 views</td>
					<td><a>How to make a simple pie chart using Kendo UI ASP.NET MVC using Entity Framework?</a></td>
				</tr>
				<tr>
					<td/><td/><td/>
					<td><table><tr><td>TAG 1</td><td>TAG 2</td><td>TAG 3</td></tr></table></td>
					<td>Infos Publisher</td>
				</tr>
				</table>
			</g:each>		
		</div>
		<div id="Tags">
			<table>
				<tr>
				<td><h1>Tagged</h1></td>
				</tr>
				<tr>
					<td>TAG1</td>
				</tr>
				<tr>
					<td>TAG2</td>
				</tr>
				<tr>
					<td>TAG3</td>
				</tr>
			</table>
		</div>
	</body>
</html>
