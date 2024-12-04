package racing.domain

import io.kotest.matchers.be
import io.kotest.matchers.shouldBe
import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource

class CarModelTest {
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
                initPosition = input,
            )
        carModel.currentPosition shouldBe input
    }

    @ParameterizedTest
    @ValueSource(strings = ["1", "22", "333", "4444", "55555"])
    fun `각 자동차에 이름을 부여할 수 있다`(input: String) {
        // - 각 자동차에 이름을 부여할 수 있다. 자동차 이름은 5자를 초과할 수 없다.
        val carModel = CarModel(input)
        be(carModel)
    }

    @ParameterizedTest
    @ValueSource(strings = ["1234567", "12345678", "123456789", "1234567890"])
    fun `자동차 이름은 5자를 초과할 수 없다`(input: String) {
        // - 각 자동차에 이름을 부여할 수 있다. 자동차 이름은 5자를 초과할 수 없다.
        assertThatIllegalArgumentException().isThrownBy { CarModel(input) }
    }
}
