package org.isima



import grails.test.mixin.*
import java.sql.Timestamp
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(VersioningService)
@Mock([Versioning,Question])
class VersioningServiceTests {

    void testVersionContent() {		
		def oldContent = "Old content"
		def oldContent2 = "Old content 2"
		def date = new Timestamp(new Date().getTime())
			
		def question = new Question(title: 'Should I buy a boat ??', nbView: 10, content: 'That is the question', creationDate: date)
		question.save(flush: true)		
		
		def vers = service.versionContent(question,oldContent)
		
		assert Versioning.count == 1
		
		vers = service.versionContent(question,oldContent2)		
		
		assert Versioning.count == 2
		
		vers = service.versionContent(question,null)
		vers = service.versionContent(question,"")
		
		assert Versioning.count == 2
    }
}
