package com.meli.academy.grails.test

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(PasajeroController)
@Mock(Pasajero)
class PasajeroControllerSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "test something"() {
    }
	
	void "Probando grabar un pasajero invalido"(){
		when: 
			controller.save()
		then: 
			model.pasajeroInstance != null
			view == '/pasajero/create'
	}	
	
	void "Probando grabar un pasajero valido"(){
		when:
			params.nombre="Elgato"
			params.apellido="Volador"
			params.dni="24234543"
			controller.save()
		then:
			response.redirectUrl == '/pasajero/show/1'
			flash.message != null
			Pasajero.count()==1
	}
}
