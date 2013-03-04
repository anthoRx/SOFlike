package org.isima

import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils

class UserService {
	
	def springSecurityService
	
    def serviceMethod() {

    }
	
	/**
	 * Determines whether the current user
	 * has the authority to edit the instance
	 * 
	 * Source : Bobby Warner
	 * 
	 * @attr instance the instance to evaluate
	 **/
	boolean hasPermission(instance) {
		if (SpringSecurityUtils.ifAnyGranted("ROLE_ADMIN")
		|| instance?.user.id == springSecurityService.getCurrentUser().id) {
			return true
		} else {
			return false
		}
	}
}
