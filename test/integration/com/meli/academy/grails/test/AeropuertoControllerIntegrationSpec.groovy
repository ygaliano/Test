package com.meli.academy.grails.test

class AeropuertoControllerIntegrationSpec extends GroovyTestCase {

    def setup() {
    }

    def cleanup() {
    }

    void "test something"() {
    }
	
	void "testBienvenida"(){
		def aeropuertoController = new AeropuertoController()
		aeropuertoController.bienvenida()
		assertEquals("Bienvenidos", aeropuertoController.response.contentAsString)
	}
	
	void "testRedirect"(){
		def aeropuertoController = new AeropuertoController()
		aeropuertoController.index()
		assertEquals("/aeropuerto/bienvenida", aeropuertoController.response.redirectedUrl)
	}
}
