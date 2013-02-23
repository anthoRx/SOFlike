package org.isima



import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(GameService)
@Mock(User)
class GameServiceTests {
	
	GameService gameService
	
    void testAskForPointsNominalCase() {
		def domain = "question"
		def action = "create"
		def points = 100
		def user = new User(username: 'bobby', enabled: false, password: 'password', name: 'Bob', lastName: 'Bobby', points: 0)
		
		service.grailsApplication.config.game.points[domain][action] = points
		
        service.askForPoints(user, domain,action)
		
		assert user.points == 100
    }
	
	void testAskForPointsBadProperty() {
		def domain = "question"
		def action = "create"
		def points = "notInteger"
		def user = new User(username: 'bobby', enabled: false, password: 'password', name: 'Bob', lastName: 'Bobby', points: 0)
		
		service.grailsApplication.config.game.points[domain][action] = points
		
		service.askForPoints(user, domain, action)
		
		assert user.points == 0
	}
	
	void testAskForPointsNotIntegerProperty() {
		def domain = "question"
		def action = "fakeProperty"
		def points = "notInteger"
		def user = new User(username: 'bobby', enabled: false, password: 'password', name: 'Bob', lastName: 'Bobby', points: 0)
		
		service.grailsApplication.config.game.points[domain]["create"] = points
		
		service.askForPoints(user, domain, action)
		
		assert user.points == 0
	}
}
