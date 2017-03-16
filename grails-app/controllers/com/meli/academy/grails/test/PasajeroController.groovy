package com.meli.academy.grails.test

class PasajeroController {

    def index() { 
		
	}
	
	def save() {
		def pasajero = new Pasajero(params)
		if (pasajero.save(flush:true)) {
			flash.message = message(
				code: 'default.create.message',
				args: [message(code: 'pasajero.label', default:'Pasajero'), pasajero.id])
			redirect(action: "show", id:pasajero.id)
		}else {
		render(view:"create", model:[pasajeroInstance:pasajero])
		}
	}
	
	
}
