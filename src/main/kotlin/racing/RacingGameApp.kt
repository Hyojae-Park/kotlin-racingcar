package racing

import racing.controller.RacingController
import racing.view.CarRecordView
import racing.view.InputRuleView

class RacingGameApp {
    private val ruleView = InputRuleView()

    // input info
    private val gameInfo = ruleView.inputGameInfo()

    // make domain (model) -> unit test
    private val carRecordView = CarRecordView(gameInfo, RacingController())

    // output racing result
    fun startGame() {
        println("실행 결과")
        repeat(gameInfo.rounds) {
            carRecordView.nextRound()
        }
    }
}

fun main() {
    val game = RacingGameApp()

    game.startGame()
}
