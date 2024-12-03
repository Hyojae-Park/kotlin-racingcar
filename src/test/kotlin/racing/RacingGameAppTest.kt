package racing

import io.kotest.matchers.be
import io.kotest.matchers.shouldBe
import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.junit.jupiter.api.Test
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
        val carNames = "pobi,crong,honux"
        val carRecordView = CarRecordView(GameInfo(carNames, input))
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
    fun `CarModel 위치 생성자 테스트`(input: Int) {
        val carModel =
            CarModel(
                input.toString(),
                controller = RacingController(),
                initPosition = input,
            )
        carModel.currentPosition shouldBe input
    }

    @ParameterizedTest
    @ValueSource(strings = ["1", "22", "333", "4444", "55555"])
    fun `CarModel 이름 생성자 성공 테스트`(input: String) {
        // - 각 자동차에 이름을 부여할 수 있다. 자동차 이름은 5자를 초과할 수 없다.
        val carModel = CarModel(input, controller = RacingController())
        be(carModel)
    }

    @ParameterizedTest
    @ValueSource(strings = ["1234567", "12345678", "123456789", "1234567890"])
    fun `CarModel 이름 생성자 실패 테스트`(input: String) {
        // - 각 자동차에 이름을 부여할 수 있다. 자동차 이름은 5자를 초과할 수 없다.
        assertThatIllegalArgumentException().isThrownBy { CarModel(input, controller = RacingController()) }
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
