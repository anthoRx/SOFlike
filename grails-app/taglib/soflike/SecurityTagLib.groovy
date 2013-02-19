package soflike

class SecurityTagLib {
	static namespace = "sec"
	
	def userService
	
	/**
	 * Renders the body if user has authority
	 *
	 * @attr value REQUIRED the field value
	 */
	def ifAuthorized = { attrs, body ->
		def instance = assertAttribute("value", attrs, "ifModifyAuth")
		if (userService.hasPermission(instance)) {
			out << body()
		}
	}

	protected assertAttribute(String name, attrs, String tag) {
		if (!attrs.containsKey(name)) {
			throwTagError "Tag [$tag] is missing required attribute [$name]"
		}
		attrs.remove name
	}
}
