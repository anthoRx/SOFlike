import java.sql.Timestamp;

import org.isima.Role
import org.isima.User
import org.isima.UserRole
import org.isima.Question
import org.isima.Badge

class BootStrap {

    def init = { servletContext ->
		def adminRole = new Role(authority: 'ROLE_ADMIN').save(flush: true)
		def userRole = new Role(authority: 'ROLE_USER').save(flush: true)
  
		User testUser = new User(username: 'me', enabled: true, password: 'password', name: 'ME', lastName: 'Pierre')
		User testUser1 = new User(username: 'you', enabled: true, password: 'password', name: 'ME', lastName: 'Pierre')
		User testUser2 = new User(username: 'toonyGSI', enabled: true, password: 'password', name: 'ROUX', lastName: 'Anthony')
		User testUser3 = new User(username: 'pfr63', enabled: true, password: 'password', name: 'RIGODIAT', lastName: 'Pierre')
		User testUser4 = new User(username: 'pingu', enabled: true, password: 'password', name: 'BRUNO', lastName: 'Quentin')
		User testUser5 = new User(username: 'toonette', enabled: true, password: 'password', name: 'Pradier', lastName: 'Amandine')
		
		testUser.save(flush: true)
		testUser1.save(flush: true)
		testUser2.save(flush: true)
		testUser3.save(flush: true)
		testUser4.save(flush: true)
		testUser5.save(flush: true)
		
		Badge b = new Badge();
		b.name = "Badge1"
  
		java.util.Date date= new java.util.Date()
		Question testQ1 = new Question(title: 'Why ?', nbView: 0, content: 'Why not ?', creationDate: new Timestamp(date.getTime()))
		testQ1.save();
		
		Question testQ2 = new Question(title: 'Why 2 ?', nbView: 10, content: 'Why not too ?', creationDate: new Timestamp(date.getTime()))
		testQ2.save();
			
		testUser.addToBadges(b).save()
		testUser.addToInteractionContents(testQ1).save()
		testUser.addToInteractionContents(testQ2).save()
		
		UserRole.create testUser, adminRole, true
		UserRole.create testUser1, userRole, true
		UserRole.create testUser2, adminRole, true
		UserRole.create testUser3, adminRole, true
		UserRole.create testUser4, userRole, true
		UserRole.create testUser5, userRole, true
		
		assert User.count() == 6
		assert Role.count() == 2
		assert UserRole.count() == 6
    }
	
    def destroy = {
    }
}
