package org.isima



import org.junit.*

import grails.test.mixin.*
import org.springframework.mock.web.MockMultipartFile
import org.springframework.mock.web.MockMultipartHttpServletRequest

@TestFor(UserController)
@Mock([User,Question,Answer])
class UserControllerTests {
	
	@Before
	void setUp() {
		// The method isDirty isn't mocked in Grails 2.1.1
		// So we add the method to the User's metaclass in order to execute
		// to execute tests on User domain class
		// see the GRAILS-7506 issue for more information
		User.metaClass.isDirty = { 
			return true
		}
	}
		
    def populateValidParams(params) {
        assert params != null

		params["username"] = "bobby"
		params["password"] = "pass"
		params["name"] = "Bobby"
		params["lastName"] = "Bob"
		params["points"] = "0"
		params["enabled"] = "false"
    }

    void testIndex() {
        controller.index()
        assert "/user/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.userInstanceList.size() == 0
        assert model.userInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.userInstance != null
    }

    void testSave() {
        controller.save()

        assert model.userInstance != null
        assert view == '/user/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/user/show/1'
        assert controller.flash.message != null
        assert User.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/user/list'

        populateValidParams(params)
        def user = new User(params)

        assert user.save() != null

        params.id = user.id

        def model = controller.show()

        assert model.userInstance == user
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/user/list'

        populateValidParams(params)
        def user = new User(params)

        assert user.save() != null

        params.id = user.id

        def model = controller.edit()

        assert model.userInstance == user
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/user/list'

        response.reset()

        populateValidParams(params)
        def user = new User(params)

        assert user.save() != null

        // test invalid parameters in update
        params.id = user.id
		params["lastName"] = 1234
		params["points"] = "Nothing"
		
		// Mock the image upload
		def imgContentType = 'image/jpeg'
		def imgContentBytes = '123' as byte[]
		controller.metaClass.request = new MockMultipartHttpServletRequest()
		controller.request.addFile(
			new MockMultipartFile('avatarFile', 'myImage.jpg', imgContentType, imgContentBytes)
		)
		
        controller.update()

        assert view == "/user/edit"
        assert model.userInstance != null

        user.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/user/show/$user.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        user.clearErrors()

        populateValidParams(params)
        params.id = user.id
        params.version = -1
        controller.update()

        assert view == "/user/edit"
        assert model.userInstance != null
        assert model.userInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/user/list'

        response.reset()

        populateValidParams(params)
        def user = new User(params)

        assert user.save() != null
        assert User.count() == 1

        params.id = user.id

        controller.delete()

        assert User.count() == 0
        assert User.get(user.id) == null
        assert response.redirectedUrl == '/user/list'
    }
	
	void testAvatarImage() {
		// Mock the image 
		def imgContentType = 'image/jpeg'
		def imgContentBytes = '123' as byte[]
		controller.metaClass.request = new MockMultipartHttpServletRequest()
		controller.request.addFile(
			new MockMultipartFile('avatarFile', 'myImage.jpg', imgContentType, imgContentBytes)
		)
		
		populateValidParams(params)
		controller.save()

		def usr = User.findByUsername("bobby")
		params["id"] = usr.id
		controller.avatar_image()
		
		assert response.getOutputStream() != null
	}
}
