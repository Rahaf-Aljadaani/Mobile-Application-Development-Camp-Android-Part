import kotlin.random.Random
import kotlin.collections.ArrayList as ArrayList1

class countries(val country: String, val capital: String) {

    fun printInfo() {
        println("The capital of $country is $capital.")
    }
}

fun main() {
    val ListOfCointry = listOf(
        countries("KSA", "Riyadh"),
        countries("South Korea", "Seoul"),
        countries("Japan", "Tokyo"),
        countries("Russia", "Moscow"),
        countries("India", "NewDelhi"),
        countries("Morocco", "Rabat"),
        countries("China", "Beijing"),
        countries("Indonesia", "Jakarta"),
        countries("Egypt", "Cairo"),
        countries("NewZealand", "Wellington")
    )

    var playAgain = "y"
    while (playAgain.equals("y")) {
        println("*** Countries Game *** ")
        var scour = 0
        val numbers = choce3number()
        for (i in numbers) {
            println("What is the capital of the ${ListOfCointry[i].country} ? ")
            val answer = readLine()!!.toString()

            when (answer) {
                ListOfCointry[i].capital -> {
                    scour++
                    println("You get it!")
                }
                else -> {
                    println("Wrong Answer")
                    ListOfCointry[i].printInfo()
                }
            }
        }
        println("> Your scour is $scour ")
        println("> Do You Want Play Again ? (Y/N) ")
        playAgain = readLine()!!.toString().lowercase()
    }
    println("> The End ")

}

// get uniq numbers of Quations
fun choce3number(): ArrayList1<Int> {
    val arrayList = ArrayList1<Int>()
    arrayList.add(Random.nextInt(10))
    var count = 1
    while (count < 3) {
        while (true) {
            var num = Random.nextInt(10)
            var x = false
            for (i in arrayList) {
                if (i == num) {
                    x = true
                    continue
                }
            }
            if (x == false) {
                arrayList.add(num)
                break
            }
        }
        count++
    }
    return arrayList
}
