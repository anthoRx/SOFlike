package org.isima

import grails.test.mixin.Mock
import grails.test.mixin.domain.DomainClassUnitTestMixin;

import java.sql.Timestamp
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(QuestionController)
@Mock(Question)
class QuestionControllerTests {

    void testIndex() {
		Date date= new Date()
		Timestamp currentTime = new Timestamp(date.getTime())
		
		mockDomain(Question,[new Question(title: 'q1', nbView: 0, content: 'q1_content?', creationDate: currentTime),
							new Question(title: 'q2', nbView: 0, content: 'q2_content?', creationDate: currentTime)])
		
		def model = this.controller.index()
		//assertEquals 'q1', model.questions.getAt(0).title
		assert view == "/index.gsp"
    }
}
