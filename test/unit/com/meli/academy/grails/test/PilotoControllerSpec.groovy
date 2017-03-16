package com.meli.academy.grails.test

import spock.lang.*

@TestFor(PilotoController)
@Mock(Piloto)
class PilotoControllerSpec extends Specification {

    def populateValidParams(params) {
        //assert params != null
        params["nombre"] = 'Juan'
		params["apellido"]= 'Perez'
		params["edad"]=30
		
    }

    void "Test the index action returns the correct model"() {

        when:"The index action is executed"
            controller.index()

        then:"The model is correct"
            !model.pilotoInstanceList
            model.pilotoInstanceCount == 0
    }

    void "Test the create action returns the correct model"() {
        when:"The create action is executed"
            controller.create()

        then:"The model is correctly created"
            model.pilotoInstance!= null
    }

    void "Test the save action correctly persists an instance"() {

        when:"The save action is executed with an invalid instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'POST'
            def piloto = new Piloto()
            piloto.validate()
            controller.save(piloto)

        then:"The create view is rendered again with the correct model"
            model.pilotoInstance!= null
            view == 'create'

        when:"The save action is executed with a valid instance"
            response.reset()
            populateValidParams(params)
            piloto = new Piloto(params)

            controller.save(piloto)

        then:"A redirect is issued to the show action"
            response.redirectedUrl == '/piloto/show/1'
            controller.flash.message != null
            Piloto.count() == 1
    }

    void "Test that the show action returns the correct model"() {
        when:"The show action is executed with a null domain"
            controller.show(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the show action"
            populateValidParams(params)
            def piloto = new Piloto(params)
            controller.show(piloto)

        then:"A model is populated containing the domain instance"
            model.pilotoInstance == piloto
    }

    void "Test that the edit action returns the correct model"() {
        when:"The edit action is executed with a null domain"
            controller.edit(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the edit action"
            populateValidParams(params)
            def piloto = new Piloto(params)
            controller.edit(piloto)

        then:"A model is populated containing the domain instance"
            model.pilotoInstance == piloto
    }

    void "Test the update action performs an update on a valid domain instance"() {
        when:"Update is called for a domain instance that doesn't exist"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'PUT'
            controller.update(null)

        then:"A 404 error is returned"
            response.redirectedUrl == '/piloto/index'
            flash.message != null


        when:"An invalid domain instance is passed to the update action"
            response.reset()
            def piloto = new Piloto()
            piloto.validate()
            controller.update(piloto)

        then:"The edit view is rendered again with the invalid instance"
            view == 'edit'
            model.pilotoInstance == piloto

        when:"A valid domain instance is passed to the update action"
            response.reset()
            populateValidParams(params)
            piloto = new Piloto(params).save(flush: true)
            controller.update(piloto)

        then:"A redirect is issues to the show action"
            response.redirectedUrl == "/piloto/show/$piloto.id"
            flash.message != null
    }

    void "Test that the delete action deletes an instance if it exists"() {
        when:"The delete action is called for a null instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'DELETE'
            controller.delete(null)

        then:"A 404 is returned"
            response.redirectedUrl == '/piloto/index'
            flash.message != null

        when:"A domain instance is created"
            response.reset()
            populateValidParams(params)
            def piloto = new Piloto(params).save(flush: true)

        then:"It exists"
            Piloto.count() == 1

        when:"The domain instance is passed to the delete action"
            controller.delete(piloto)

        then:"The instance is deleted"
            Piloto.count() == 0
            response.redirectedUrl == '/piloto/index'
            flash.message != null
    }
	
	void 'test show piloto detalle'(){
		when: 
			def model =
			controller.showPilotoDetalle()
		then: 
			model.apellido == 'Perez'
	}
	
	void 'test render json'(){
		when: 
			controller.renderJson()
		then: 
			response.text == "{\"piloto\":{\"nombre\":"+
			"\"Juan\",\"apellido\":"+
			"\"Perez\",\"edad\":30}}"	
	}
}
