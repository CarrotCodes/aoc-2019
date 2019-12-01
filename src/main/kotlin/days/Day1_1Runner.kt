package days

import helper.DayRunner
import helper.ResourceLineReader
import kotlin.math.floor

class Day1_1Runner: DayRunner {

    /*
    Fuel required to launch a given module is based on its mass. Specifically, to find the fuel required for a module, take its mass, divide by three, round down, and subtract 2.

    For example:
        For a mass of 12, divide by 3 and round down to get 4, then subtract 2 to get 2.
        For a mass of 14, dividing by 3 and rounding down still yields 4, so the fuel required is also 2.
        For a mass of 1969, the fuel required is 654.
        For a mass of 100756, the fuel required is 33583.

    The Fuel Counter-Upper needs to know the total fuel requirement. To find it, individually calculate the fuel needed for the mass of each module (your puzzle input), then add together all the fuel values.

    Result: 3216868
     */

    override val testCases = listOf(
        Pair(listOf("12"), "2"),
        Pair(listOf("14"), "2"),
        Pair(listOf("1969"), "654"),
        Pair(listOf("100756"), "33583")
    )

    override fun run(inputs: List<String>): String {
        val results = inputs.map {
            val input = it.toInt()
            compute(input)
        }
        val result = results.sum()
        return result.toString()
    }

    private fun compute(input: Int): Int {
        val dividedAndFloored = floor(input / 3.0).toInt()
        return dividedAndFloored - 2
    }
}