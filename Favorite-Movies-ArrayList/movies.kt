
fun main () {
    val movies = arrayListOf<String>()
    println("*** Your  favorite  movies *** ")
    for (i in 0..2) {
        println("Enter your movie :")
        movies.add(readLine()!!.toString())
    }
    var cont = "y"
    while (cont.equals("y")) {
        println(">Do you want to add more? (y/n) ")
        cont = readLine()!!.toString()
        when (cont) {
            "y" -> {
                println("Enter your movie :")
                movies.add(readLine()!!.toString())
            }
            else -> break;
        }
    }
    println("> Your favorite  movies : ")
    for (i in movies) {
        println(i)
    }

}
