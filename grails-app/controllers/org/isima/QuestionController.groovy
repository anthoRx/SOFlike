package org.isima

import java.security.Security;
import java.sql.Timestamp
import org.springframework.dao.DataIntegrityViolationException
import grails.plugins.springsecurity.Secured



class QuestionController {

    static allowedMethods = [save: "POST", update: "POST", delete: ["POST","GET"]]
	
	def springSecurityService
	def userService
	def questionService
	
    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [questionInstanceList: Question.list(params), questionInstanceTotal: Question.count()]
    }
	
	def listUnAnswered(Integer max) {
		params.max = Math.min(max ?: 10, 100)
		def questionInstanceList = Question.list(params)
		questionInstanceList = questionInstanceList.findAll { e -> e.answers.size() == 0 }
		
		render(view: "list", model: [questionInstanceList: questionInstanceList, questionInstanceTotal: questionInstanceList.size()])
	}
	
	/**
	 * 
	 * @return
	 */
	@Secured(['ROLE_USER','ROLE_ADMIN'])
    def create() {
        [questionInstance: new Question(params)]
    }
	
	/**
	 * 
	 * @return
	 */
	@Secured(['ROLE_USER','ROLE_ADMIN'])
    def save() {
        def questionInstance = new Question(params)
		def date= new Date()
		questionInstance.creationDate = new Timestamp(date.getTime())
		
		if(!params.user) {
			def user = springSecurityService.currentUser
			questionInstance.user = user
		}
		
        if (!questionInstance.save(flush: true)) {
            render(view: "create", model: [questionInstance: questionInstance])
            return
        }
		
		questionService.create(questionInstance)
		
        flash.message = message(code: 'default.created.message', args: [message(code: 'question.label', default: 'Question'), questionInstance.id])
        redirect(action: "show", id: questionInstance.id)
    }
	
	/**
	 * 
	 * @param id
	 * @return
	 */
    def show(Long id) {
        def questionInstance = Question.get(id)
		//Sort the answers
		
        if (!questionInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'question.label', default: 'Question'), id])
            redirect(action: "list")
            return
        }
		questionService.incrementNbView(questionInstance)
        [questionInstance: questionInstance ]
    }

	/**
	 * 
	 * @param id
	 * @return
	 */
    def edit(Long id) {
        def questionInstance = Question.get(id)
		
		// Check if the user can update the question (only for the owner or an admin)
		if(!userService.hasPermission(questionInstance)) {
			flash.message = message(code: 'security.not.authorized')
			redirect(action: "list")
			return
		}
		
        if (!questionInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'question.label', default: 'Question'), id])
            redirect(action: "list")
            return
        }

        [questionInstance: questionInstance]
    }

    def update(Long id, Long version) {
        def questionInstance = Question.get(id)
		
		// Check if the user can update the question (only for the owner or an admin)
		if(!userService.hasPermission(questionInstance)) {
			flash.message = message(code: 'security.not.authorized')
			redirect(action: "list")
			return 
		}
		
        if (!questionInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'question.label', default: 'Question'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (questionInstance.version > version) {
                questionInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'question.label', default: 'Question')] as Object[],
                          "Another user has updated this Question while you were editing")
                render(view: "edit", model: [questionInstance: questionInstance])
                return
            }
        }
		
		// We keep the current content before try to save it
		def oldContent = questionInstance.content
		
        questionInstance.properties = params
		
        if (!questionInstance.save(flush: true)) {
            render(view: "edit", model: [questionInstance: questionInstance])
            return
        }
		
		// Save the old content with the versionning feature
		questionService.update(questionInstance,oldContent)
		
        flash.message = message(code: 'default.updated.message', args: [message(code: 'question.label', default: 'Question'), questionInstance.id])
        redirect(action: "show", id: questionInstance.id)
    }

    def delete(Long id) {
        def questionInstance = Question.get(id)
		
		// Check if the user can delete the question (only for the owner or an admin)
		if(!userService.hasPermission(questionInstance)) {
			flash.message = message(code: 'security.not.authorized')
			redirect(action: "list")
			return
		}
		
        if (!questionInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'question.label', default: 'Question'), id])
            redirect(action: "list")
            return
        }
		
        try {
            questionInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'question.label', default: 'Question'), id])
            forward action: "list"
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'question.label', default: 'Question'), id])
            redirect(action: "show", id: id)
        }
    }
}
