package org.isima

import java.util.Date;


abstract class InteractionContent {
	String content;
	Date creationDate;
	
	static hasMany = [votes: Vote, versionings: Versioning, comments: Comment]
	static belongsTo = [user:User]
	
    static constraints = {
    }
}
