package org.isima

import org.springframework.dao.DataIntegrityViolationException

class AnswerController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
	def springSecurityService

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [answerInstanceList: Answer.list(params), answerInstanceTotal: Answer.count()]
    }

    def create() {
        [answerInstance: new Answer(params)]
    }
	
	def createInShow() {
		[answerInstance: new Answer(params)]
	}
	
	def saveInShow() {
		def answerInstance = new Answer(params)
		//We retrieve the user
		def user = springSecurityService.currentUser
		answerInstance.user = user
		//We set the content. Error with richui if samename for field (question, answer)
		answerInstance.content = params.contentAnswer
		//We retrieve the question
		def questionInstance = params.questionInstance
		questionInstance = Question.get(questionInstance);
		//We inform the question
		answerInstance.question = questionInstance
		//We set the date
		answerInstance.creationDate = new Date()
		
		if (!answerInstance.save(flush: true)) {
			render(view: "create", model: [answerInstance: answerInstance])
			return
		}

		render(template: "answersByQuestion", model: [questionInstance: questionInstance])
	}

    def save() {
        def answerInstance = new Answer(params)
        if (!answerInstance.save(flush: true)) {
            render(view: "create", model: [answerInstance: answerInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'answer.label', default: 'Answer'), answerInstance.id])
        redirect(action: "show", id: answerInstance.id)
    }

    def show(Long id) {
        def answerInstance = Answer.get(id)
        if (!answerInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'answer.label', default: 'Answer'), id])
            redirect(action: "list")
            return
        }

        [answerInstance: answerInstance]
    }

    def edit(Long id) {
        def answerInstance = Answer.get(id)
        if (!answerInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'answer.label', default: 'Answer'), id])
            redirect(action: "list")
            return
        }

        [answerInstance: answerInstance]
    }

    def update(Long id, Long version) {
        def answerInstance = Answer.get(id)
        if (!answerInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'answer.label', default: 'Answer'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (answerInstance.version > version) {
                answerInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'answer.label', default: 'Answer')] as Object[],
                          "Another user has updated this Answer while you were editing")
                render(view: "edit", model: [answerInstance: answerInstance])
                return
            }
        }

        answerInstance.properties = params

        if (!answerInstance.save(flush: true)) {
            render(view: "edit", model: [answerInstance: answerInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'answer.label', default: 'Answer'), answerInstance.id])
        redirect(action: "show", id: answerInstance.id)
    }

    def delete(Long id) {
        def answerInstance = Answer.get(id)
        if (!answerInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'answer.label', default: 'Answer'), id])
            redirect(action: "list")
            return
        }

        try {
            answerInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'answer.label', default: 'Answer'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'answer.label', default: 'Answer'), id])
            redirect(action: "show", id: id)
        }
    }
}
