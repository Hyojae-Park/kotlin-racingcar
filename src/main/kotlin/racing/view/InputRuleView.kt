package racing.view

data class GameInfo(val carNames: String, val rounds: Int)

class InputRuleView {
    fun inputGameInfo(): GameInfo {
        println("경주할 자동차 이름을 입력하세요(이름은 쉼표(,)를 기준으로 구분).")
        val cars = readln()
        println("시도할 횟수는 몇 회인가요?")
        val rounds = readln().toInt()

        val info = GameInfo(cars, rounds)

        return info
    }
}
