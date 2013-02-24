package org.isima

import org.junit.Before
import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(GameService)
@Mock([User,Badge])
class GameServiceTests {
	
	GameService gameService
	User testUser
	
	@Before
	void setUp() {
		testUser = new User(username: 'bobby', enabled: false, password: 'password', name: 'Bob', lastName: 'Bobby', points: 0)
		// see the GRAILS-7506 issue for more information
		User.metaClass.isDirty = { 
			return true
		}
	}
	
    void testAskForPointsNominalCase() {
		def domain = "question"
		def action = "create"
		def points = 100		
		
		service.grailsApplication.config.game.points[domain][action] = points
		
        service.askForPoints(testUser, domain,action)
		
		assert testUser.points == 100
    }
	
	void testAskForPointsBadProperty() {
		def domain = "question"
		def action = "create"
		def points = "notInteger"
		
		service.grailsApplication.config.game.points[domain][action] = points
		
		service.askForPoints(testUser, domain, action)
		
		assert testUser.points == 0
	}
	
	void testAskForPointsNotIntegerProperty() {
		def domain = "question"
		def action = "fakeProperty"
		def points = "notInteger"
		
		service.grailsApplication.config.game.points[domain]["create"] = points
		
		service.askForPoints(testUser, domain, action)
		
		assert testUser.points == 0
	}
	
	void testAssignBadgesToUser() {
		testUser.badges = new ArrayList<Badge>();
		
		// Test with a user without points
		service.assignBadgesToUser(testUser)		
		assert testUser.badges.empty
		
		def badgeGuru = new Badge(name: "Guru", pointsToObtain: 10).save(flush: true)
		def badgeLegend = new Badge(name: "Legend", pointsToObtain: 100).save(flush: true)
		def badgeMe = new Badge(name: "Me", pointsToObtain: 1000).save(flush: true)
		
		testUser.points = 15
		service.assignBadgesToUser(testUser)
		assert testUser.badges.contains(badgeGuru)
		
		testUser.points = 400
		service.assignBadgesToUser(testUser)
		assert testUser.badges.contains(badgeLegend)	
		assert testUser.badges.size() == 2
		
		testUser.points = 1200
		service.assignBadgesToUser(testUser)
		assert testUser.badges.contains(badgeMe)	
		assert testUser.badges.size() == 3
	}
}
