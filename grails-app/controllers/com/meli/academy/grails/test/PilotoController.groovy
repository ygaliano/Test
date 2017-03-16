package com.meli.academy.grails.test



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class PilotoController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Piloto.list(params), model:[pilotoInstanceCount: Piloto.count()]
    }

    def show(Piloto pilotoInstance) {
        respond pilotoInstance
    }

    def create() {
        respond new Piloto(params)
    }

    @Transactional
    def save(Piloto pilotoInstance) {
        if (pilotoInstance == null) {
            notFound()
            return
        }

        if (pilotoInstance.hasErrors()) {
            respond pilotoInstance.errors, view:'create'
            return
        }

        pilotoInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'piloto.label', default: 'Piloto'), pilotoInstance.id])
                redirect pilotoInstance
            }
            '*' { respond pilotoInstance, [status: CREATED] }
        }
    }

    def edit(Piloto pilotoInstance) {
        respond pilotoInstance
    }

    @Transactional
    def update(Piloto pilotoInstance) {
        if (pilotoInstance == null) {
            notFound()
            return
        }

        if (pilotoInstance.hasErrors()) {
            respond pilotoInstance.errors, view:'edit'
            return
        }

        pilotoInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Piloto.label', default: 'Piloto'), pilotoInstance.id])
                redirect pilotoInstance
            }
            '*'{ respond pilotoInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Piloto pilotoInstance) {

        if (pilotoInstance == null) {
            notFound()
            return
        }

        pilotoInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Piloto.label', default: 'Piloto'), pilotoInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'piloto.label', default: 'Piloto'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
	
	def showPilotoDetalle(){
		[nombre: 'Juan'
			, apellido:'Perez'
			, edad:30]
	}
	
	def renderJson(){
		render(contentType:"text/json"){
			piloto(nombre:'Juan',
				apellido: 'Perez',
				edad:30)
		}
	}
}
