package racing.view

import racing.controller.GameController
import racing.controller.RacingController
import racing.domain.CarModel
import racing.domain.RaceResult

class CarRecordView(private val gameInfo: GameInfo, private val controller: GameController = RacingController()) {
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

    fun hasNextRound(): Boolean {
        return (currentRound < gameInfo.rounds)
    }

    fun nextRound() {
        check(hasNextRound())
        currentRound++
        for (car in cars) {
            car.nextState()
        }

        printRecord()
    }

    fun getWinners(): List<CarModel> {
        return RaceResult(cars).getWinners()
    }

    fun getWinnerNames(): String {
        return RaceResult(cars).getCarNames()
    }

    private fun printRecord() {
        for (car in cars) {
            printCar(car)
        }
        println()
    }

    private fun printCar(car: CarModel) {
        print("${car.name} : ")
        repeat(car.currentPosition) {
            print("-")
        }
        println()
    }

    companion object {
        private const val DEFAULT_DELIMITER = ","
    }
}
