package com.meli.academy.grails.test

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(AeropuertoController)
class AeropuertoControllerSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "test something"() {
    }
	
	void "test bienvenida"(){
		when:
			controller.bienvenida()
		then:
			response.text == "Bienvenidos"
	}
	
	void "test index"(){
		when:
			controller.index()
		then:
			response.redirectUrl == '/aeropuerto/bienvenida'
	}
	
	void "test display"(){
		when:
			controller.display()
		then:
			response.text == "Ejemplo"
	}
	
	
}
