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
		avatar(nullable:true, maxSize: 3000000 /* 3MO */)
		avatarType(nullable:true)
	}

	static mapping = {
		password column: '`password`'
	}
	
	Map<Tag, Integer> getUserTags()
	{
		Map<Tag, Integer> mapTags = new HashMap<Tag, Integer>();
		for(Object item in interactionContents){
			if(item instanceof Question)
			{
				Question quest = (Question) item
				for(Tag tag in quest?.tags){
					if(mapTags.containsKey(tag)){
						int val = mapTags.get(tag).value
						mapTags.remove(tag)
						mapTags.put(tag, ++val)
					}
					else{
						mapTags.put(tag, 1)
					}
				}
			}	
		}
				
		return mapTags;
	}

	Set<Role> getAuthorities() {
		UserRole.findAllByUser(this).collect { it.role } as Set
	}

	def beforeInsert() {
		//We set enabled true
		enabled = true;
		//We add the avatar				
		def img = ImageIO.read(new File("C:\\Users\\toony\\Desktop\\client.png"));
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
		if (springSecurityService)
			password = springSecurityService.encodePassword(password)
	}
	
	@Override String toString()
	{
		return this.username;
	}
}
