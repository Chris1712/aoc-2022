package aoc

import kotlin.test.Test
import kotlin.test.assertEquals

class Day6Test {

    @Test fun getPositionOfNDistinct() {
        assertEquals(7, getPositionOfNDistinct("mjqjpqmgbljsphdztnvjfqwrcgsmlb", 4))
    }

    @Test fun getPositionOf4Diff_2() {
        assertEquals(5, getPositionOfNDistinct("bvwbjplbgvbhsrlpgdmjqwftvncz", 4))
    }

    @Test fun getPositionOf14Diff() {
        assertEquals(26, getPositionOfNDistinct("zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw", 14))
    }

}
