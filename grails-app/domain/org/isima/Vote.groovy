package org.isima

class Vote {
	int value;
	
	static belongsTo = [user:User,interactionContent:InteractionContent]
	
    static constraints = {
    }
}
