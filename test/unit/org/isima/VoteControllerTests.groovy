package org.isima



import org.junit.*
import grails.test.mixin.*
import java.sql.Timestamp

@TestFor(VoteController)
@Mock([Vote,Question,User])
class VoteControllerTests {

    def populateValidParams(params) {
        assert params != null
		def date = new Timestamp(new Date().getTime())
		def loggedInUser = new User(username: 'bobby', enabled: false, password: 'password', name: 'Bob', lastName: 'Bobby', points: 0);
		loggedInUser.save(flush: true)
		def question = new Question(title: 'Should I buy a boat ??', nbView: 10, content: 'That is the question', creationDate: date)
		question.save(flush: true)
		
        params["value"] = 1
		params["user"] = loggedInUser
		params["interactionContent"] = question
    }

    void testIndex() {
        controller.index()
        assert "/vote/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.voteInstanceList.size() == 0
        assert model.voteInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.voteInstance != null
    }

    void testSave() {
        controller.save()

        assert model.voteInstance != null
        assert view == '/vote/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/vote/show/1'
        assert controller.flash.message != null
        assert Vote.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/vote/list'

        populateValidParams(params)
        def vote = new Vote(params)

        assert vote.save() != null

        params.id = vote.id

        def model = controller.show()

        assert model.voteInstance == vote
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/vote/list'

        populateValidParams(params)
        def vote = new Vote(params)

        assert vote.save() != null

        params.id = vote.id

        def model = controller.edit()

        assert model.voteInstance == vote
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/vote/list'

        response.reset()

        populateValidParams(params)
        def vote = new Vote(params)

        assert vote.save() != null

        // test invalid parameters in update
        params.id = vote.id
        params.value = "Bad value"

        controller.update()

        assert view == "/vote/edit"
        assert model.voteInstance != null

        vote.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/vote/show/$vote.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        vote.clearErrors()

        populateValidParams(params)
        params.id = vote.id
        params.version = -1
        controller.update()

        assert view == "/vote/edit"
        assert model.voteInstance != null
        assert model.voteInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/vote/list'

        response.reset()

        populateValidParams(params)
        def vote = new Vote(params)

        assert vote.save() != null
        assert Vote.count() == 1

        params.id = vote.id

        controller.delete()

        assert Vote.count() == 0
        assert Vote.get(vote.id) == null
        assert response.redirectedUrl == '/vote/list'
    }
}
