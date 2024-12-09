package racing.view

import racing.controller.RandomNumberInput
import racing.domain.CarModel
import racing.domain.RacingGame

class CarRecordView(gameInfo: GameInfo) {
    private val racingGame = RacingGame(gameInfo, RandomNumberInput())

    fun nextRound() {
        racingGame.nextRound()
        printRecord()
    }

    fun getWinnerNames(): String {
        return racingGame.getWinnerNames()
    }

    private fun printRecord() {
        val carCount = racingGame.getCarCount()
        (0..carCount).forEach { index ->
            val carModel = racingGame.getCar(index)
            carModel?.let {
                printCar(it)
            }
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
}
