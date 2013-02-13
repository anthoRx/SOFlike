import java.sql.Timestamp;

import org.isima.Role
import org.isima.User
import org.isima.UserRole
import org.isima.Question

class BootStrap {

    def init = { servletContext ->
		def adminRole = new Role(authority: 'ROLE_ADMIN').save(flush: true)
		def userRole = new Role(authority: 'ROLE_USER').save(flush: true)
  
		def testUser = new User(username: 'me', enabled: true, password: 'password')
		testUser.save(flush: true)
  
		UserRole.create testUser, adminRole, true
		
		java.util.Date date= new java.util.Date()
		def testQ1 = new Question(title: 'Why ?', nbView: 0, content: 'Why not ?', creationDate: new Timestamp(date.getTime()))
		testQ1.save();
		
		assert User.count() == 1
		assert Role.count() == 2
		assert UserRole.count() == 1
    }
	
    def destroy = {
    }
}
