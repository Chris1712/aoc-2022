package aoc.day4

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

    @Test fun checkForOverlap_true() {
        assertTrue(checkForOverlap("3-4,1-10"))
    }

    @Test fun checkForOverlap_true2() {
        assertTrue(checkForOverlap("3-4,4-8"))
    }

    @Test fun checkForOverlap_true3() {
        assertTrue(checkForOverlap("4-4,4-8"))
    }

    @Test fun checkForOverlap_false() {
        assertFalse(checkForOverlap("3-5,6-10"))
    }

    @Test fun checkForOverlap_false2() {
        assertFalse(checkForOverlap("3-5,1-2"))
    }

}