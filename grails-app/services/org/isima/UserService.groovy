package org.isima

import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils

class UserService {
	
	def springSecurityService
	
	
	/**
	 * Determines whether the current user
	 * has the authority to edit the instance
	 * 
	 * adapted from Bobby Warner's solution
	 * 
	 * @attr instance the instance to evaluate
	 **/
	boolean hasPermission(instance) {
		if(instance?.user != null  
			&& springSecurityService.isLoggedIn()
			&& (SpringSecurityUtils.ifAnyGranted("ROLE_ADMIN") || instance?.user.id == springSecurityService.getCurrentUser().id))	{
			return true
		} else {
			return false
		}
	}
}
