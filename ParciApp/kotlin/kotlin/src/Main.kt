
class Main{
    val words: MutableList<String>
    init{
        val url = URL("https://www.ms.sapientia.ro/~manyi/dict.txt")
        words = url.openStream().bufferedReader().readLines().toMutableList()
    }

    fun main(){
        val words: MutableList<String>


        val dict: IDictionary = ListDictionary()
        println("Number of words: ${dict.size()}")
        var word: String?
        while(true){
            print("What to find? ")
            word = readLine()
            if( word.equals("quit")){
                break
            }
            println("Result: ${word?.let { dict.find(it) }}")
        }
    }
}
