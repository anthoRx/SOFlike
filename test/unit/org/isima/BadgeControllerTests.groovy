package org.isima


import org.junit.Before;
import org.junit.*
import grails.test.mixin.*

@TestFor(BadgeController)
@Mock(Badge)
class BadgeControllerTests {
	
	@Before
	void setUp() {		
		def mockGameService = mockFor(GameService)
		mockGameService.demand.updateBadgeAttribution(0..1) {Badge object -> return true}
		controller.gameService = mockGameService.createMock()
	}
	
    def populateValidParams(params) {
        assert params != null
        params["name"] = 'someValidName'
        params["pointsToObtain"] = 30
    }

    void testIndex() {
        controller.index()
        assert "/badge/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.badgeInstanceList.size() == 0
        assert model.badgeInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.badgeInstance != null
    }

    void testSave() {
        controller.save()

        assert model.badgeInstance != null
        assert view == '/badge/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/badge/show/1'
        assert controller.flash.message != null
        assert Badge.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/badge/list'

        populateValidParams(params)
        def badge = new Badge(params)

        assert badge.save() != null

        params.id = badge.id

        def model = controller.show()

        assert model.badgeInstance == badge
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/badge/list'

        populateValidParams(params)
        def badge = new Badge(params)

        assert badge.save() != null

        params.id = badge.id

        def model = controller.edit()

        assert model.badgeInstance == badge
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/badge/list'

        response.reset()

        populateValidParams(params)
        def badge = new Badge(params)

        assert badge.save() != null

        // test invalid parameters in update
        params.id = badge.id
        params.pointsToObtain = "Bad value"

        controller.update()

        assert view == "/badge/edit"
        assert model.badgeInstance != null

        badge.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/badge/show/$badge.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        badge.clearErrors()

        populateValidParams(params)
        params.id = badge.id
        params.version = -1
        controller.update()

        assert view == "/badge/edit"
        assert model.badgeInstance != null
        assert model.badgeInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/badge/list'

        response.reset()

        populateValidParams(params)
        def badge = new Badge(params)

        assert badge.save() != null
        assert Badge.count() == 1

        params.id = badge.id

        controller.delete()

        assert Badge.count() == 0
        assert Badge.get(badge.id) == null
        assert response.redirectedUrl == '/badge/list'
    }
}
