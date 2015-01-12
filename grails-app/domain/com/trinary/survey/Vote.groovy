package com.trinary.survey

class Vote {
	Integer value
	
	static belongsTo = [voter : Voter, entry : ContestEntry]

    static constraints = {
    }
}
