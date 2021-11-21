import kotlin.random.Random

fun main (){
    val numRandom = Random.nextInt(11) //from 0 to 10
    print("Enter first number : ")
    var num1 = check(readLine())
    print("Enter second number : ")
    var num2 = check(readLine())
    // num1 * num2 - x = ?
    val resalt = num1 * num2 - numRandom
    println("$num1 * $num2 - x = $resalt")
    print("What is X ? >> ")
    var answer = check(readLine())
    when (answer){
        numRandom -> println("You get it!")
        else -> println("Wrong , X = $numRandom")
    }
}

// to check if it's number or if not return 0
fun check ( x : String? ) : Int {
    try {
        return x!!.toInt()
    } catch (e: Exception) {
        println("Error : Numbers only")
        return 0
    }

}
