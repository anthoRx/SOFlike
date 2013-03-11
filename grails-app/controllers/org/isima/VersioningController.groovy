package org.isima

import org.springframework.dao.DataIntegrityViolationException
import grails.plugins.springsecurity.Secured

class VersioningController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

	@Secured(['ROLE_USER','ROLE_ADMIN'])
    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [versioningInstanceList: Versioning.list(params), versioningInstanceTotal: Versioning.count()]
    }
	
	@Secured(['ROLE_USER','ROLE_ADMIN'])
	def listByQuestion(Long id) {
        def questionInstance = Question.get(id)
		
        if (!questionInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'question.label', default: 'Question'), id])
            redirect(action: "list")
            return
        }
        render(view:"list", model: [versioningInstanceList: questionInstance.versionings, questionInstance: questionInstance, 
											versioningInstanceTotal: questionInstance.versionings.count])		
	}
	
	@Secured(['ROLE_USER','ROLE_ADMIN'])
    def show(Long id) {
        def versioningInstance = Versioning.get(id)
        if (!versioningInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'versioning.label', default: 'Versioning'), id])
            redirect(action: "list")
            return
        }

        [versioningInstance: versioningInstance]
    }
	
	@Secured('ROLE_ADMIN')
    def delete(Long id) {
        def versioningInstance = Versioning.get(id)
        if (!versioningInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'versioning.label', default: 'Versioning'), id])
            redirect(action: "list")
            return
        }

        try {
            versioningInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'versioning.label', default: 'Versioning'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'versioning.label', default: 'Versioning'), id])
            redirect(action: "show", id: id)
        }
    }
}
