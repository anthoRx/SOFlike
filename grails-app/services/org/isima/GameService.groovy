package org.isima

import groovy.lang.GroovyClassLoader

import org.apache.jasper.tagplugins.jstl.core.Catch;
import org.apache.log4j.*
import org.codehaus.groovy.runtime.typehandling.GroovyCastException;

class GameService {
	
	def grailsApplication
	def springSecurityService
	
	/**
	 * This method check the game config file to to know if points have to 
	 * be add to the user provided in params.
	 * 
	 * @param usr The user concerned by the points
	 * @param actionFullName Name of action realized
	 * @return nothing
	 */
    def askForPoints(User usr, String domain, String action) {
		
		// try to find the number of points associed with the action
		def configPoints = grailsApplication.config.game.points[domain][action]
		
		if(configPoints) {
			try {
				// add points to the user
				usr.points += configPoints
				
				// save modifications
				usr.save(flush: true)
				
				// assign badges
				assignBadgesToUser(usr)							
			} catch(GroovyCastException e) {
				log.error("Error in GameConfig file, values for points must be integers")
			}
		}
		else
			log.warn("Action \""+domain+"."+action+"\" doesn't find in game config.")
    }
	
	/**
	 * This method assign badges to the user usr
	 * 
	 * @param usr The user who must be added the badges 
	 * @return nothing
	 */
	def assignBadgesToUser(User usr) {
		// Add badge(s) to the user if he has enough points
		for(badge in Badge.getAll()) {
			if(badge.pointsToObtain <= usr.points 
					&& ! usr.badges.contains(badge))
				usr.badges.add(badge)
		}		
		usr.save(flush: true)
	}
}
