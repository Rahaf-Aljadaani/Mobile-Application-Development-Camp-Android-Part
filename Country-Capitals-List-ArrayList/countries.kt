fun main () {
    val countries = listOf("KSA", "South Korea", "Japan ")
    val capital = arrayListOf<String>() // Riyadh ,Seoul ,Tokyo
    println("*** Countries *** ")
    for (i in 0..2) {
        println("What is the capital of the " + countries[i] + " ? ")
        capital.add(readLine()!!.toString())
    }

    println("> Info of Countries : ")
    for (i in 0..2) {
        println(countries[i]+" : "+capital[i])
    }

}
