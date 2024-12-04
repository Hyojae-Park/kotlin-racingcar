package racing.domain

class RaceResult(cars: List<CarModel>) {
    private val winPosition: Int = cars.maxOf { it.currentPosition }
    private val winners: List<CarModel> = cars.filter { it.currentPosition == winPosition }.toList()

    fun getWinners(): List<CarModel> {
        return winners
    }

    fun getCarNames(): String {
        return winners.joinToString(
            separator = ",",
            transform = {
                it.name
            },
        )
    }
}
