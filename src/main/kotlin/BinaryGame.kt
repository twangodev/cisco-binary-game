import com.codeborne.selenide.Selectors.byText
import com.codeborne.selenide.Selenide.element

// page_url = https://learningcontent.cisco.com/games/binary/index.html
object BinaryGame {
    val buttonPlayGame = element(byText("Play Game"))

    private val problem = element(".slide-fade-enter-done")
    val bits = problem.find(".bits").findAll("button")
    val digits = problem.find(".digits")

    val calculator = element(".calculator")
    val calculatorSubmit = calculator.find("button", 2)

    val next = element(byText("Next Level"))

    fun interpretProblem(): ProblemType {
        return if (digits.text == "?") ProblemType.INT_INP else ProblemType.BINARY_REP
    }


}