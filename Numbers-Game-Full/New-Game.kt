import kotlin.random.Random

fun main (){
    var count = 1
    val numRandom = Random.nextInt(11) //from 0 to 10
    while (count<=3 ){
        print("Guess a number between 0 and 10 : ")
        var num = readLine()
        try {
            when(num!!.toInt()){
                numRandom -> {
                    println("You get it!")
                    break }
                else -> println("Wrong guess, try again")
            }
            count++
        } catch (e: Exception) {
            if (num.equals("quit")){
                break
            }
            println("Numbers only")
        }
    }
    println("The answer is $numRandom")
    println("Game is over!")
}
