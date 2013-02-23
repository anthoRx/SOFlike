package org.isima

import java.awt.TextArea;
import java.util.Date;


abstract class InteractionContent {
	String content;
	Date creationDate;
	
	static hasMany = [votes: Vote, versionings: Versioning, comments: Comment]
	static belongsTo = [user:User]
	
    static constraints = {
		content ( blank: false, maxSize: 50000, html: true )
    }
	
	int getValeurVotes()
	{
		int valVotes = 0;
		for(Vote vote in votes)
		{
			valVotes += vote.value;
		}
		
		return valVotes;
	}
}
