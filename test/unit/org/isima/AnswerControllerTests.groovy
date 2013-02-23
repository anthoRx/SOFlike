package org.isima



import org.junit.*

import grails.test.mixin.*
import java.sql.Timestamp

@TestFor(AnswerController)
@Mock([Answer,User,Question])
class AnswerControllerTests {
		
    def populateValidParams(params) {
        assert params != null
		
		def date = new Timestamp(new Date().getTime())
		def loggedInUser = new User(username: 'bobby', enabled: false, password: 'password', name: 'Bob', lastName: 'Bobby', points: 0);
		loggedInUser.save(flush: true)		
		def question = new Question(title: 'Should I buy a boat ??', nbView: 10, content: 'That is the question', creationDate: date)
		question.save(flush: true)
		
		params["content"] = "Yes of course"
		params["creationDate"] = date
		params["user"] = loggedInUser
		params["question"] = question
    }

    void testIndex() {
        controller.index()
        assert "/answer/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.answerInstanceList.size() == 0
        assert model.answerInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.answerInstance != null
    }

    void testSave() {
        controller.save()

        assert model.answerInstance != null
        assert view == '/answer/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/answer/show/1'
        assert controller.flash.message != null
        assert Answer.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/answer/list'

        populateValidParams(params)
        def answer = new Answer(params)

        assert answer.save() != null

        params.id = answer.id

        def model = controller.show()

        assert model.answerInstance == answer
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/answer/list'

        populateValidParams(params)
        def answer = new Answer(params)

        assert answer.save() != null

        params.id = answer.id

        def model = controller.edit()

        assert model.answerInstance == answer
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/answer/list'

        response.reset()

        populateValidParams(params)
        def answer = new Answer(params)

        assert answer.save() != null

        // test invalid parameters in update
        params.id = answer.id
		params["question"] = "This is not a valid question"

        controller.update()

        assert view == "/answer/edit"
        assert model.answerInstance != null

        answer.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/answer/show/$answer.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        answer.clearErrors()

        populateValidParams(params)
        params.id = answer.id
        params.version = -1
        controller.update()

        assert view == "/answer/edit"
        assert model.answerInstance != null
        assert model.answerInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/answer/list'

        response.reset()

        populateValidParams(params)
        def answer = new Answer(params)

        assert answer.save() != null
        assert Answer.count() == 1

        params.id = answer.id

        controller.delete()

        assert Answer.count() == 0
        assert Answer.get(answer.id) == null
        assert response.redirectedUrl == '/answer/list'
    }
}
