package flashcards

import java.io.File

fun main(args: Array<String>) {
    val flashcards = Flashcards()
    val handler = FileHandler()
    var exFile: File? = null
    for (i in args.indices) {
        when(args[i]) {
            "-import" -> {
                handler.importCards(flashcards.cards, File(args[i+1]))
            }
            "-export" -> exFile = File(args[i+1])
        }
    }
    val logger = Logger.getLogger()
    var action = ""
    while (action != "exit") {
        logger.logAndPrintln("Input the action (add, remove, import, export, ask, exit, log, hardest card, reset stats): ")
        action = readLine()!!
        logger.log(action)
        when(action) {
            "add" -> flashcards.addCard()
            "remove" -> flashcards.removeCard()
            "import" -> handler.importCards(flashcards.cards)
            "export" -> handler.exportCards(flashcards.cards)
            "ask" -> flashcards.askCard()
            "log"-> handler.saveLogs()
            "hardest card" -> flashcards.askHardest()
            "reset stats" -> flashcards.resetStats()
            "exit" -> {
                println("Bye bye!")
                if (exFile != null) { handler.exportCards(flashcards.cards, exFile) }
            }
        }
    }
}
