package animalgame



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import animalgame.Animal

@Transactional(readOnly = true)
class AnimalController {

    static responseFormats = ['json', 'xml']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        println "test param "+ listStringToINteger(params.listDoNotDo).getClass()
        params.max = Math.min(max ?: 10, 100)
        println "Animals left "+Animal.list(params).getTotalCount() 
        def animalCriteria = Animal.createCriteria()
        def result = animalCriteria.list{
            listDoNotDo{
                'in'('id', [1l , 3l])
            }
        };
        respond result, [status: OK]
        
    }

    def listStringToINteger(String str){
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(str);

        List<Long> list = new ArrayList<Long>();

        while (matcher.find()) {
            System.out.println(matcher.group());
            list.add(Long.parseLong(matcher.group())); // Add the value to the list
        }
        list
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
