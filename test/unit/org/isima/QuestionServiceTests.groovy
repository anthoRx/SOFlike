package org.isima



import grails.test.mixin.*
import java.sql.Timestamp
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(QuestionService)
@Mock([Question])
class QuestionServiceTests {
	
	def question
	def testUser
	
	@Before
	void setUp() {
		def date = new Timestamp(new Date().getTime())
		testUser = new User(username: 'bobby', enabled: false, password: 'password', name: 'Bob', lastName: 'Bobby', points: 0)
		question = new Question(title: 'Should I buy a boat ??', nbView: 0, content: 'That is the question', creationDate: date)
		question.save(flush: true)
		def mockGameService = mockFor(QuestionService)
		mockGameService.demand.askForPoints(0..3) {User usr, String a, String b -> return true}
		service.gameService = mockGameService.createMock()
	}
	
    void testIncrementNbView() {
        assert question.nbView == 0
		
		service.incrementNbView(question)
        assert question.nbView == 1
		
		service.incrementNbView(question)
        assert question.nbView == 2
    }
}
