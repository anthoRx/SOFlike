package org.isima
import javax.imageio.*
import javax.imageio.spi.*

class User {

	transient springSecurityService
	
	String username
	String password
	boolean enabled
	boolean accountExpired
	boolean accountLocked
	boolean passwordExpired
	//Custom params
	String name
	String lastName
	int points
	//Avatar
	byte[] avatar
	String avatarType
	
	List interactionsContents
	
	static hasMany = [badges:Badge,votes:Vote,interactionContents:InteractionContent]
	
	static constraints = {
		username blank: false, unique: true
		password blank: false
		name nullable: true
		lastName nullable: true
		points nullable: true
		avatar(nullable:true, maxSize: 16384 /* 16K */)
		avatarType(nullable:true)
	}

	static mapping = {
		password column: '`password`'
	}

	Set<Role> getAuthorities() {
		UserRole.findAllByUser(this).collect { it.role } as Set
	}

	def beforeInsert() {
		//We add the avatar
		def img = ImageIO.read(new File("/Users/PFR/Desktop/client.png"));
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write( img, "png", baos );
		baos.flush();
		
		byte[] imageInByte = baos.toByteArray();
		baos.close();
		
		avatar = imageInByte
		avatarType = "png"
		
		encodePassword()
	}

	def beforeUpdate() {
		if (isDirty('password')) {
			encodePassword()
		}
	}

	protected void encodePassword() {
		password = springSecurityService.encodePassword(password)
	}
}
