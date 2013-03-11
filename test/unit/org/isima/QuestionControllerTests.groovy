package org.isima



import org.junit.*
import grails.test.mixin.*
import java.sql.Timestamp

@TestFor(QuestionController)
@Mock([Question,User])
class QuestionControllerTests {
	
	@Before
	void setUp() {
		// We save the user in order to have an id
		def loggedInUser = new User(username: 'bobby', enabled: false, password: 'password', name: 'Bob', lastName: 'Bobby', points: 0);
		loggedInUser.save(flush: true)
		
		// We mock the springSecurityService
		controller.springSecurityService = [
			encodePassword: 'password',
			reauthenticate: { String u -> true},
			loggedIn: true,
			currentUser: loggedInUser]
		
		def mockQuestionService = mockFor(QuestionService)
		mockQuestionService.demand.incrementNbView(0..1) {Question object -> return true}
		mockQuestionService.demand.update(0..2) {Question object, String oldContent -> return true}
		mockQuestionService.demand.create(0..1) {Question object -> return true}
		controller.questionService = mockQuestionService.createMock()

		controller.userService = [hasPermission: {instance -> true}]		
	}
	
    def populateValidParams(params) {
        assert params != null
		params["title"] = "Should I buy a boat ?"
		params["content"] = "I'm not sure if it's a good idea"
		params["nbView"] = 0
		params["creationDate"] = new Timestamp(new Date().getTime())
		params["user"] = controller.springSecurityService.currentUser
    }

    void testIndex() {
        controller.index()
        assert "/question/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.questionInstanceList.size() == 0
        assert model.questionInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.questionInstance != null
    }

    void testSave() {
		
        controller.save()

        assert model.questionInstance != null
        assert view == '/question/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/question/show/1'
        assert controller.flash.message != null
        assert Question.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/question/list'

        populateValidParams(params)
        def question = new Question(params)
		
        assert question.save() != null

        params.id = question.id

        def model = controller.show()

        assert model.questionInstance == question
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/question/list'

        populateValidParams(params)
        def question = new Question(params)

        assert question.save() != null

        params.id = question.id

        def model = controller.edit()

        assert model.questionInstance == question
    }

    void testUpdate() {		
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/question/list'

        response.reset()

        populateValidParams(params)
        def question = new Question(params)

        assert question.save() != null

        // test invalid parameters in update
        params.id = question.id
        params.nbView = "oo"

        controller.update()

        assert view == "/question/edit"
        assert model.questionInstance != null

        question.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/question/show/$question.id"
        assert flash.message != null		

        //test outdated version number
        response.reset()
        question.clearErrors()

        populateValidParams(params)
        params.id = question.id
        params.version = -1
        controller.update()

        assert view == "/question/edit"
        assert model.questionInstance != null
        assert model.questionInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/question/list'

        response.reset()

        populateValidParams(params)
        def question = new Question(params)

        assert question.save() != null
        assert Question.count() == 1

        params.id = question.id

        controller.delete()

        assert Question.count() == 0
        assert Question.get(question.id) == null
        assert response.redirectedUrl == '/question/list'
    }
}
