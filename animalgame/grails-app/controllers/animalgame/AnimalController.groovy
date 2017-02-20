package animalgame



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import animalgame.Animal
import gameutils.GameHelper

@Transactional(readOnly = true)
class AnimalController {

    static responseFormats = ['json', 'xml']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        println "test param "+ params.listDoNotDo
        
        //converting params to list of integer
        def listIdDoNotDo = GameHelper.convertStringListToLongList(params.listDoNotDo)
        def listIdDo = GameHelper.convertStringListToLongList(params.listDo)
        params.max = Math.min(max ?: 10, 100)
        println "Animals left "+Animal.list(params).getTotalCount() 
        def animalCriteria = Animal.createCriteria()
        def result
        if(!params.listDoNotDo && !params.listDo){
            respond Animal.list(), [status: OK]
        }
        
        if(params.listDoNotDo.length() > 0 && params.listDo.length() >0){
            result =  animalCriteria.list{
                or{ 
                    listDoNotDo{
                        'in'('id', listIdDoNotDo)
                    }
                    listDo{
                        'in'('id', listIdDo)
                    }
                }
            }
        }else if(params.listDoNotDo.length() > 0 ){
            result =  animalCriteria.list{
                listDoNotDo{
                'in'('id', listIdDoNotDo)
                }
            };
        }else{
            result =  animalCriteria.list{
                listDo{
                'in'('id', listIdDo)
                }
            };
        }
        respond result, [status: OK]
        
    }
    
    @Transactional
    def save(Animal animalInstance) {
        if (animalInstance == null) {
            render status: NOT_FOUND
            return
        }

        animalInstance.validate()
        if (animalInstance.hasErrors()) {
            render status: NOT_ACCEPTABLE
            return
        }

        animalInstance.save flush:true
        respond animalInstance, [status: CREATED]
    }

    @Transactional
    def update(Animal animalInstance) {
        if (animalInstance == null) {
            render status: NOT_FOUND
            return
        }

        animalInstance.validate()
        if (animalInstance.hasErrors()) {
            render status: NOT_ACCEPTABLE
            return
        }

        animalInstance.save flush:true
        respond animalInstance, [status: OK]
    }

    @Transactional
    def delete(Animal animalInstance) {

        if (animalInstance == null) {
            render status: NOT_FOUND
            return
        }

        animalInstance.delete flush:true
        render status: NO_CONTENT
    }
}
