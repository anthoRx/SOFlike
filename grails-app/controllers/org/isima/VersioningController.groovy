package org.isima

import org.springframework.dao.DataIntegrityViolationException

class VersioningController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [versioningInstanceList: Versioning.list(params), versioningInstanceTotal: Versioning.count()]
    }

    def create() {
        [versioningInstance: new Versioning(params)]
    }

    def save() {
        def versioningInstance = new Versioning(params)
        if (!versioningInstance.save(flush: true)) {
            render(view: "create", model: [versioningInstance: versioningInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'versioning.label', default: 'Versioning'), versioningInstance.id])
        redirect(action: "show", id: versioningInstance.id)
    }

    def show(Long id) {
        def versioningInstance = Versioning.get(id)
        if (!versioningInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'versioning.label', default: 'Versioning'), id])
            redirect(action: "list")
            return
        }

        [versioningInstance: versioningInstance]
    }

    def edit(Long id) {
        def versioningInstance = Versioning.get(id)
        if (!versioningInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'versioning.label', default: 'Versioning'), id])
            redirect(action: "list")
            return
        }

        [versioningInstance: versioningInstance]
    }

    def update(Long id, Long version) {
        def versioningInstance = Versioning.get(id)
        if (!versioningInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'versioning.label', default: 'Versioning'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (versioningInstance.version > version) {
                versioningInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'versioning.label', default: 'Versioning')] as Object[],
                          "Another user has updated this Versioning while you were editing")
                render(view: "edit", model: [versioningInstance: versioningInstance])
                return
            }
        }

        versioningInstance.properties = params

        if (!versioningInstance.save(flush: true)) {
            render(view: "edit", model: [versioningInstance: versioningInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'versioning.label', default: 'Versioning'), versioningInstance.id])
        redirect(action: "show", id: versioningInstance.id)
    }

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
