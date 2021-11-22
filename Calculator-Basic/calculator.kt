fun main (){
    var checkIfContinue = "y"
    while (checkIfContinue.equals("y")) {
        print("Enter first number : ")
        var num1 = check(readLine())
        print("Enter second number : ")
        var num2 = check(readLine())
        // num1 + num2 =  x
        val resalt = sum(num1, num2)
        println("$num1 + $num2 = $resalt")
        print("Would you like to add more numbers? (Y/N) >> ")
        checkIfContinue = readLine().toString().lowercase()
    }
    print("> The End")
}

fun sum ( x : Int , y: Int ) : Int {
    return (x+y)
}

fun check ( x : String? ) : Int {
    try {
        return x!!.toInt()
    } catch (e: Exception) {
        println("Error : Numbers only")
        return 0
    }
}
