package racing

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import racing.view.CarRecordView
import racing.view.GameInfo

class RacingGameAppTest {
    //    모든 로직에 단위 테스트를 구현
    // CarModel : nextState
    // CarRecordView : nextRound

    @ParameterizedTest
    @ValueSource(ints = [2, 3, 4, 5])
    fun `라운드 완료 테스트`(input: Int) {
        val carNames = "pobi,crong,honux"
        val carRecordView = CarRecordView(GameInfo(carNames, input))
        repeat(input) {
            carRecordView.nextRound()
        }
        carRecordView.hasNextRound() shouldBe false
    }

    @Test
    fun `중복 우승자 테스트`() {
        // controller 를 통해 여러 우승자를 만든다, position 0 / max
        val carNames = "pobi,crong,honux"
        val carNumber = 3
        val rounds = 5
        val doneMoveNumber = 3

        val carRecordView = CarRecordView(GameInfo(carNames, rounds), { doneMoveNumber })
        repeat(rounds) {
            carRecordView.nextRound()
        }
        carRecordView.getWinners().size shouldBe carNumber
    }
}
