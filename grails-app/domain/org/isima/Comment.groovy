package org.isima

import java.sql.Timestamp;

class Comment {
	String content
	Timestamp creationDate
	
	static belongsTo = [interactionContent:InteractionContent]
	
    static constraints = {
    }
}
