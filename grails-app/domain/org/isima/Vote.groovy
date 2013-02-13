package org.isima

class Vote {
	int value;
	
	static belongsTo = [interactionContent:InteractionContent]
	
    static constraints = {
    }
}
