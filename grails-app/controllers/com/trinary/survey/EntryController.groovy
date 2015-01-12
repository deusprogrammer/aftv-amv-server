package com.trinary.survey

import grails.converters.JSON

class EntryController {
	Boolean videoChanged = false;
	
    def current() { 
		println("PARAMS: ${params}")
		String pid = params.pid
		
		if (!pid) {
			println("PID was null!")
			return
		}
		
		Contest contest = Contest.findByUuid(pid)
		
		if (!contest) {
			println("Contest not found!")
			response.setStatus(404)
			return
		}
		
		println "CONTEST: ${contest.title}"
		println "NOW PLAYING: '${contest.nowPlaying?.title}' ON PID '${pid}'"
		
		render contest.nowPlaying as JSON
	}
	
	def vote() {
		println("JSON: ${request.JSON}")
		
		String vid = request.JSON.vid
		String rating = request.JSON.rating
		
		if (!vid || !rating) {
			response.setStatus(404)
			return
		}
		
		ContestEntry entry = ContestEntry.findByUuid(vid)
		Voter voter = new Voter(ipAddress: request.getRemoteAddr())
		voter.save()
		Vote vote = new Vote(voter: voter, entry: entry, value: rating).save()
		
		println "IP: ${vote.voter.ipAddress} RATING: ${vote.value}"
		
		render ([requestStatus: "SUCCESS"]) as JSON
	}
}