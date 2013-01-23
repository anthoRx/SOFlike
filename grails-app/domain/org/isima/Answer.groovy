package org.isima

class Answer extends InteractionContent{
	
	static belongsTo = [question:Question]
	
    static constraints = {
    }
}
