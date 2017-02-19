package animalgame

class Animal {
    String name
    static hasMany =[
        listDo: Adjective,
        listDoNotDo: Adjective
    ]
    static constraints = {
    	name unique: true
    }
}
