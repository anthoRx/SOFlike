package org.isima

import java.sql.Timestamp;

class Versioning {
	String content
	Timestamp modificationDate
	
	static belongsTo = [interactionContent:InteractionContent]
	
    static constraints = {
    }
}
