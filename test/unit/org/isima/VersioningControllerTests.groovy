package org.isima



import org.junit.*
import grails.test.mixin.*

@TestFor(VersioningController)
@Mock(Versioning)
class VersioningControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/versioning/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.versioningInstanceList.size() == 0
        assert model.versioningInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.versioningInstance != null
    }

    void testSave() {
        controller.save()

        assert model.versioningInstance != null
        assert view == '/versioning/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/versioning/show/1'
        assert controller.flash.message != null
        assert Versioning.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/versioning/list'

        populateValidParams(params)
        def versioning = new Versioning(params)

        assert versioning.save() != null

        params.id = versioning.id

        def model = controller.show()

        assert model.versioningInstance == versioning
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/versioning/list'

        populateValidParams(params)
        def versioning = new Versioning(params)

        assert versioning.save() != null

        params.id = versioning.id

        def model = controller.edit()

        assert model.versioningInstance == versioning
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/versioning/list'

        response.reset()

        populateValidParams(params)
        def versioning = new Versioning(params)

        assert versioning.save() != null

        // test invalid parameters in update
        params.id = versioning.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/versioning/edit"
        assert model.versioningInstance != null

        versioning.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/versioning/show/$versioning.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        versioning.clearErrors()

        populateValidParams(params)
        params.id = versioning.id
        params.version = -1
        controller.update()

        assert view == "/versioning/edit"
        assert model.versioningInstance != null
        assert model.versioningInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/versioning/list'

        response.reset()

        populateValidParams(params)
        def versioning = new Versioning(params)

        assert versioning.save() != null
        assert Versioning.count() == 1

        params.id = versioning.id

        controller.delete()

        assert Versioning.count() == 0
        assert Versioning.get(versioning.id) == null
        assert response.redirectedUrl == '/versioning/list'
    }
}
