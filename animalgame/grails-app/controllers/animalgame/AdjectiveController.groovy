package animalgame



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class AdjectiveController {

    static responseFormats = ['json', 'xml']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(String max) {
        println params
        String listId 
        if(params.listDoNotDo.length() > 0 && params.listDo.length() > 0)
            listId = params.listDoNotDo +","+params.listDo
        else if(params.listDoNotDo.length() > 0)
            listId = params.listDoNotDo
        else
            listId = params.listDo
            
        if(params.random == "1"){
            println "random result request"
            respond Adjective.find("from Adjective where id not in($listId) order by rand()"),[status: OK]
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
            println "an error in new adjective"
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
