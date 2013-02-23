package org.isima

import groovy.transform.ToString;

class Badge {
	
	String name;
	int pointsToObtain;
	
	static hasMany = [users:User]
	static belongsTo = User
	
    static constraints = {
		name blank:false
		pointsToObtain blank:false
    }
	
	
	@Override String toString()
	{
		return this.name
	}
}
