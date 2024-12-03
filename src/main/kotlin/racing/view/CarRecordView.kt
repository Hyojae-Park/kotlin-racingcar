package racing.view

import racing.controller.GameController
import racing.controller.RacingController
import racing.domain.CarModel

class CarRecordView(private val gameInfo: GameInfo, private val controller: GameController = RacingController()) {
    private val carList: List<CarModel>
    private var currentRound: Int = 0

    init {
        val carNames = gameInfo.carNames
        val cars = carNames.split(DEFAULT_DELIMITER)
        carList =
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
        for (car in carList) {
            car.nextState()
        }

        printRecord()
    }

    fun getWinners(): List<CarModel> {
        var winPosition = 0
        val winCars = arrayListOf<CarModel>()
        for (car in carList) {
            when {
                car.currentPosition > winPosition -> {
                    winPosition = car.currentPosition
                    winCars.clear()
                    winCars.add(car)
                }
                car.currentPosition == winPosition -> {
                    winCars.add(car)
                }
            }
        }

        return winCars
    }

    fun getWinnerNames(): String {
        val winners = getWinners()

        return winners.joinToString(
            separator = ",",
            postfix = "가 최종 우승했습니다.",
            transform = {
                it.name
            },
        )
    }

    private fun printRecord() {
        for (car in carList) {
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
