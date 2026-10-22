fun monogram(keresztnev:String,vezeteknev:String):String{

    var valtozo1:Char =keresztnev.first()
    var valtozo2:Char=vezeteknev.first()
    return "$valtozo1$valtozo2"
}
fun leghosszabbEleme(list: List<String>):String{
return list.maxByOrNull{it.length}?:""
}
fun List<String>.joinWith(separator: String): String =
    joinToString(separator)

fun main() {
    println(monogram("John","Smith"))
    var list:List<String> = listOf("apple", "pear", "strawberry", "melon")
    println(leghosszabbEleme(list))
    val fruits = listOf("apple", "pear", "melon")
    println(fruits.joinWith("#"))
}