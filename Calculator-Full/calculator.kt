fun main (){
    var repet = "y"
    while (repet.equals("y")) {
        repet = "n"
        print("Enter first number : ")
        var num1 = check(readLine())
        print("Enter opration : ")
        var op = readLine()!!.toString()
        print("Enter second number : ")
        var num2 = check(readLine())
       when (op){
           "*" -> {
               val resalt = mult(num1, num2)
               println("$num1 * $num2 = $resalt") }
           "/" -> {
               if(num2 == 0) {
                   println("Error : You cannot diffide by 0 ")
                   repet = "y"
                   continue
               }
               val resalt = diff(num1, num2)
               println("$num1 / $num2 = $resalt") }
           "-" -> {
               val resalt = sub(num1, num2)
               println("$num1 - $num2 = $resalt") }
           "+" -> {
               val resalt = sum(num1, num2)
               println("$num1 + $num2 = $resalt") }

           else->print("Error : add just opration")
       }
    }
    print("> The End")
}

fun sum ( x : Int , y: Int ) : Double {
    return (x.toDouble()+y.toDouble())
}
fun sub ( x : Int , y: Int ) : Double {
    return (x.toDouble()-y.toDouble())
}
fun mult (x: Int, y: Int ) : Double {
    return (x.toDouble()*y.toDouble())
}
fun diff ( x : Int , y: Int ) : Double {
    return (x.toDouble()/y.toDouble())
}

fun check ( x : String? ) : Int {
    try {
        return x!!.toInt()
    } catch (e: Exception) {
        println("Error : Numbers only")
        return 0
    }
}
