package org.isima

import org.springframework.dao.DataIntegrityViolationException
import grails.plugins.springsecurity.Secured

class VoteController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
	def springSecurityService

    def index() {
        redirect(action: "list", params: params)
    }
	
	@Secured('ROLE_ADMIN')
    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [voteInstanceList: Vote.list(params), voteInstanceTotal: Vote.count()]
    }

	
	@Secured(['ROLE_USER','ROLE_ADMIN'])
	def saveInShow() {
		def voteInstance = new Vote(params)
		//We retrieve the user
		def user = springSecurityService.currentUser
		voteInstance.user = user
		
		if (!voteInstance.save(flush: true)) {
			render(view: "create", model: [voteInstance: voteInstance])
			return
		}

		flash.message = message(code: 'default.created.message', args: [message(code: 'vote.label', default: 'Vote'), voteInstance.id])
		redirect(action: "show", id: voteInstance.id)
	}
	
	
	@Secured('ROLE_ADMIN')
    def delete(Long id) {
        def voteInstance = Vote.get(id)
        if (!voteInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'vote.label', default: 'Vote'), id])
            redirect(action: "list")
            return
        }

        try {
            voteInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'vote.label', default: 'Vote'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'vote.label', default: 'Vote'), id])
            redirect(action: "show", id: id)
        }
    }
}
