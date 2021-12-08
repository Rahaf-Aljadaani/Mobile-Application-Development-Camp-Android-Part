/*Lambda Practice

Lambda Practice App:*/

// Find one function (with no parameters) you have used in the past and rewrite it using a lambda expression

// original
    fun info(){
        println("Hi I'm Rahaf")
    }
    info()
// Lambda
    val info = { 
      println("Hi I'm Rahaf")
    }
    info()

// Find two functions that take in one or more parameters and rewrite them using a lambda expression
// original
    fun info(name: String){
        println("Hi I'm  $name")
    }
    info("Rahaf")
    
// Lambda
    val info = { name:String -> println("Hi I'm  $name") }
    info("Rahaf")
    
//------

// original
    fun add(num1: Int , num2: Int){
        println("= ${num1 + num2}")
    }
    add(1,2)
    
// Lambda
    val add = { num1: Int , num2: Int ->  println("= ${num1 + num2}") }
    add(1,2)
   
// Find two functions that return a value and rewrite them using a lambda expression

// original
    fun sub(num1: Int , num2: Int){
     return num1 - num2
    }
   println(sub(1,2)) 
    
// Lambda
    val sub = { num1: Int , num2: Int -> num1 - num2 }
   println(sub(1,2)) 
   


//Make use of a data class to create a Player class with attributes name, age, height (in cm)
data class Player(val name: String, val age: Int, , val height: Double)

//Create a list of 20 players
  val ListOfPlayer = listOf(
        Player("Lili", 20,150),
        Player("Meep",  20,150),
        Player("Patchy",  20,150),
        Player("Furball",  20,150),
        Player("Snowball", 20,150),
        Player("Patchy",  20,150),
        Player("Furball",  20,150),
        Player("Snowball", 20,150),
        Player("Patchy",  20,150),
        Player("Furball",  20,150),
        Player("Snowball", 20,150),
        Player("Patchy",  20,150),
        Player("Furball",  20,150),
        Player("Snowball", 20,150),
        Player("Patchy",  20,150),
        Player("Furball",  20,150),
        Player("Snowball", 20,150),
        Player("Patchy",  20,150),
        Player("Furball",  20,150),
        Player("Snowball", 20,150)
    )

//Use a lambda expression to sort and print the list by each attribute 
ListOfPlayer.forEach { name ->
    println("Player $name count: ")
}
ListOfPlayer.forEach { age ->
    println("Player $name count: ")
}
ListOfPlayer.forEach { height ->
    println("Player $name count: ")
}





