package aoc

import kotlin.test.Test
import kotlin.test.assertEquals

class Day6Test {

    @Test fun getPositionOf4Diff() {
        assertEquals(7, getPositionOf4Diff("mjqjpqmgbljsphdztnvjfqwrcgsmlb"))
    }

    @Test fun getPositionOf4Diff_2() {
        assertEquals(5, getPositionOf4Diff("bvwbjplbgvbhsrlpgdmjqwftvncz"))
    }

}
