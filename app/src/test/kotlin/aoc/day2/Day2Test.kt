package aoc.day2

import kotlin.test.Test
import kotlin.test.assertEquals

class Day2Test {

    @Test fun victoryScore_win() {
        assertEquals(6, victoryScore(Move.ROCK, Move.PAPER))
    }

    @Test fun victoryScore_tie() {
        assertEquals(3, victoryScore(Move.ROCK, Move.ROCK))
    }

    @Test fun victoryScore_loss() {
        assertEquals(0, victoryScore(Move.PAPER, Move.ROCK))
    }

    @Test fun roundScore1() {
        assertEquals(8, roundScore(Move.ROCK, Move.PAPER))
    }

    @Test fun roundScore2() {
        assertEquals(1, roundScore(Move.PAPER, Move.ROCK))
    }

    @Test fun roundScore3() {
        assertEquals(6, roundScore(Move.SCISSORS, Move.SCISSORS))
    }
}