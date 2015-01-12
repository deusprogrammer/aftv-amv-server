package com.trinary.survey

class Contest {
	String uuid
	
	String title
	String description
	
	ContestEntry nowPlaying
	
	ContestConfig config
	
	static hasMany = [entries: ContestEntry]
	
    static constraints = {
		config nullable: true
		nowPlaying nullable: true
    }
}
