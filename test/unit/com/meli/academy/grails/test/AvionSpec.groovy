package com.meli.academy.grails.test

import grails.test.mixin.TestFor //genera clases de pruebas 
import java.text.SimpleDateFormat
import spock.lang.Specification //libreria de testing 
/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Avion) // declara el sujeto de prueba
class AvionSpec extends Specification {

	//acciones antes de las pruebas
    def setup() {
    }
	
	//acciones despues de las pruebas
    def cleanup() {
    }
	
	//pruebas
    void "test something"() {
		expect:
		Avion avion = new Avion(aerolinea:"LAN")
		assertEquals("LAN", avion.aerolinea) //compara si lo 1ro es igual a lo 2do
    }
	
	void "testToString"(){
		expect: 
		SimpleDateFormat df=
		new SimpleDateFormat("dd/MM/yyyy HH:mm")
		Avion avion = new Avion(aerolinea:"LAN",
			fechaSalida: df.parse("15/03/2017 12:00"), 
			fechaLlegada: df.parse("15/03/2017 13:45"))
		assertEquals(avion.toString(),
			"LAN SALIDA: 15 mar 2017 12:00 "+
			"- LLEGADA: 15 mar 2017 13:45")
	}
}
