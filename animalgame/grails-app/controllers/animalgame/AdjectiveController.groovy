package animalgame



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class AdjectiveController {

    static responseFormats = ['json', 'xml']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(String max) {
        //println "indexing "+params
        // params.max = 3
        if(params.random == "1"){
            println "random result request"
            respond Adjective.find("from Adjective order by rand()"),[status: OK]
        }else{
            println "Random not requested"
            respond Adjective.list(params), [status: OK]   
        }
       
        
    }

    @Transactional
    def save(Adjective adjectiveInstance) {
        if (adjectiveInstance == null) {
            render status: NOT_FOUND
            return
        }

        adjectiveInstance.validate()
        if (adjectiveInstance.hasErrors()) {
            render status: NOT_ACCEPTABLE
            return
        }

        adjectiveInstance.save flush:true
        respond adjectiveInstance, [status: CREATED]
    }

    @Transactional
    def update(Adjective adjectiveInstance) {
        if (adjectiveInstance == null) {
            render status: NOT_FOUND
            return
        }

        adjectiveInstance.validate()
        if (adjectiveInstance.hasErrors()) {
            render status: NOT_ACCEPTABLE
            return
        }

        adjectiveInstance.save flush:true
        respond adjectiveInstance, [status: OK]
    }

    @Transactional
    def delete(Adjective adjectiveInstance) {

        if (adjectiveInstance == null) {
            render status: NOT_FOUND
            return
        }

        adjectiveInstance.delete flush:true
        render status: NO_CONTENT
    }
}
