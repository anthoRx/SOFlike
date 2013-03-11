package org.isima



import org.junit.*

import grails.test.mixin.*
import java.sql.Timestamp

@TestFor(VoteController)
@Mock([Vote,Question,User])
class VoteControllerTests {
	
	def loggedInUser
	
	@Before
	void setUp() {
		// We save the user in order to have an id
		loggedInUser = new User(username: 'bobby', enabled: false, password: 'password', name: 'Bob', lastName: 'Bobby', points: 0);
		loggedInUser.save(flush: true)
		
		// We mock the springSecurityService
		controller.springSecurityService = [
			encodePassword: 'password',
			reauthenticate: { String u -> true},
			loggedIn: true,
			currentUser: loggedInUser]
		
		def mockVoteService = mockFor(VoteService)
		mockVoteService.demand.upVote(0..1) {Vote object -> return true}
		mockVoteService.demand.downVote(0..1) {Vote object -> return true}
		controller.voteService = mockVoteService.createMock()
	}
	
    def populateValidParams(params) {
        assert params != null
		def date = new Timestamp(new Date().getTime())
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

    void testSaveInShow() {
        controller.saveInShow()

        assert model.voteInstance != null
        assert view == '/vote/create'

        response.reset()

        populateValidParams(params)
        controller.saveInShow()

        assert controller.flash.message != null
        assert Vote.count() == 1
		
        controller.saveInShow()

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
