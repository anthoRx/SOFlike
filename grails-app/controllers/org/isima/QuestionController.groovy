package org.isima

import java.sql.Timestamp

class QuestionController {

    def index() {
		def questions = Question.getAll()
		render(view: '/index.gsp', model:[questions: questions])
	}
	
	/**
	 * 
	 * @return
	 */
	def create() {
		def createdQuestion = new Question()
		createdQuestion.title = params.title
		createdQuestion.content = params.content
		createdQuestion.creationDate = new Timestamp(date.getTime())
		createdQuestion.nbView = 0
		
		createdQuestion.save();
	}
}
