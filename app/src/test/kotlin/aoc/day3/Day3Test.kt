package aoc.day3

import kotlin.test.Test
import kotlin.test.assertEquals

class Day3Test {

    @Test fun itemPrio_a() {
        assertEquals(1, itemPrio('a'))
    }

    @Test fun itemPrio_z() {
        assertEquals(26, itemPrio('z'))
    }

    @Test fun itemPrio_A() {
        assertEquals(27, itemPrio('A'))
    }

    @Test fun itemPrio_Z() {
        assertEquals(52, itemPrio('Z'))
    }

    @Test fun charsInBothHalves() {
        assertEquals(setOf('A'), charsInBothHalves("AbcdeA"))
    }

    @Test fun day3Part2_test1() {
        val elfPacks = listOf(
            "abcABa", "zxaazz", "jkloabanm", // Share a, value 1
            "ZzyZ", "abcdZZuiop", "qwZklZ", // Share Z, value 52
            "bHhb", "bb", "bacAbC" // Share b, value 2
        )

        assertEquals(55, sumScoresForCommon(elfPacks))
    }

    @Test fun day3Part2_test2() {
        // From AoC example
        val elfPacks = listOf(
            "vJrwpWtwJgWrhcsFMMfFFhFp", "jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL", "PmmdzqPrVvPwwTWBwg",
            "wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn", "ttgJtRGJQctTZtZT", "CrZsJsPPZsGzwwsLwLmpwMDw"
        )

        assertEquals(70, sumScoresForCommon(elfPacks))
    }
}