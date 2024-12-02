package calculator

class Calculator {
    fun calculator(input: String?): Double {
        val inputParams = input?.split(" ") ?: emptyList()

        val iterator = inputParams.listIterator()

        if (iterator.hasNext().not()) {
            throw IllegalArgumentException("입력 데이터가 없어요")
        }

        val param = iterator.next()

        return try {
            val initValue = param.toDouble()
            calculateRecursive(initValue, iterator)
        } catch (e: Exception) {
            throw IllegalArgumentException(e)
        }
    }

    private fun calculateRecursive(
        lValue: Double,
        iterator: ListIterator<String>,
    ): Double {
        if (iterator.hasNext().not()) return lValue
        val strOperator = iterator.next()

        val rValue =
            try {
                iterator.next().toDouble()
            } catch (e: Exception) {
                throw IllegalArgumentException("rValue 가 확인 할 수 없습니다..", e)
            }

        val resultValue = operateCalculator(lValue, rValue, strOperator)

        return if (iterator.hasNext()) {
            calculateRecursive(resultValue, iterator)
        } else {
            resultValue
        }
    }

    private fun operateCalculator(
        lValue: Double,
        rValue: Double,
        operator: String,
    ): Double {
        return when (operator) {
            "+" -> lValue + rValue
            "-" -> lValue - rValue
            "*" -> lValue * rValue
            "/" -> lValue / rValue
            else -> throw IllegalArgumentException("")
        }
    }
}
