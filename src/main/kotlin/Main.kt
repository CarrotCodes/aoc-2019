import days.Day1_1Runner
import days.Day1_2Runner
import days.Day2_1Runner
import days.Day2_2Runner
import helper.DayRunner
import helper.ResourceLineReader

object Main {

    private val runners = mapOf<String, DayRunner>(
        "1-1" to Day1_1Runner(),
        "1-2" to Day1_2Runner(),
        "2-1" to Day2_1Runner(),
        "2-2" to Day2_2Runner()
    )

    @JvmStatic fun main(args: Array<String>) {
        val arguments = args.asList()
        val firstArgument = arguments.firstOrNull()
        if (firstArgument == null || firstArgument == "help") {
            return printHelp()
        }

        val restOfArguments = arguments.drop(1)
        return when (firstArgument) {
            "run" -> {
                val result = handleRun(restOfArguments)
                println("result: $result")
            }
            "test" -> {
                val allPassed = handleTests(restOfArguments)
                if (allPassed == null) {
                    println("failed to run test cases")
                    return
                }

                return when (allPassed) {
                    true -> println("all test cases passed 🎉")
                    false -> println("some test cases failed ❌")
                }
            }
            else -> {
                println("unknown usage")
                return printHelp()
            }
        }
    }

    private fun printHelp() {
        println("usage:")
        println("* `help` - shows this message")
        println("* `run 1-1` - runs a given runner")
        println("* `test 1-1` - tests a given runner")
    }

    private fun handleRun(arguments: List<String>): String? {
        val day = arguments.firstOrNull()
        if (day == null) {
            println("must specify a day to run")
            return null
        }

        val runner = runners[day]
        if (runner == null) {
            println("unknown day to run, choose from: ${runners.keys}")
            return null
        }

        val inputs = ResourceLineReader.readLines(day) ?: listOf()
        return runner.run(inputs)
    }

    private fun handleTests(arguments: List<String>): Boolean? {
        val day = arguments.firstOrNull()
        if (day == null) {
            println("must specify a day to test")
            return null
        }

        val runner = runners[day]
        if (runner == null) {
            println("unknown day to test, choose from: ${runners.keys}")
            return null
        }

        println("running test cases:")
        val results = runner.testCases.map {
            val result = runner.run(it.first)
            val correct = it.second == result
            when (correct) {
                true -> {
                    println(" ${it.first} produced $result ✅")
                }
                else -> {
                    println(" ${it.first} produced $result instead of ${it.second} ❌")
                }
            }

            return@map correct
        }

        return results.all { it }
    }
}