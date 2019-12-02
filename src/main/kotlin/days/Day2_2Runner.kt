package days

import helper.DayRunner
import helper.ResourceLineReader
import java.lang.RuntimeException
import kotlin.math.floor

class Day2_2Runner: DayRunner {

    /*
    Once the program has halted, its output is available at address 0, also just like before. Each time you try a pair of inputs, make sure you first reset the computer's memory to the values in the program (your puzzle input) - in other words, don't reuse memory from a previous attempt.

    Find the input noun and verb that cause the program to produce the output 19690720. What is 100 * noun + verb? (For example, if noun=12 and verb=2, the answer would be 1202.)

    Result: 6718 (noun 67, verb 18)
     */

    override val testCases = emptyList<Pair<List<String>, String>>()

    override fun run(inputs: List<String>): String {
        val parsedInput = inputs
            .first()
            .split(",")
            .map { it.toInt() }

        val nounPosition = 1
        val verbPosition = 2

        val expectedValue = 19690720

        (0..99).forEach { replacementNoun ->
            (0..99).forEach { replacementVerb ->
                val mutatedInput = parsedInput.toMutableList().apply {
                    this[nounPosition] = replacementNoun
                    this[verbPosition] = replacementVerb
                }
                val output = Day2_1Runner.run(mutatedInput)
                if (output[0] == expectedValue) {
                    val result = (100 * replacementNoun) + replacementVerb
                    return result.toString()
                }
            }
        }

        throw RuntimeException("no result found")
    }
}