package aoc

import kotlin.test.Test
import kotlin.test.assertEquals

class Day7Test {

    @Test fun getDirSizes() {
        val testData = """
            $ cd /
            $ ls
            dir a
            14848514 b.txt
            8504156 c.dat
            dir d
            $ cd a
            $ ls
            dir e
            29116 f
            2557 g
            62596 h.lst
            $ cd e
            $ ls
            584 i
            $ cd ..
            $ cd ..
            $ cd d
            $ ls
            4060174 j
            8033020 d.log
            5626152 d.ext
            7214296 k
        """.trimIndent().split("\n")

        val result = sumDirs(testData)

        assertEquals(584, result["e"])
        assertEquals(94853, result["a"])
        assertEquals(24933642, result["d"])
        assertEquals(48381165, result["/"])
    }

}
