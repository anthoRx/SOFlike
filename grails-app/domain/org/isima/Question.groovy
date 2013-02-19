package org.isima

import java.sql.Timestamp

class Question extends InteractionContent {
	String title
	int nbView
	
	static hasMany = [tags: Tag, answers:Answer]
	
    static constraints = {
    }
	
	def beforeInsert() {
		Date date= new Date()
		creationDate = new Timestamp(date.getTime())
	}
}
