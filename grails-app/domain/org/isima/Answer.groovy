package org.isima

class Answer extends InteractionContent{
	
	static belongsTo = [question:Question]
	
    static constraints = {
    }
	
	public String getResume()
	{
		String resume = content;
		if(resume.size() > 20)
			resume = content.substring(0, 20);
			
		return resume + "...";
	}
}
