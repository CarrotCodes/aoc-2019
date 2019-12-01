package days

import helper.DayRunner
import helper.ResourceLineReader

class Day1_1Runner: DayRunner {

    override fun run(): String {
        val inputs = ResourceLineReader.readLines("1-1")
        return inputs.joinToString()
    }
}