package org.isima

class QuestionService {
	
	def versioningService
	
    def incrementNbView(Question q) {
		++q.nbView
    }
	
	def update(Question q, String oldContent) {
		versioningService.versionContent(q,oldContent)		
	}
}
