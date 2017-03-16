package com.meli.academy.grails.test

class AeropuertoController {

    def index() {
		redirect action: 'bienvenida'
	}
	
	def bienvenida(){
		render "Bienvenidos"
	}
	
	def display(){
		render template:"plantilla"
	}
	
}
