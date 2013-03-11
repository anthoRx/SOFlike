package org.isima



import org.junit.*

import grails.test.mixin.*
import java.sql.Timestamp


@TestFor(VersioningController)
@Mock([Versioning,User,Question])
class VersioningControllerTests {
	
	def testUser
	def testQ
	def testV
	
	@Before
	void setUp() {
		def date = new Timestamp(new Date().getTime())
		testUser = new User(username: 'bobby', enabled: false, password: 'password', name: 'Bobby', lastName: 'Bobby', points: 0).save(flush: true)
		testQ = new Question(title: 'Why 2 ?', nbView: 10, content: 'Why not too ?', creationDate: date, user: testUser).save(flush: true)
		testV = new Versioning(content: "old_content", modificationDate: date, interactionContent: testQ).save()
	}
	
    def populateValidParams(params) {
        assert params != null
		params["content"] = "content_test"
		params["modificationDate"] = new Timestamp(new Date().getTime())
    }

    void testIndex() {
        controller.index()
        assert "/versioning/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.versioningInstanceList.size() == 1
        assert model.versioningInstanceTotal == 1
    }


    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/versioning/list'

		assert Versioning.count() > 0

        params.id = testV.id

        def model = controller.show()

        assert model.versioningInstance == testV
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/versioning/list'

        response.reset()

        assert Versioning.count() == 1

        params.id = testV.id

        controller.delete()

        assert Versioning.count() == 0
        assert Versioning.get(testV.id) == null
        assert response.redirectedUrl == '/versioning/list'
    }
}
