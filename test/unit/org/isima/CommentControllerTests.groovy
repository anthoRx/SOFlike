package org.isima



import org.junit.*

import grails.test.mixin.*
import java.sql.Timestamp

@TestFor(CommentController)
@Mock([Comment,Answer,User,Question])
class CommentControllerTests {
		
    def populateValidParams(params) {
		def date = new Timestamp(new Date().getTime())
		def question = new Question(title: 'Should I buy a boat ??', nbView: 10, content: 'That is the question', creationDate: date)
		question.save(flush: true)
		
        assert params != null
		params["content"] = "my comment"
		params["creationDate"] = date
		params["ieId"] = "my comment"
		params["question"] = question
    }

    void testIndex() {
        controller.index()
        assert "/comment/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.commentInstanceList.size() == 0
        assert model.commentInstanceTotal == 0
    }

	/**
    void testSaveInShow() {
        controller.saveInShow()

        assert model.ieInstance == null
        assert Comment.count() == 0

        response.reset()

        populateValidParams(params)
        controller.saveInShow()
		
        assert Comment.count() == 1
    }
	

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/comment/list'

        populateValidParams(params)
        def comment = new Comment(params)

        assert comment.save() != null

        params.id = comment.id

        def model = controller.show()

        assert model.commentInstance == comment
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/comment/list'

        populateValidParams(params)
        def comment = new Comment(params)

        assert comment.save() != null

        params.id = comment.id

        def model = controller.edit()

        assert model.commentInstance == comment
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/comment/list'

        response.reset()

        populateValidParams(params)
        def comment = new Comment(params)

        assert comment.save() != null

        // test invalid parameters in update
        params.id = comment.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/comment/edit"
        assert model.commentInstance != null

        comment.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/comment/show/$comment.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        comment.clearErrors()

        populateValidParams(params)
        params.id = comment.id
        params.version = -1
        controller.update()

        assert view == "/comment/edit"
        assert model.commentInstance != null
        assert model.commentInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/comment/list'

        response.reset()

        populateValidParams(params)
        def comment = new Comment(params)

        assert comment.save() != null
        assert Comment.count() == 1

        params.id = comment.id

        controller.delete()

        assert Comment.count() == 0
        assert Comment.get(comment.id) == null
        assert response.redirectedUrl == '/comment/list'
    }
    */
}
