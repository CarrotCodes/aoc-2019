package helper

interface DayRunner {

    val testCases: List<Pair<List<String>, String>>
    fun run(inputs: List<String>): String
}