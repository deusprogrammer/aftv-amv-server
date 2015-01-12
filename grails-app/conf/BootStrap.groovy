import com.trinary.survey.Contest
import com.trinary.survey.ContestEntry
import com.trinary.survey.SocketManager

class BootStrap {
    def init = { servletContext ->
		// Create some test data
		Contest contest = new Contest(uuid: "IKKICON-AMV", title: "Ikkicon AMV Contest", description: "Come watch music videos with us.")
		if (!contest.save(flush: true)) {
			println contest.errors
		}
		ContestEntry entry;
		entry = new ContestEntry(contest: contest, uuid: "10D", title: "Fading", description: "Clannad sad sad")
		if (!entry.save(flush: true)) {
			println entry.errors
		}
		entry = new ContestEntry(contest: contest, uuid:  "1D", title: "The Will to Win", description: "Clannad sad sad")
		if (!entry.save(flush: true)) {
			println entry.errors
		}
		entry = new ContestEntry(contest: contest, uuid:  "2D", title: "Sleepy Sun Bears", description: "Clannad sad sad")
		if (!entry.save(flush: true)) {
			println entry.errors
		}
		entry = new ContestEntry(contest: contest, uuid:  "3D", title: "Primary Resistance", description: "Clannad sad sad")
		if (!entry.save(flush: true)) {
			println entry.errors
		}
		entry = new ContestEntry(contest: contest, uuid:  "4D", title: "Already Over", description: "Clannad sad sad")
		if (!entry.save(flush: true)) {
			println entry.errors
		}
		entry = new ContestEntry(contest: contest, uuid:  "5D", title: "Everything has Changed", description: "Clannad sad sad")
		if (!entry.save(flush: true)) {
			println entry.errors
		}
		entry = new ContestEntry(contest: contest, uuid:  "6D", title: "Saber la Vida", description: "Clannad sad sad")
		if (!entry.save(flush: true)) {
			println entry.errors
		}
		entry = new ContestEntry(contest: contest, uuid:  "7D", title: "Otaku Paradise", description: "Clannad sad sad")
		if (!entry.save(flush: true)) {
			println entry.errors
		}
		entry = new ContestEntry(contest: contest, uuid:  "8D", title: "When it Rains", description: "Clannad sad sad")
		if (!entry.save(flush: true)) {
			println entry.errors
		}
		entry = new ContestEntry(contest: contest, uuid:  "9D", title: "Forever is a Long Long Time", description: "Clannad sad sad")
		if (!entry.save(flush: true)) {
			println entry.errors
		}
		
		def contests = Contest.list()
		
		for (Contest c : contests) {
			println("Contest: ${contest.uuid}")
			for (ContestEntry ce : contest.entries) {
				println("Entry: ${ce.uuid}")
			}
		}
		
		// Start socket manager
		new SocketManager(4000).start()
    }
    def destroy = {
    }
}
