package com.meli.academy.grails.test

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Guardia)
class GuardiaSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "test something"() {
    }
	
	void "Probar que el nombre compienza con una letra mayuscula"(){
		when: 'El nombre comienza con una letra minuscula'
			def g = new Guardia(nombre:'marco', edad:35)
		then: 'la validacion no pasa'
			!g.validate()
			
		when: 'El nombre comienza con una letra mayuscula'
			g= new Guardia(nombre:'Marco', edad:35)
		then: 'la validacion pasa'
			g.validate()
	}
}
