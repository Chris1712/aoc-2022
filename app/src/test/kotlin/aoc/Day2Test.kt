package aoc

import kotlin.test.Test
import kotlin.test.assertEquals

class Day2Test {

    @Test fun singleResultWin() {
        assertEquals(6, resultScore('A', 'Y'))
    }

    @Test fun singleResultTie() {
        assertEquals(3, resultScore('A', 'X'))
    }

    @Test fun singleResultLoss() {
        assertEquals(0, resultScore('B', 'A'))
    }

    @Test fun roundScore1() {
        assertEquals(8, roundScore('A', 'Y'))
    }

    @Test fun roundScore2() {
        assertEquals(1, roundScore('B', 'X'))
    }

    @Test fun roundScore3() {
        assertEquals(6, roundScore('C', 'Z'))
    }
}