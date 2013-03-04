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
		question.versionings = new HashSet<Versioning>()
		question.save(flush: true)		
		
		def vers = service.versionContent(question,oldContent)
		
		assert question.versionings.size() == 1
		assert question.versionings.contains(vers) == true
		
		vers = service.versionContent(question,oldContent2)
		
		assert question.versionings.size() == 2
		assert question.versionings.contains(vers) == true
		
		vers = service.versionContent(question,null)
		vers = service.versionContent(question,"")
		
		assert question.versionings.size() == 2
    }
}
