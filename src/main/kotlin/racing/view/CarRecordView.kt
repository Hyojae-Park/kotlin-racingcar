package racing.view

import racing.controller.RacingController
import racing.domain.CarModel

class CarRecordView(private val gameInfo: GameInfo) {
    private val carList: ArrayList<CarModel> = ArrayList(gameInfo.cars)
    private var currentRound: Int = 0

    init {
        for (index in 0 until gameInfo.cars) {
            carList.add(CarModel(index.toString(), RacingController()))
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

    private fun printRecord() {
        for (car in carList) {
            printCar(car.currentPosition)
        }
        println()
    }

    private fun printCar(position: Int) {
        for (i in 0 until position) {
            print("-")
        }
        println()
    }
}
