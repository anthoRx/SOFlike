package org.isima

class VoteService {
	
	def gameService
	
	/**
	 * This method ask points in order to attribute them to the vote owner.
	 * @param v The vote
	 * @return nothing
	 */
    def upVote(Vote v) {
		gameService.askForPoints(v.user, "vote", "up")
    }
	
	/**
	 * This method ask points in order to attribute them to the vote owner.
	 * @param v The vote
	 * @return nothing
	 */
	def downVote(Vote v) {
		gameService.askForPoints(v.user, "vote", "down")
	}
}
