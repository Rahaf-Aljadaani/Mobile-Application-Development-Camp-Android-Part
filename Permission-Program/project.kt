fun main (){
    val minAge = 19
    print("Enter your Age : ")
    val age = readLine()
    try {
        val test = age!!.toInt()
        if(test < minAge )
            println("you are not old enough")
        else
            println("Welcome!")

    } catch (e: Exception) {
        println("Please enter numbers only")
    }
}
