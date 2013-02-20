package org.isima

import org.springframework.dao.DataIntegrityViolationException
import org.springframework.web.multipart.commons.CommonsMultipartFile
import org.apache.commons.lang.StringUtils
import org.isima.Question
import org.isima.Answer

class UserController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [userInstanceList: User.list(params), userInstanceTotal: User.count()]
    }

    def create() {
		params.put("enabled", true)
        [userInstance: new User(params)]
    }

    def save() {
        def userInstance = new User(params)
        if (!userInstance.save(flush: true)) {
            render(view: "create", model: [userInstance: userInstance])
            return
        }

        flash.message = message(code: 'default.created.message')
        redirect(action: "show", id: userInstance.id)
    }

    def show(Long id) {
        def userInstance = User.get(id)
        if (!userInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), id])
            redirect(action: "list")
            return
        }
		def questions = Question.getAll().findAll { it.user.id == userInstance.id}
		//def answers = Answer.getAll().findAll { it.question.user.id == userInstance.id}
		//def tags = Tag.getAll().findAll { it.user.id == userInstance.id}
		//def badges = Badge.getAll().findAll { it.users.id == id}
		
        [userInstance: userInstance, questions: questions ]
		//[answers: answers]
		//[tags: tags]
		//[badges: badges]
    }

    def edit(Long id) {
        def userInstance = User.get(id)
        if (!userInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), id])
            redirect(action: "list")
            return
        }

        [userInstance: userInstance]
    }

    def update(Long id, Long version) {
        def userInstance = User.get(id)
        if (!userInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (userInstance.version > version) {
                userInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'user.label', default: 'User')] as Object[],
                          "Another user has updated this User while you were editing")
                render(view: "edit", model: [userInstance: userInstance])
                return
            }
        }

		//params = params.findAll{!StringUtils.isBlank(it)}
        userInstance.properties = params

        if (!userInstance.save(flush: true)) {
            render(view: "edit", model: [userInstance: userInstance])
            return
        }
		
		//Handle the avatar
		
		// Get the avatar file from the multi-part request
		CommonsMultipartFile f = request.getFile('avatar')
	  
		// List of OK mime-types
		def okcontents = ['image/png', 'image/jpeg', 'image/gif']
		if(!f.isEmpty())
		{
			if (! okcontents.contains(f.getContentType())) {
			  flash.message = "Avatar must be one of: ${okcontents}"
			  render(view:'edit', model:[userInstance: userInstance])
			  return;
			}
			
			// Save the image and mime type
			userInstance.avatar = f.getBytes()
			userInstance.avatarType = f.getContentType()
			log.info("File uploaded: " + userInstance.avatarType)
		  
			// Validation works, will check if the image is too big
			if (!userInstance.save()) {
				flash.message = "Image too large"
				render(view:'edit', model:[userInstance: userInstance])
			  return;
			}
		}
	  
        flash.message = message(code: 'default.updated.message', args: [message(code: 'user.label', default: 'User'), userInstance.id])
        redirect(action: "show", id: userInstance.id)
    }

    def delete(Long id) {
        def userInstance = User.get(id)
        if (!userInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), id])
            redirect(action: "list")
            return
        }

        try {
            userInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'user.label', default: 'User'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'user.label', default: 'User'), id])
            redirect(action: "show", id: id)
        }
    }
	
	def avatar_image = {
		def avatarUser = User.get(params.id)
		if (!avatarUser || !avatarUser.avatar || !avatarUser.avatarType) {
		  response.sendError(404)
		  return;
		}
		response.setContentType(avatarUser.avatarType)
		response.setContentLength(avatarUser.avatar.size())
		OutputStream out = response.getOutputStream();
		out.write(avatarUser.avatar);
		out.close();
	  }
}
