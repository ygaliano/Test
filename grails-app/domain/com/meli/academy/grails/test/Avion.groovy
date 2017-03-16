package com.meli.academy.grails.test

import java.text.SimpleDateFormat

class Avion {
	
	String aerolinea
	Date fechaSalida
	Date fechaLlegada
	
	String toString(){
		def sdf = new SimpleDateFormat("dd MMM yyyy HH:mm")
		"${aerolinea} SALIDA: "+
		"${sdf.format(fechaSalida)} "+
		"- LLEGADA: ${sdf.format(fechaLlegada)}"
	}

    static constraints = {
    }
}
