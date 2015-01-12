package com.trinary.survey

enum VotingInterfaceStyle {
	SLIDER ("SLIDER"),
	STARS  ("STARS"),
	RADIO  ("RADIO")
	
	protected String value
	
	VotingInterfaceStyle(String value) {
		this.value = value
	}
}