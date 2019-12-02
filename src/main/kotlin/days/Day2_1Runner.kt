package days

import helper.DayRunner
import helper.ResourceLineReader
import kotlin.math.floor

class Day2_1Runner: DayRunner {

    /*
    An Intcode program is a list of integers separated by commas (like 1,0,0,3,99). To run one, start by looking at the first integer (called position 0). Here, you will find an opcode - either 1, 2, or 99. The opcode indicates what to do; for example, 99 means that the program is finished and should immediately halt. Encountering an unknown opcode means something went wrong.

    Opcode 1 adds together numbers read from two positions and stores the result in a third position. The three integers immediately after the opcode tell you these three positions - the first two indicate the positions from which you should read the input values, and the third indicates the position at which the output should be stored.

    For example, if your Intcode computer encounters 1,10,20,30, it should read the values at positions 10 and 20, add those values, and then overwrite the value at position 30 with their sum.

    Opcode 2 works exactly like opcode 1, except it multiplies the two inputs instead of adding them. Again, the three integers after the opcode indicate where the inputs and outputs are, not their values.

    Once you're done processing an opcode, move to the next one by stepping forward 4 positions.

    Result: 3850704,12,2,2,1,1,2,3,1,3,4,3,1,5,0,3,2,13,1,60,1,6,19,62,2,23,6,124,1,5,27,125,1,10,31,129,2,6,35,258,1,39,13,263,1,43,9,266,2,47,10,1064,1,5,51,1065,1,55,10,1069,2,59,6,2138,2,6,63,4276,1,5,67,4277,2,9,71,12831,1,75,6,12833,1,6,79,12835,2,83,9,38505,2,87,13,192525,1,10,91,192529,1,95,13,192534,2,13,99,962670,1,103,10,962674,2,107,10,3850696,1,111,9,3850699,1,115,2,3850701,1,9,119,0,99,2,0,14,0
    (value at position 0: 3850704)
     */

    override val testCases = listOf(
        Pair(listOf("1,0,0,0,99"), "2,0,0,0,99"),
        Pair(listOf("2,3,0,3,99"), "2,3,0,6,99"),
        Pair(listOf("2,4,4,5,99,0"), "2,4,4,5,99,9801"),
        Pair(listOf("1,1,1,4,99,5,6,0,99"), "30,1,1,4,2,5,6,0,99")
    )

    override fun run(inputs: List<String>): String {
        val parsedInput = inputs
            .first()
            .split(",")
            .map { it.toInt() }
        val output = parsedInput.toMutableList()
        var index = 0

        loop@while (true) {
            val opCode = output[index]
            when (opCode) {
                1 -> {
                    val indexOfFirst = output[index + 1]
                    val indexOfSecond = output[index + 2]
                    val indexOfStorage = output[index + 3]

                    val first = output[indexOfFirst]
                    val second = output[indexOfSecond]

                    output[indexOfStorage] = first + second
                    index += 4
                }
                2 -> {
                    val indexOfFirst = output[index + 1]
                    val indexOfSecond = output[index + 2]
                    val indexOfStorage = output[index + 3]

                    val first = output[indexOfFirst]
                    val second = output[indexOfSecond]

                    output[indexOfStorage] = first * second
                    index += 4
                }
                99 -> break@loop
                else -> throw RuntimeException("unexpected opcode $opCode at position $index")
            }
        }

        return output.joinToString(",")
    }
}