package aoc

import kotlin.test.Test
import kotlin.test.assertTrue
import kotlin.test.assertFalse

class Day4Test {

    @Test fun checkForContainedRange_true() {
        assertTrue(checkForContainedRange("3-4,1-10"))
    }

    @Test fun checkForContainedRange_true2() {
        assertTrue(checkForContainedRange("3-4,3-4"))
    }

    @Test fun checkForContainedRange_false() {
        assertFalse(checkForContainedRange("3-5,4-10"))
    }

    @Test fun checkForContainedRange_false2() {
        assertFalse(checkForContainedRange("3-5,8-10"))
    }

}