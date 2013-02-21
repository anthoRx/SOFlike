package org.isima
import groovy.lang.GroovyClassLoader

class GameService {
	
	def grailsApplication
	
    def askForPoints(User usr, String actionFullName) {

		def config = grailsApplication.config.game[actionFullName]
		println config
    }
}
