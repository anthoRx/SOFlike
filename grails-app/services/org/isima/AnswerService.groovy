package org.isima

class AnswerService {
	
	def versioningService
    
	def update(Answer a, String oldContent) {
		versioningService.versionContent(a,oldContent)		
	}
}
