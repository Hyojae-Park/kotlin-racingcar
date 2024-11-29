package racing

import io.kotest.matchers.shouldBe
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import racing.view.CarRecordView
import racing.view.GameInfo
import racing.view.InputRuleView
import java.io.ByteArrayInputStream

class RacingGameAppTest {
    @Test
    fun testInputGameInfo() {
        val inputView = InputRuleView()
        val result =
            mockUserInput("3", "5") {
                assertDoesNotThrow {
                    inputView.inputGameInfo()
                }
            }.withPrintResult()

        assertThat(result).isInstanceOf(GameInfo::class.java)
    }

    private fun GameInfo.withPrintResult(): GameInfo {
        println("\nresult: `$this`")
        return this
    }

    private fun <T> mockUserInput(
        vararg input: String,
        block: () -> T,
    ): T {
        val testIn = ByteArrayInputStream(input.joinToString("\n").toByteArray())
        System.setIn(testIn)

        return block.invoke()
    }

    @Test
    fun testOneDisplayView() {
        val carRecordView = CarRecordView(GameInfo(2, 5))
        carRecordView.nextRound()
        carRecordView.nextRound()
        carRecordView.nextRound()
        carRecordView.nextRound()
        carRecordView.nextRound()
        carRecordView.hasNextRound() shouldBe false
    }
}
