package org.isima

class QuestionService {
	
	def versioningService
	def gameService
	
	/**
	 * This method incremenent the number of view of the question q 
	 * and ask points in order to attribute them to the question owner.
	 * @param q The question to be updated
	 * @return nothing
	 */
    def incrementNbView(Question q) {
		++q.nbView
		gameService.askForPoints(q.user, "question", "nbView")
    }
	
	/**
	 * This method ask points in order to attribute them to the question owner.
	 * @param q The question
	 * @return nothing
	 */
	def create(Question q) {
		gameService.askForPoints(q.user, "question", "create")
	}
	
	/**
	 * This method handle the versioning of the question.
	 * The old version of the question is saved in database.
	 * 
	 * @param q The question
	 * @param oldContent Previous content of the question.
	 * @return nothing
	 */
	def update(Question q, String oldContent) {
		versioningService.versionContent(q,oldContent)		
	}
}
