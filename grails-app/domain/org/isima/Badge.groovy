package org.isima

import groovy.transform.ToString;

class Badge {
	
	String name;
	
	static hasMany = [users:User]
	static belongsTo = User
    static constraints = {
    }
	
	
	@Override String toString()
	{
		return this.name
	}
}
