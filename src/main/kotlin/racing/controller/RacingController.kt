package racing.controller

interface GameController {
    fun generateRandomNumber(): Int
}

class RacingController : GameController {
    override fun generateRandomNumber(): Int {
        return (0..9).random()
    }
}
