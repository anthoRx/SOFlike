package org.isima

import java.sql.Timestamp;


abstract class InteractionContent {
	String content;
	Timestamp creationDate;
	
	static hasMany = [votes:Vote,versionings:Versioning,comments:Comment]
	static belongsTo = [user:User]
	
    static constraints = {
    }
}
