package calulator

import calculator.Calculator
import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource
import kotlin.test.assertEquals

class CalculatorTest {
    @ParameterizedTest
    @CsvSource(
        "'1 + 1 / 3 * 3',2",
        "'1 - 1',0",
        "'1 * 1',1",
        "'1 / 1',1",
    )
    fun testCalculateCalculator(
        input: String?,
        expected: Double,
    ) {
        val calculator = Calculator()
        val actualValue = calculator.calculator(input)
        assertEquals(actualValue, expected)
    }

    @ParameterizedTest
    @ValueSource(strings = ["=", "1 a 1", "1 -", "- 1", "1+1", "1+ 1", "1 1"])
    fun testFailedCalculator(input: String?) {
        val calculator = Calculator()
        assertThatIllegalArgumentException().isThrownBy { input?.let { calculator.calculator(it) } }
    }
}
