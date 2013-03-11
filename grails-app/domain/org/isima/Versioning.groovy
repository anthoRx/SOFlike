package org.isima

import java.sql.Timestamp;

class Versioning {
	String content
	Timestamp modificationDate
	
	static belongsTo = [interactionContent:InteractionContent]
	
    static constraints = {
		content ( blank: false, maxSize: 50000, html: true )
    }
	
	public String getResume()
	{
		String resume = content;
		if(resume.size() > 40)
			resume = content.substring(0, 40);
			
		return resume + "...";
	}
}
