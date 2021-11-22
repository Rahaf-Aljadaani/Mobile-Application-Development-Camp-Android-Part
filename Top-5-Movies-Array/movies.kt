fun main (){
    var movies= arrayOf("", "", "","","")
    println("*** Your top 5 movies *** ")
    for( i in 0..4 ){
        println("Enter your "+ (i+1) +" movie :")
       movies[i] = readLine()!!.toString()
    }
    println("> Your Top 5 : ")
    for( i in 0..4 ){
        println("$i - "+ movies[i])
    }

}
