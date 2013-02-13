package org.isima

class Question extends InteractionContent {
	String title
	int nbView
	
	static hasMany = [tags:Tag,answers:Answer]
	
    static constraints = {
    }
}
