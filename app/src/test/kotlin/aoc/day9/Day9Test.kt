package aoc.day9

import kotlin.test.Test
import kotlin.test.assertEquals

class Day9Test {
    private val testData = listOf(
        "R 4",
        "U 4",
        "L 3",
        "D 1",
        "R 4",
        "D 1",
        "L 5",
        "R 2",
    )

    @Test fun countTailPositions() {
        assertEquals(13, countTailPositions(testData))
    }


}
