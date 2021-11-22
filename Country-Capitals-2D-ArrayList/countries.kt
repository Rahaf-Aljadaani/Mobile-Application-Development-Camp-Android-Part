import kotlin.collections.ArrayList as ArrayList1
/*
KSA -> Riyadh
South Korea -> Seoul
Japan -> Tokyo
 */
fun main () {
    val arrayList  =  ArrayList1<ArrayList1<Any>>()
    println("*** Countries *** ")
    for (i in 0..2) {
        println("Enter a country : ")
        val country = readLine()!!.toString()
        println("What is the capital of the $country ? ")
        val capital = readLine()!!.toString()
        arrayList.add(arrayListOf(country, capital))
    }
    println("> Info of Countries : ")
    for (arr in arrayList) {
        println("The capital of ${arr.get(0)} is ${arr.get(1)}.")
    }

}
