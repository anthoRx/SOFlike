package org.isima

class AnswerService {
	
	def versioningService
	def gameService
	
	/**
	 * This method ask points in order to attribute them to the answer owner.
	 * @param a The answer
	 * @return nothing
	 */
	def create(Answer a) {
		gameService.askForPoints(a.user, "answer", "create")		
	}
	
	/**
	 * This method handle the versioning of the answer.
	 * The old version of the answer is saved in database.
	 *
	 * @param a The answer
	 * @param oldContent Previous content of the answer.
	 * @return nothing
	 */
	def update(Answer a, String oldContent) {
		versioningService.versionContent(a,oldContent)		
	}
}
