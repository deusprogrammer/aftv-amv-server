package com.trinary.survey

class Voter {
	String ipAddress
	
	static hasMany = [votes : Vote]

    static constraints = {
    }
}
