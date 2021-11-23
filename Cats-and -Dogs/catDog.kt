import kotlin.random.Random

abstract class Animal(name: String, age: Int) {
    abstract fun talk()
}

class Dog(val name: String, val age: Int) : Animal(name, age) {
    override fun talk() {
        println("$name: Woof")
    }

}

class Cat(val name: String, val age: Int) : Animal(name, age) {

    override fun talk() {
        println("$name: Meow")
    }

}

fun main() {
    val ListOfCat = listOf(
        Cat("Lili", Random.nextInt(11)),
        Cat("Meep", Random.nextInt(11)),
        Cat("Patchy", Random.nextInt(11)),
        Cat("Furball", Random.nextInt(11)),
        Cat("Snowball", Random.nextInt(11))
    )
    val ListOfDog = listOf(
        Dog("Rufus", Random.nextInt(11)),
        Dog("Fred", Random.nextInt(11)),
        Dog("Spot", Random.nextInt(11)),
        Dog("max", Random.nextInt(11)),
        Dog("meo", Random.nextInt(11))
    )

    for (i in ListOfCat){
        i.talk()
        for (j in ListOfDog){
            if(j.age> i.age){
                j.talk()
            }
        }
    }

}
