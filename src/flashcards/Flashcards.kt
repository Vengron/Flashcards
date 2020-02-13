package flashcards

import java.lang.StringBuilder
import kotlin.random.Random

class Flashcards {

    var cards = mutableListOf<Card>()
    private val logger = Logger.getLogger()

    fun addCard() {
        logger.logAndPrintln("The card:")
        val term = readLine()!!
        logger.log(term)
        if (cards.any { it.term == term }) {
            logger.logAndPrintln("The card \"$term\" already exists.\n")
            return
        }
        logger.logAndPrintln("The definition of the card: ")
        val definition = readLine()!!
        logger.log(definition)
        if (cards.any { it.definition == definition }) {
            logger.logAndPrintln("The definition \"$definition\" already exists.\n")
            return
        }
        cards.add(Card(term, definition))
        logger.logAndPrintln("The pair (\"$term\":\"$definition\") has been added.\n")
    }

    fun askCard() {
        logger.logAndPrintln("How many times to ask?")
        val num = readLine()!!
        logger.log(num)
        repeat(num.toInt()) {
            val random = Random.nextInt(cards.size)
            val term = cards[random].term
            val definition = cards[random].definition
            logger.logAndPrintln("Print the definition of \"$term\":")
            val answer = readLine()!!
            logger.log(answer)
            if (answer != definition) {
                cards[random].mistakes += 1
            }
            logger.logAndPrintln(when {
                answer == definition -> "Correct answer.\n"
                cards.any { it.definition == answer } -> "Wrong answer. The correct one is \"$definition\", " +
                        "you've just written the definition of \"${cards.first { it.definition == answer }.term}\".\n"
                else -> "Wrong answer. The correct one is \"$definition\".\n"
            })
        }
    }

    fun removeCard() {
        logger.logAndPrintln("The card:")
        val term = readLine()!!
        logger.log(term)
        if (cards.any { it.term == term}) {
            cards.remove(cards.first{ it.term == term })
            logger.logAndPrintln("The card has been removed.\n")
        } else {
            logger.logAndPrintln("Can't remove \"$term\": there is no such card.\n")
        }
    }

    fun askHardest() {
        var mistake = 0
        cards.forEach {
            if (it.mistakes > mistake) {
                mistake = it.mistakes
            }
        }
        if (mistake == 0) {
            logger.logAndPrintln("There are no cards with errors.\n")
            return
        }
        val terms = cards.filter { it.mistakes == mistake }
        if (terms.size == 1) {
            logger.logAndPrintln("The hardest card is \"${terms[0].term}\". " +
                    "You have $mistake errors answering it.\n")
        } else {
            val sb = StringBuilder("\"${terms[0].term}\"")
            for (i in 1 until terms.size) {
                sb.append(", \"${terms[i].term}\"")
            }
            logger.logAndPrintln("The hardest cards are $sb. " +
                    "You have $mistake errors answering them.\n")
        }
    }

    fun resetStats() {
        cards.forEach { it.mistakes = 0 }
        logger.logAndPrintln("Card statistics has been reset.\n")
    }

}

data class Card(val term: String,
                val definition: String,
                var mistakes: Int = 0)
