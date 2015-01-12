package com.trinary.survey

class ContestEntry {
	String uuid
	
	String title
	String description
	
	static hasMany = [votes: Vote]
	static belongsTo = [contest: Contest]
	
    static constraints = {
    }
}
