import days.Day1_1Runner
import helper.DayRunner

object Main {

    private val runners = mapOf<String, DayRunner>(
        "1-1" to Day1_1Runner()
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

        return runner.run()
    }
}