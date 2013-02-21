package org.isima



import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(GameService)
class GameServiceTests {
	
	GameService gameService
	
    void testSomething() {
		def conf ="vote"
		service.grailsApplication.config.game[conf] = 'test my conf'
        service.askForPoints(new User(username: 'me', enabled: true, password: 'password', name: 'ME', lastName: 'Pierre'), conf)
    }
}
