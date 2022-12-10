package aoc.day9

import aoc.util.loadResource
import kotlin.math.abs

data class Coord(val x: Int, val y: Int)

fun day9Part1(): Int {
    val lines = loadResource("day9-input").split("\n").filter(String::isNotBlank)
    return countNthTailPositions(lines, 1)
}

fun day9Part2(): Int {
    val lines = loadResource("day9-input").split("\n").filter(String::isNotBlank)
    return countNthTailPositions(lines, 9)
}

fun countNthTailPositions(lines: List<String>, ropeLength: Int): Int {
    var headPos = Coord(0, 0)
    val tailPos = MutableList(ropeLength) { Coord(0, 0) }

    val endPositionsHistory = mutableSetOf<Coord>(tailPos.last())

    for (line in lines) {
        val (direction, distance) = line.split(" ")
        val distanceInt = distance.toInt()

        repeat (distanceInt) {
            headPos = moveHead(headPos, direction)
            tailPos[0] = moveTail(headPos, tailPos[0])
            for (i in 1 until ropeLength) {
                tailPos[i] = moveTail(tailPos[i-1], tailPos[i])
            }
            endPositionsHistory.add(tailPos.last())
        }
    }

    return endPositionsHistory.size
}

fun moveTail(headPos: Coord, tailPos: Coord): Coord {
    if (abs(headPos.x - tailPos.x) <= 1 && abs(headPos.y - tailPos.y) <= 1) {
        return tailPos // No movement needed
    } else if (tailPos.x == headPos.x) {
        // Same col, too far apart in y
        return if (tailPos.y < headPos.y) {
            Coord(tailPos.x, tailPos.y + 1)
        } else {
            Coord(tailPos.x, tailPos.y - 1)
        }
    } else if (tailPos.y == headPos.y) {
        // Same row, too far apart in x
        return if (tailPos.x < headPos.x) {
            Coord(tailPos.x + 1, tailPos.y)
        } else {
            Coord(tailPos.x - 1, tailPos.y)
        }
    } else {
        // Diagonal, too far apart in both x and y
        return Coord( // Move towards headPos in both x and y
            if (tailPos.x < headPos.x) tailPos.x + 1 else tailPos.x - 1,
            if (tailPos.y < headPos.y) tailPos.y + 1 else tailPos.y - 1
        )
    }
}

fun moveHead(pos: Coord, direction: String): Coord {
    return when (direction) {
        "U" -> Coord(pos.x, pos.y + 1)
        "D" -> Coord(pos.x, pos.y - 1)
        "L" -> Coord(pos.x - 1, pos.y)
        "R" -> Coord(pos.x + 1, pos.y)
        else -> throw IllegalArgumentException("Invalid direction: $direction")
    }
}