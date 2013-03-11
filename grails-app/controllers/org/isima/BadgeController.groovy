package org.isima

import org.springframework.dao.DataIntegrityViolationException
import grails.plugins.springsecurity.Secured

class BadgeController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
	def gameService
	
    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [badgeInstanceList: Badge.list(params), badgeInstanceTotal: Badge.count()]
    }
	
	@Secured('ROLE_ADMIN')
    def create() {
        [badgeInstance: new Badge(params)]
    }
	
	@Secured('ROLE_ADMIN')
    def save() {
        def badgeInstance = new Badge(params)
        if (!badgeInstance.save(flush: true)) {
            render(view: "create", model: [badgeInstance: badgeInstance])
            return
        }
		
		// Check if some users could obtain the new badge with their points 
		gameService.updateBadgeAttribution(badgeInstance)

        flash.message = message(code: 'default.created.message', args: [message(code: 'badge.label', default: 'Badge'), badgeInstance.id])
        redirect(action: "show", id: badgeInstance.id)
    }

    def show(Long id) {
        def badgeInstance = Badge.get(id)
        if (!badgeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'badge.label', default: 'Badge'), id])
            redirect(action: "list")
            return
        }

        [badgeInstance: badgeInstance]
    }
	
	@Secured('ROLE_ADMIN')
    def edit(Long id) {
        def badgeInstance = Badge.get(id)
        if (!badgeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'badge.label', default: 'Badge'), id])
            redirect(action: "list")
            return
        }

        [badgeInstance: badgeInstance]
    }
	
	@Secured('ROLE_ADMIN')
    def update(Long id, Long version) {
        def badgeInstance = Badge.get(id)
        if (!badgeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'badge.label', default: 'Badge'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (badgeInstance.version > version) {
                badgeInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'badge.label', default: 'Badge')] as Object[],
                          "Another user has updated this Badge while you were editing")
                render(view: "edit", model: [badgeInstance: badgeInstance])
                return
            }
        }

		def oldNbPoints = badgeInstance.pointsToObtain
        badgeInstance.properties = params

        if (!badgeInstance.save(flush: true)) {
            render(view: "edit", model: [badgeInstance: badgeInstance])
            return
        }
		
		// If the number of points was modified, awards could be modified
		if(oldNbPoints != badgeInstance.pointsToObtain)
			gameService.updateBadgeAttribution(badgeInstance)
		
        flash.message = message(code: 'default.updated.message', args: [message(code: 'badge.label', default: 'Badge'), badgeInstance.id])
        redirect(action: "show", id: badgeInstance.id)
    }
	
	@Secured('ROLE_ADMIN')
    def delete(Long id) {
        def badgeInstance = Badge.get(id)
        if (!badgeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'badge.label', default: 'Badge'), id])
            redirect(action: "list")
            return
        }

        try {
            badgeInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'badge.label', default: 'Badge'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'badge.label', default: 'Badge'), id])
            redirect(action: "show", id: id)
        }
    }
}
