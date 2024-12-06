package racing.domain

import racing.controller.GameController
import racing.controller.RacingController
import racing.view.GameInfo

/*
car list 를 멤버로 가지며
이동 로직(랜덤 숫자로 이동) 에 대한 처리를 함
 */
class RacingGame(private val gameInfo: GameInfo, private val controller: GameController = RacingController()) {
    private val cars: List<CarModel>
    private var currentRound: Int = 0

    init {
        val carNames = gameInfo.carNames
        val cars = carNames.split(DEFAULT_DELIMITER)
        this.cars =
            List(cars.size) { index ->
                CarModel(inputName = cars[index], controller = controller)
            }
    }

    fun getCarCount(): Int {
        return cars.size
    }

    fun getCar(index: Int): CarModel? {
        return cars.getOrNull(index)
    }

    fun hasNextRound(): Boolean {
        return (currentRound < gameInfo.rounds)
    }

    fun nextRound() {
        check(hasNextRound())
        currentRound++
        for (car in cars) {
            car.nextState()
        }
    }

    fun getWinners(): List<CarModel> {
        return RaceResult(cars).getWinners()
    }

    fun getWinnerNames(): String {
        return RaceResult(cars).getCarNames()
    }

    companion object {
        private const val DEFAULT_DELIMITER = ","
    }
}
