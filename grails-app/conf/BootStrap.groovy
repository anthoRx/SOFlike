import java.sql.Timestamp;

import org.isima.Answer
import org.isima.Role
import org.isima.Tag
import org.isima.User
import org.isima.UserRole
import org.isima.Question
import org.isima.Badge

class BootStrap {

    def init = { servletContext ->
		
		def date= new Date()
		
		// Define user and roles
		def adminRole = new Role(authority: 'ROLE_ADMIN').save(flush: true)
		def userRole = new Role(authority: 'ROLE_USER').save(flush: true)
		
		def testUser = new User(username: 'me', enabled: true, password: 'password', name: 'ME', lastName: 'Pierre')
		def testUser1 = new User(username: 'you', enabled: true, password: 'password', name: 'ME', lastName: 'Pierre')
		def testUser2 = new User(username: 'toonyGSI', enabled: true, password: 'password', name: 'ROUX', lastName: 'Anthony')
		def testUser3 = new User(username: 'pfr63', enabled: true, password: 'password', name: 'RIGODIAT', lastName: 'Pierre')
		def testUser4 = new User(username: 'pingu', enabled: true, password: 'password', name: 'BRUNO', lastName: 'Quentin')
		
		testUser.save(flush: true)
		testUser1.save(flush: true)
		testUser2.save(flush: true)
		testUser3.save(flush: true)
		testUser4.save(flush: true)
		
		
		// Define badges
		def b = new Badge(name: "Bobby", pointsToObtain: 10);
		def b2 = new Badge(name: "Guru", pointsToObtain: 100);
		def b3 = new Badge(name: "Van dame", pointsToObtain: 1000);
		
		// Define questions and answers
		def testQ1 = new Question(title: 'Why ?', nbView: 0, content: 'I have a bunch of threads that generate events of type A and type B.My program takes these events, wraps them in a message and sends them across the network. A message can hold either one A event, one B event, or one A event and one B event:\n endMessage(new Message(a: 1,    b: null));\n SendMessage(new Message(a: null, b: 2   ));\n SendMessage(new Message(a: 3,    b: 4   ));\n Events of type A happen quite frequently, while events of type B occur much less often. So, when a thread generates a B event, my program waits a bit to see if another thread generates an A event and combines the A event and the B event if possible.\n Here is my code:', creationDate: date)
		testQ1.save();		
		def testQ2 = new Question(title: 'Why 2 ?', nbView: 10, content: 'Why not too ?', creationDate: date)
		testQ2.save();	
		def testQ3 = new Question(title: 'Should I buy a boat ?', nbView: 2, content: 'Help I am not sure', creationDate: date)
		testQ3.save();
		def a1 = new Answer(content: 'I don t care f*****g cat', date: date, question: testQ3)
		a1.save(flush: true)
		def a2 = new Answer(content: 'Buy a car, I think it s better', date: date, question: testQ3)
		a2.save(flush: true)
		
		testUser.addToBadges(b).save()
		testUser.addToBadges(b2).save()
		testUser.addToInteractionContents(testQ1).save()
		testUser.addToInteractionContents(testQ2).save()
		testUser.addToInteractionContents(testQ3).save()
		testUser3.addToInteractionContents(a1).save()
		testUser4.addToInteractionContents(a2).save()
		
		//Define tags
		def t1 = new Tag(name: "Boat")
		def t2 = new Tag(name: "cat")
		def t3 = new Tag(name: "rien")
		def t4 = new Tag(name: "ouaich")
		testQ1.addToTags(t4).save()
		testQ2.addToTags(t3).save()
		testQ3.addToTags(t1).save()
		testQ3.addToTags(t2).save()
		
		UserRole.create testUser, adminRole, true
		UserRole.create testUser1, userRole, true
		UserRole.create testUser2, adminRole, true
		UserRole.create testUser3, adminRole, true
		UserRole.create testUser4, userRole, true
		
		assert User.count() == 5
		assert Role.count() == 2
		assert UserRole.count() == 5
    }
	
    def destroy = {
    }
}
