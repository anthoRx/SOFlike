package org.isima

import org.springframework.dao.DataIntegrityViolationException
import grails.plugins.springsecurity.Secured

class AnswerController {

    static allowedMethods = [save: "POST", update: "POST", delete: ["POST","GET"]]
	def springSecurityService
	def answerService
	def userService

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [answerInstanceList: Answer.list(params), answerInstanceTotal: Answer.count()]
    }
		
	
	@Secured(['ROLE_USER','ROLE_ADMIN'])
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
            flash.message ="The answer wasn't be created"
			render(template: "answersByQuestion", model: [questionInstance: questionInstance])
			return
		}
		
		answerService.create(answerInstance)
		
		render(template: "answersByQuestion", model: [questionInstance: questionInstance])
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

	/**
	 * 
	 * @param id
	 * @return
	 */
    def edit(Long id) {
        def answerInstance = Answer.get(id)
		print answerInstance
		// Check if the user can update the question (only for the owner or an admin)
		if(!userService.hasPermission(answerInstance)) {
			flash.message = message(code: 'security.not.authorized')
			redirect(action: "list")
			return
		}
		
        if (!answerInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'answer.label', default: 'Answer'), id])
            redirect(action: "list")
            return
        }

        [answerInstance: answerInstance]
    }

	/**
	 * 
	 * @param id
	 * @param version
	 * @return
	 */
    def update(Long id, Long version) {
        def answerInstance = Answer.get(id)
		
		// Check if the user can update the question (only for the owner or an admin)
		if(!userService.hasPermission(answerInstance)) {
			flash.message = message(code: 'security.not.authorized')
			redirect(action: "list")
			return
		}
		
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
		
		// We keep the current content before try to save it
		def oldContent = answerInstance.content
		
        answerInstance.properties = params

        if (!answerInstance.save(flush: true)) {
            render(view: "edit", model: [answerInstance: answerInstance])
            return
        }
		
		// Save the old content with the versionning feature
		answerService.update(answerInstance,oldContent)

        flash.message = message(code: 'default.updated.message', args: [message(code: 'answer.label', default: 'Answer'), answerInstance.id])
        redirect(controller: "question", action: "show", id: answerInstance.question.id)
    }
	
	/**
	 * 
	 * @param id
	 * @return
	 */
    def delete(Long id) {
        def answerInstance = Answer.get(id)
		
		// Check if the user can update the question (only for the owner or an admin)
		if(!userService.hasPermission(answerInstance)) {
			flash.message = message(code: 'security.not.authorized')
			redirect(action: "list")
			return
		}
		
        if (!answerInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'answer.label', default: 'Answer'), id])
            redirect(action: "list")
            return
        }

        try {
			def qId = answerInstance.question.id
            answerInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'answer.label', default: 'Answer'), id])
            redirect(controller: "question", action: "show", id: qId)
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'answer.label', default: 'Answer'), id])
            redirect(action: "show", id: id)
        }
    }
}
