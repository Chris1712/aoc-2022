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

        assertEquals(listOf( 65L, 78L), monkeys[0].items)
        assertEquals(24, monkeys[0].operation(8)) // * 3
        assertTrue(monkeys[0].test(35)) // divisible by 5
        assertFalse(monkeys[0].test(36)) // divisible by 5
        assertEquals(2, monkeys[0].trueDestination)
        assertEquals(3, monkeys[0].falseDestination)

        assertEquals(12, monkeys[1].operation(4)) // + 8
        assertEquals(100, monkeys[6].operation(10)) // old * old
    }

    @Test fun monkeyRound() {
        val lines = loadResource("day11-test-input").split("\n")
        val monkeys = parseInput(lines)
        round(monkeys, 3)

        assertEquals(listOf(20L, 23L, 27L ,26L ), monkeys[0].items)
        assertEquals(listOf(2080L, 25L, 167L, 207L, 401L, 1046), monkeys[1].items)
        assertTrue( monkeys[2].items.isEmpty())
        assertTrue( monkeys[3].items.isEmpty())
    }

    @Test fun monkeyBusiness() {
        val lines = loadResource("day11-test-input").split("\n")
        val monkeys = parseInput(lines)

        assertEquals(10605, getMonkeyBusiness(monkeys, 20, 3))
    }

    @Test fun monkeyBusinessPart2() {
        val lines = loadResource("day11-test-input").split("\n")
        val monkeys = parseInput(lines)

        assertEquals(2713310158L, getMonkeyBusiness(monkeys, 10000, 1))
    }
}
