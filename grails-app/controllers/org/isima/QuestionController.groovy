package org.isima

class QuestionController {

    def index() {
		def questions = Question.getAll()
		render(view: '/index.gsp', model:[questions: questions])
	}
	
	
}
