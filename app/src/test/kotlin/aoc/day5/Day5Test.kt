package aoc.day5

import kotlin.test.Test
import kotlin.test.assertEquals

class Day5Test {

    @Test fun getMovesFromInput() {
        val moveList = getMovesFromInput(listOf(
                    "move 1 from 7 to 4",
                    "move 1 from 6 to 2",
                    "move 5 from 9 to 4",
                    "move 2 from 2 to 8",
                    "move 2 from 2 to 6",
                    "move 3 from 3 to 7"
        ))

        assertEquals(6, moveList.size)
        assertEquals(CrateMove(1, 7 , 4), moveList[0])
        assertEquals(CrateMove(3, 3 , 7), moveList.last())
    }

    @Test fun getStacksFromInput() {
        val stackList: List<ArrayDeque<Char>> = getStacksFromInput(listOf(
            "[T]     [M]",
            "[Z] [H] [F]",
            "[B] [V] [B]",
            " 1   2   3 "
        ))

        assertEquals(3, stackList.size)

        assertEquals(3, stackList[0].size)  // T Z B
        assertEquals(2, stackList[1].size)  // H V

        assertEquals('T', stackList[0][0])
        assertEquals('Z', stackList[0][1])
        assertEquals('B', stackList[0][2])

        assertEquals('H', stackList[1][0])
        assertEquals('V', stackList[1][1])
    }

    @Test fun applyMove() {
        val stackList: List<ArrayDeque<Char>> = getStacksFromInput(listOf(
            "[T]     [M]",
            "[Z] [H] [F]",
            "[B] [V] [B]",
            " 1   2   3 "
        ))

        applyMove(stackList, CrateMove(2, 1 , 3))

        assertEquals(1, stackList[0].size)
        assertEquals(5, stackList[2].size)
        assertEquals('B', stackList[0][0])
        assertEquals('Z', stackList[2][0])
    }

    @Test fun applyMulti() {
        val stackList: List<ArrayDeque<Char>> = getStacksFromInput(listOf(
            "[T]     [M]",
            "[Z] [H] [F]",
            "[B] [V] [B]",
            " 1   2   3 "
        ))

        applyMoveMulti(stackList, CrateMove(2, 1 , 3))

        assertEquals(1, stackList[0].size)
        assertEquals(5, stackList[2].size)
        assertEquals('B', stackList[0][0])
        assertEquals('T', stackList[2][0])
        assertEquals('Z', stackList[2][1])
        assertEquals('M', stackList[2][2])
    }

}
