package com.trinary.survey

class EventController {
	def trigger() {
		println request.JSON
		String pid = request.JSON["pid"]
		String vid = request.JSON["vid"]
		
		if (!pid || !vid) {
			println "PID OR VID NOT SENT"
			return "FAILURE!"
		}
		
		Contest contest = Contest.findByUuid(pid)
		ContestEntry entry = ContestEntry.findByUuid(vid)
		
		if (!contest || !entry) {
			println "COULDN'T FIND ENTRY"
			return "FAILURE!"	
		}
		
		contest.nowPlaying = entry
		if (!contest.save(flush: true)) {
			println(contest.errors)	
		}
		
		println "NOW PLAYING: '${contest.nowPlaying.title}' ON PID '${pid}'" 
		
		SocketManager.broadcastEvent(pid, request.JSON.event)
		return "SUCCESS"
	}
}
