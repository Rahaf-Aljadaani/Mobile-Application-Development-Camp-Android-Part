fun main (){

    val numRandom = (0..10).random() //from 0 to 10
    print("Guess a number between 0 and 10 : ")
    val num = readLine()
    try {
        if(numRandom== num!!.toInt()  )
            println("You get it!")
        else
            println("Wrong guess, the answer is $numRandom !")

    } catch (e: Exception) {
        println("Numbers only")
    }
    println("Game is over!")
}
