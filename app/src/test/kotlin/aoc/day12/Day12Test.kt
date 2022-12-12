package aoc.day12

import aoc.util.loadResource
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class Day12Test {

    @Test fun canReach() {
        assertTrue(canReach('a', 'b') )
        assertTrue(canReach('b', 'a') )
        assertTrue(canReach('a', 'a') )
        assertFalse(canReach('a', 'c') )
        assertFalse(canReach('a', 'E') )
        assertFalse(canReach('S', 'E') )
        assertTrue(canReach('S', 'a') )
        assertTrue(canReach('S', 'b') )
        assertFalse(canReach('S', 'c') )
        assertFalse(canReach('x', 'E') )
        assertTrue(canReach('y', 'E') )
        assertTrue(canReach('z', 'E') )
    }

    @Test fun findS() {
        val input = loadResource("day12-input").split("\n").filter { it.isNotEmpty() }

        assertEquals(Pair(20, 0), findChar(input, 'S'))
    }

    @Test fun findS_2() {
        val input = listOf(
            "aaaabaa",
            "aaaScaa",
            "aaaaaza",
        )

        assertEquals(Pair(1, 3), findChar(input, 'S'))
    }

    @Test fun findPath() {
        val input = listOf(
            "aaaabaa",
            "aaaScaa",
            "aaaaaza",
        )

        val completedStepGrid = findPath(input)

        assertEquals(0, completedStepGrid[1][3]) // Starting point
        assertEquals(1, completedStepGrid[1][2]) // First step left
        assertEquals(1, completedStepGrid[0][3]) // First step up
        assertEquals(3, completedStepGrid[1][4]) // Step up, left, down
        assertEquals(-1, completedStepGrid[2][5]) // Unreachable z

    }

    @Test fun findPath_2() {
        val input = listOf(
            "Sabqponm",
            "abcryxxl",
            "accszExk",
            "acctuvwj",
            "abdefghi",
        )

        val stepGrid = findPath(input)

        assertEquals(0, stepGrid[0][0]) // Starting point
        assertEquals(31, stepGrid[2][5]) // E
    }

}
