import kotlin.random.Random

fun main (){
    var count = 1
    while (count<=3 ){
    val numRandom = Random.nextInt(11) //from 0 to 10
    print("Guess a number between 0 and 10 : ")
    var num = readLine()
    try {
        when(num!!.toInt()){
            numRandom -> println("You get it!")
            else -> println("Wrong guess, the answer is $numRandom !")
        }
        count++
    } catch (e: Exception) {
        if (num.equals("quit")){
            break
        }
        println("Numbers only")
    }
    }
    println("Game is over!")
}
