package aoc.util

import kotlin.test.Test
import kotlin.test.assertEquals

class UtilTest {
    @Test fun loadResource() {
        assertEquals("this is some text", loadResource("greeting"))
    }
}
