package flashcards

import java.io.File
import kotlin.text.StringBuilder

class FileHandler {

    private val logger = Logger.getLogger()

    fun importCards(cards: MutableList<Card>, file: File) {
        if (!file.exists()) {
            logger.logAndPrintln("File not found.\n")
            return
        }
        val content = file.readLines()
        for (i in content.indices step 3) {
            cards.removeIf { card ->  card.term == content[0] }
            cards.add(Card(content[i], content[i + 1], content[i + 2].toInt()))
        }
        logger.logAndPrintln("${content.size / 3} cards have been loaded.\n")
    }

    fun exportCards(cards: MutableList<Card>, file: File) {
        val sb = StringBuilder()
        cards.forEach {
            sb.append("${it.term}\n${it.definition}\n${it.mistakes}\n")
        }
        file.writeText(sb.toString())
        logger.logAndPrintln("${cards.size} cards have been saved.\n")
    }

    fun saveLogs() {
        println("File name: ")
        val file = File(readLine()!!)
        val sb = StringBuilder()
        for (log in logger.getLogs()) {
            sb.append("$log\n")
        }
        file.writeText(sb.toString())
        println("The log has been saved.\n")
    }

    fun importCards(cards: MutableList<Card>) {
        logger.logAndPrintln("File name: ")
        val file = File(readLine()!!)
        logger.log(file.toString())
        importCards(cards, file)
    }

    fun exportCards(cards: MutableList<Card>) {
        logger.logAndPrintln("File name: ")
        val file = File(readLine()!!)
        logger.log(file.toString())
        exportCards(cards, file)
    }
}