package org.isima

class Tag {
	String name
	
	static hasMany = [questions:Question]
	static belongsTo = [Question]
	
    static constraints = {
    }
	
	@Override String toString() {
		return name
	}
}
