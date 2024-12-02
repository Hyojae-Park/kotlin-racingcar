package racing.view

data class GameInfo(val cars: Int, val rounds: Int)

class InputRuleView {
    fun inputGameInfo(): GameInfo {
//        val value: String? = readLine()
        println("자동차 대수는 몇 대인가요?")
        val cars = readln().toInt()
        println("시도할 횟수는 몇 회인가요?")
        val rounds = readln().toInt()

        val info = GameInfo(cars, rounds)

        return info
    }
}
