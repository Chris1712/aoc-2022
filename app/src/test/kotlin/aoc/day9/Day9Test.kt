package aoc.day9

import kotlin.test.Test
import kotlin.test.assertEquals

class Day9Test {
    @Test fun countTailPositions() {
        val testData = listOf(
            "R 4",
            "U 4",
            "L 3",
            "D 1",
            "R 4",
            "D 1",
            "L 5",
            "R 2",
        )

        assertEquals(13, countNthTailPositions(testData, 1))
    }

    @Test fun countTailPositionsLong() {
        val testData = listOf(
            "R 5",
            "U 8",
            "L 8",
            "D 3",
            "R 17",
            "D 10",
            "L 25",
            "U 20"
        )

        assertEquals(36, countNthTailPositions(testData, 9))
    }


}
