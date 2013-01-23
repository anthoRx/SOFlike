package org.isima

class Question {
	String title
	int nbView
	
	static hasMany = [tags:Tag,answers:Answer]
	
    static constraints = {
    }
}
