package racing

import io.kotest.matchers.shouldBe
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource
import racing.controller.RacingController
import racing.domain.CarModel
import racing.view.CarRecordView
import racing.view.GameInfo

class RacingGameAppTest {
    //    모든 로직에 단위 테스트를 구현
    // CarModel : nextState
    // CarRecordView : nextRound

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
    fun `입력 숫자에 대한 이동 여부 테스트`(
        input: Int,
        expectedMoved: Boolean,
    ) {
        val initPosition = 5
        val carModel =
            CarModel(
                input.toString(),
                { input },
                initPosition,
            )
        carModel.nextState()
        (initPosition != carModel.currentPosition) shouldBe expectedMoved
    }

    @ParameterizedTest
    @ValueSource(ints = [2, 3, 4, 5])
    fun `CarModle 위치 생성자 테스트`(input: Int) {
        val carModel =
            CarModel(
                name = input.toString(),
                controller = RacingController(),
                initPosition = input,
            )
        carModel.currentPosition shouldBe input
    }
}
