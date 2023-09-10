import com.codeborne.selenide.Selectors.byText
import com.codeborne.selenide.Selenide.open
import kotlin.math.floor
import kotlin.math.log10

const val URL = "https://learningcontent.cisco.com/games/binary/index.html"

fun main() {
    open(URL)

    BinaryGame.buttonPlayGame.click()

    while (true) {
        val problemType = BinaryGame.interpretProblem()

        println("New Problem of $problemType")

        when (problemType) {
            ProblemType.BINARY_REP -> {

                BinaryGame.bits.forEach {
                    if (it.text.toInt() == 1) it.click()
                }

                val target: Int
                try {
                    target = BinaryGame.digits.text.toInt()
                } catch (e: NumberFormatException) {
                    println("Out of sync, retrying...")
                    continue
                }

                var targetBinary = Integer.toBinaryString(target).toInt()

                println("Target: $target, Binary: $targetBinary")

                var i = 0;
                while (targetBinary > 0) {
                    if (targetBinary % 10 == 1) BinaryGame.bits.reversed()[i].click()
                    targetBinary /= 10
                    i++
                }

            }
            ProblemType.INT_INP -> {
                val binaryState = BinaryGame.bits.joinToString("") { it.text }
                val state = Integer.parseInt(binaryState, 2).toString()

                println("Binary: $binaryState, State: $state")

                BinaryGame.digits.click()

                state.forEach {
                    BinaryGame.calculator.findElement(byText(it.toString())).click()
                }

                BinaryGame.calculatorSubmit.click()

            }
        }

        Thread.sleep(1000)
        if (BinaryGame.next.exists()) {
            println("Proceeding to next level...")
            BinaryGame.next.click()
        }

    }


}