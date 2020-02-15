package flashcards

object Logger {

    private val logs = mutableListOf<String>()

    fun log(log: String) {
        logs.add(log)
    }

    fun getLogs(): MutableList<String> {
        return logs
    }

    fun logAndPrintln(text: String) {
        log(text)
        println(text)
    }
}