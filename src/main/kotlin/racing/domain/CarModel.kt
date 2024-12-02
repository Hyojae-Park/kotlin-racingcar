package racing.domain

import racing.controller.GameController

/*
자동차 정보 클래스
- 자동차의 이름 등 특징을 갖는 클래스 : 하위 클래스로 구현
- 사용자 입력(random)에 대해 다음 동작을 결정하는 함수 필요
 */
class CarModel(private val name: String, private val controller: GameController, initPosition: Int = 0) {
    var currentPosition: Int = initPosition
        private set

    fun nextState() {
        val inputNumber = controller.generateRandomNumber()
        currentPosition += if (inputNumber >= INPUT_THRESHOLD) 1 else 0
    }

    companion object {
        private const val INPUT_THRESHOLD = 4
    }
}
