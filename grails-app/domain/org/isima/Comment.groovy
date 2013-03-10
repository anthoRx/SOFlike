package org.isima

import java.sql.Timestamp;

class Comment {
	String content
	Date creationDate
	
	static belongsTo = [interactionContent:InteractionContent]
	
    static constraints = {
    }
	
}
