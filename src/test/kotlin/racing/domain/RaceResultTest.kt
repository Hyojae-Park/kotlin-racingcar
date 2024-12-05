package racing.domain

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class RaceResultTest {
  /*
  [x] 경기 결과를 테스트 한다 : 입력 간소화
  [] 경기 결과 우승 대수를 확인 한다
   */

    @Test
    fun `경기 결과를 테스트 한다`() {
        val cars =
            arrayListOf(
                CarModel("1", initPosition = 1),
                CarModel("2", initPosition = 2),
                CarModel("3", initPosition = 3),
            )
        val raceResult = RaceResult(cars)
        raceResult.getCarNames() shouldBe "3"
    }

    @Test
    fun `경기 결과 우승 대수를 확인 한다`() {
        val cars =
            arrayListOf(
                CarModel("1", initPosition = 1),
                CarModel("2", initPosition = 3),
                CarModel("3", initPosition = 3),
            )
        val raceResult = RaceResult(cars)
        raceResult.getWinners().size shouldBe 2
    }
}
