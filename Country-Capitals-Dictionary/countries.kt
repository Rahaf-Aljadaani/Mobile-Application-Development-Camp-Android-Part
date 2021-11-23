fun main() {
    /*
   KSA -> Riyadh
   South Korea -> Seoul
   Japan -> Tokyo
    */
    val dictionaryOfCountries = mutableMapOf<String, String>()
    println("*** Countries *** ")
    for (i in 0..2) {
        println("Enter a country : ")
        val country = readLine()!!.toString()
        println("What is the capital of the $country ? ")
        val capital = readLine()!!.toString()
        dictionaryOfCountries.set(country, capital)
    }
    println("> Info of Countries : ")
    for (d in dictionaryOfCountries) {
        println("The capital of ${d.key} is ${d.value}.")
    }
}
