package aoc.day11

import aoc.util.loadResource
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class Day11Test {

    @Test fun parseInput() {
        val lines = loadResource("day11-input").split("\n")

        val monkeys = parseInput(lines)

        assertEquals(8, monkeys.size)

        assertEquals(listOf(65, 78), monkeys[0].items)
        assertEquals(24, monkeys[0].operation(8)) // * 3
        assertTrue(monkeys[0].test(35)) // divisible by 5
        assertFalse(monkeys[0].test(36)) // divisible by 5
        assertEquals(2, monkeys[0].trueDestination)
        assertEquals(3, monkeys[0].falseDestination)

        assertEquals(12, monkeys[1].operation(4)) // + 8
        assertEquals(100, monkeys[6].operation(10)) // old * old
    }
}
