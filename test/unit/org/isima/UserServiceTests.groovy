package org.isima



import grails.test.mixin.*

import java.security.Provider.Service;
import java.sql.Timestamp
import org.junit.*
import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(UserService)
@Mock([Question,User])
class UserServiceTests {
	
	def testUser
	def testUser2
	def testUser3
	def testQ
	
	@Before
	void setUp() {
		def date = new Timestamp(new Date().getTime())
				
		User.metaClass.isDirty = { return true }
		
		testUser = new User(username: 'bobby', enabled: false, password: 'password', name: 'Bobby', lastName: 'Bobby', points: 0).save(flush: true)
		testUser2 = new User(username: 'bob', enabled: false, password: 'password', name: 'Bob', lastName: 'bob', points: 0).save(flush: true)
		testUser3 = new User(username: 'boba', enabled: false, password: 'password', name: 'Boba', lastName: 'boba', points: 0).save(flush: true)
		testQ = new Question(title: 'Why 2 ?', nbView: 10, content: 'Why not too ?', creationDate: date, user: testUser).save(flush: true)
		
		testUser.interactionContents = new ArrayList<InteractionContent>()
		testUser.interactionContents.add(testQ)
		testUser.save()
		
		// We mock the springSecurityService
		service.springSecurityService = [
			isLoggedIn: {return true},
			getCurrentUser: {return testUser}]
		
		SpringSecurityUtils.metaClass.'static'.ifAnyGranted = { String role -> return true }
	}
	
    void testHasPermission() {
		// Nominal case, user is admin and the owner of the question
		def result = service.hasPermission(testQ)   
		assert result
		
		// Case where user is just admin
		service.springSecurityService = [
			isLoggedIn: {return true},
			getCurrentUser: {return testUser2}]
		result = service.hasPermission(testQ)
		assert result
		
		// Case where user is nothing
		service.springSecurityService = [
			isLoggedIn: {return true},
			getCurrentUser: {return testUser3}]
		SpringSecurityUtils.metaClass.'static'.ifAnyGranted = { String role -> return false }
		result = service.hasPermission(testQ)
		assert result == false
		
		// Case where user is just the owner
		service.springSecurityService = [
			isLoggedIn: {return true},
			getCurrentUser: {return testUser}]
		SpringSecurityUtils.metaClass.'static'.ifAnyGranted = { String role -> return false }
		result = service.hasPermission(testQ)
		assert result 
    }
}
