
import animalgame.Adjective
import animalgame.Animal

class BootStrap {

    def init = { servletContext ->
        persistInitialData()
    }
    def destroy = {
    }
    //Store first records in database
    private void persistInitialData() {
        def adjective = new Adjective(description:"Vive na água")
        adjective.save()  
        def shark = new Animal(name:"Tubarão")
        shark.addToListDo(adjective)
        shark.save()
        def monkey = new Animal(name:"Macaco")
        monkey.addToListDoNotDo(adjective)
        monkey.save()
    }
}
