package aoc

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class Day8Test {
    private val testData = listOf(
        "30373",
        "25512",
        "65332",
        "33549",
        "35390"
    )

    @Test fun treeLinesToGrid() {
        val treeGrid = treeLinesToGrid(testData)

        assertEquals(3, treeGrid[0][0])
        assertEquals(0, treeGrid[0][1])
        assertEquals(2, treeGrid[1][0])
        assertEquals(0, treeGrid[4][4])
    }


    @Test fun checkTreeVisibility() {
        val treeGrid = treeLinesToGrid(testData)

        assertTrue(checkTreeVisibility(treeGrid, 0, 0))
        assertTrue(checkTreeVisibility(treeGrid, 1, 1))
        assertFalse(checkTreeVisibility(treeGrid, 1, 3))
    }

    @Test fun getVisibleTrees() {
        assertEquals(21, getVisibleTrees(testData))
    }

    // Part 2

    @Test fun getBestScenicScore() {
        assertEquals(8, getBestScenicScore(testData))
    }


    @Test fun getTreeScenicScore() {
        val treeGrid = treeLinesToGrid(testData)
        assertEquals(4, getTreeScenicScore(treeGrid, 1, 2))
    }

    @Test fun getTreeScenicScore_2() {
        val treeGrid = treeLinesToGrid(testData)
        assertEquals(8, getTreeScenicScore(treeGrid, 3, 2))
    }



}
