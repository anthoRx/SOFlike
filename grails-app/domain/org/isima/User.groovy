package org.isima

class User {
	String name
	String lastName
	String password
	int points
	int userLevel
	
	static hasMany = [badges:Badge,votes:Vote,interactionContents:InteractionContent]
    static constraints = {
    }
}
