package racing.view

import racing.controller.GameController
import racing.controller.RacingController
import racing.domain.CarModel

class CarRecordView(private val gameInfo: GameInfo, private val controller: GameController = RacingController()) {
    private val carList =
        List(gameInfo.cars) { index ->
            CarModel(name = index.toString(), controller = controller)
        }
    private var currentRound: Int = 0

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

    private fun printRecord() {
        for (car in carList) {
            printCar(car.currentPosition)
        }
        println()
    }

    private fun printCar(position: Int) {
        repeat(position) {
            print("-")
        }
        println()
    }
}
