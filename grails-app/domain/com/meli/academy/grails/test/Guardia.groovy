package com.meli.academy.grails.test

class Guardia {
	
	String nombre
	int edad

    static constraints = {
		nombre matches:/[A-Z].*/
		edad range: 1..99
    }
}
