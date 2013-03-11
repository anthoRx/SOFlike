package org.isima

import org.springframework.dao.DataIntegrityViolationException
import grails.plugins.springsecurity.Secured

class CommentController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [commentInstanceList: Comment.list(params), commentInstanceTotal: Comment.count()]
    }
	
	
	@Secured(['ROLE_USER','ROLE_ADMIN'])
	def saveInShow() {
		def commentInstance = new Comment(params)
		//We set the date
		commentInstance.creationDate = new Date()
		
		//We retrieve the interaction content
		def ieInstance = null
		if(params.ieId)
			ieInstance = InteractionContent.findById(params.ieId);
		commentInstance.interactionContent = ieInstance
		
		if (!commentInstance.save(flush: true)) {
            flash.message ="The comment wasn't be created"
			render(template: "commentsByIE", model: [ieInstance: ieInstance])
			return
		}

		render(template: "commentsByIE", model: [ieInstance: ieInstance])
	}

    def show(Long id) {
        def commentInstance = Comment.get(id)
        if (!commentInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'comment.label', default: 'Comment'), id])
            redirect(action: "list")
            return
        }

        [commentInstance: commentInstance]
    }
	
	@Secured(['ROLE_ADMIN'])
    def edit(Long id) {
        def commentInstance = Comment.get(id)
        if (!commentInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'comment.label', default: 'Comment'), id])
            redirect(action: "list")
            return
        }

        [commentInstance: commentInstance]
    }
	
	@Secured(['ROLE_ADMIN'])
    def update(Long id, Long version) {
        def commentInstance = Comment.get(id)
        if (!commentInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'comment.label', default: 'Comment'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (commentInstance.version > version) {
                commentInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'comment.label', default: 'Comment')] as Object[],
                          "Another user has updated this Comment while you were editing")
                render(view: "edit", model: [commentInstance: commentInstance])
                return
            }
        }

        commentInstance.properties = params

        if (!commentInstance.save(flush: true)) {
            render(view: "edit", model: [commentInstance: commentInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'comment.label', default: 'Comment'), commentInstance.id])
        redirect(action: "show", id: commentInstance.id)
    }
	
	@Secured(['ROLE_ADMIN'])
    def delete(Long id) {
        def commentInstance = Comment.get(id)
        if (!commentInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'comment.label', default: 'Comment'), id])
            redirect(action: "list")
            return
        }

        try {
            commentInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'comment.label', default: 'Comment'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'comment.label', default: 'Comment'), id])
            redirect(action: "show", id: id)
        }
    }
}
