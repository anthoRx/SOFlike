package org.isima

import java.awt.TextArea;
import java.util.Date;


abstract class InteractionContent implements Comparable<InteractionContent> {
	String content;
	Date creationDate;
	
	
	static hasMany = [votes: Vote, versionings: Versioning, comments: Comment]
	static belongsTo = [user:User]
	
    static constraints = {
		content ( blank: false, maxSize: 50000, html: true )
		versionings: nullable: false 	
    }
	
	//For sorting comments by date
	static mapping = {
		comments sort:'creationDate'
	}
	
	//CompareTo method to have the more voted answer first
	int compareTo(InteractionContent other) {
		return  other.getValeurVotes() <=> getValeurVotes()
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
	
	def beforeInsert() {
		Date date= new Date()
		creationDate = date
	}
}
