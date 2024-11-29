package racing

import racing.view.CarRecordView
import racing.view.InputRuleView

class RacingGameApp {
    private val ruleView = InputRuleView()

    // input info
    private val gameInfo = ruleView.inputGameInfo()

    // make domain (model) -> unit test
    private val carRecordView = CarRecordView(gameInfo)

    // output racing result
    fun startGame() {
        println("실행 결과")
        for (round in 0 until gameInfo.rounds)
            carRecordView.nextRound()
    }
}

fun main() {
    val game = RacingGameApp()

    game.startGame()
}
