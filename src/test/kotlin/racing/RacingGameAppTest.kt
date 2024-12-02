package racing

import io.kotest.matchers.shouldBe
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource
import racing.controller.GameController
import racing.domain.CarModel
import racing.view.CarRecordView
import racing.view.GameInfo

class RacingGameAppTest {
    //    모든 로직에 단위 테스트를 구현
    // CarModel : nextState
    // CarRecordView : nextRound

    class TestController(val fixedNumber: Int) : GameController {
        override fun generateRandomNumber(): Int {
            return fixedNumber
        }
    }

    @ParameterizedTest
    @ValueSource(ints = [2, 3, 4, 5])
    fun `라운드 완료 테스트`(input: Int) {
        val carRecordView = CarRecordView(GameInfo(1, input))
        repeat(input) {
            carRecordView.nextRound()
        }
        carRecordView.hasNextRound() shouldBe false
    }

    @ParameterizedTest
    @CsvSource(
        "3, false",
        "5, true",
    )
    fun `입력 값 이동 테스트`(
        input: Int,
        expectedMoved: Boolean,
    ) {
        val initPosition = 5
        val carModel = CarModel(input.toString(), TestController(input), initPosition)
        carModel.nextState()
        (initPosition != carModel.currentPosition) shouldBe expectedMoved
    }
}
