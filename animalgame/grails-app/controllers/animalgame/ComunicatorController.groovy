package animalgame



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class ComunicatorController {

    static responseFormats = ['json', 'xml']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        println "indexando 2 "+params
        params.max = Math.min(max ?: 10, 100)
        //respond Animal.list(params), [status: OK]
        respond Animal.list(params), [status: OK]
    }
    
  
    
    @Transactional
    def save(Comunicator comunicatorInstance) {
       println "etaporrta"
       println "DOIN \n"+comunicatorInstance
        respond comunicatorInstance, [status: ok]
    }

    @Transactional
    def update(Comunicator comunicatorInstance) {
        if (comunicatorInstance == null) {
            render status: NOT_FOUND
            return
        }

        comunicatorInstance.validate()
        if (comunicatorInstance.hasErrors()) {
            render status: NOT_ACCEPTABLE
            return
        }

        comunicatorInstance.save flush:true
        respond comunicatorInstance, [status: OK]
    }

    @Transactional
    def delete(Comunicator comunicatorInstance) {
        render Animal.list()
//        if (comunicatorInstance == null) {
//            render status: NOT_FOUND
//            return
//        }
//
//        comunicatorInstance.delete flush:true
//        render status: NO_CONTENT
    }
}
