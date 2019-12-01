package helper

import java.io.IOException

object ResourceLineReader {

    fun readLines(day: String): List<String>? {
        val path = "/inputs/day$day.txt"
        return try {
            readLinesFromPath(path)
        } catch (exception: IOException) {
            null
        }
    }

    private fun readLinesFromPath(resourcePath: String): List<String> {
        return this::class.java
            .getResourceAsStream(resourcePath)
            .bufferedReader(charset = Charsets.UTF_8)
            .readLines()
    }
}