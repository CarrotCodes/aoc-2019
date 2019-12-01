package helper

object ResourceLineReader {

    fun readLines(day: String): List<String> {
        val path = "/inputs/day$day.txt"
        return readLinesFromPath(path)
    }

    private fun readLinesFromPath(resourcePath: String): List<String> {
        return this::class.java
            .getResourceAsStream(resourcePath)
            .bufferedReader(charset = Charsets.UTF_8)
            .readLines()
    }
}