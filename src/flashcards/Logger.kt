package flashcards

class Logger private constructor() {

    private val logs = mutableListOf<String>()

    companion object {
        private var LOGGER: Logger? = null

        fun getLogger(): Logger {
                var logger = LOGGER

                if (logger == null) {
                    logger = Logger()
                    LOGGER = logger
                }
                return logger
        }
    }

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