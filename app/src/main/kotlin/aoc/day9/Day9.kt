package aoc.day9

import aoc.util.loadResource
import java.lang.Math.abs

data class Coord(val x: Int, val y: Int)

fun day9Part1(): Int {
    val lines = loadResource("day9-input").split("\n").filter(String::isNotBlank)
    return countTailPositions(lines)
}

fun countTailPositions(lines: List<String>): Int {
    var headPos = Coord(0, 0)
    var tailPos = Coord(0, 0)
    val tailPositions = mutableSetOf<Coord>(tailPos)

    for (line in lines) {
        val (direction, distance) = line.split(" ")
        val distanceInt = distance.toInt()

        repeat (distanceInt) {
            val movedPair = moveHeadAndTail(headPos, tailPos, direction)
            headPos = movedPair.first
            tailPos = movedPair.second
            tailPositions.add(tailPos)
            println(movedPair)
        }
    }

    return tailPositions.size
}

fun moveHeadAndTail(headPos: Coord, tailPos: Coord, direction: String): Pair<Coord, Coord> {
    val newHeadPos = move(headPos, direction)
    val newTailPos: Coord

    if ( abs(newHeadPos.x - tailPos.x) <= 1 && abs(newHeadPos.y - tailPos.y) <= 1) {
        newTailPos = tailPos
    } else {
        // The head needs to move. If col or row is the same, move the tail in the same direction.
        if (tailPos.x == newHeadPos.x || tailPos.y == newHeadPos.y) {
            newTailPos = move(tailPos, direction)
        } else {
            // The head needs to move, and the tail is diagonal. Move the tail towards the head.
            newTailPos = when (direction) {
                "U" -> move(newHeadPos, "D")
                "D" -> move(newHeadPos, "U")
                "L" -> move(newHeadPos, "R")
                "R" -> move(newHeadPos, "L")
                else -> throw Exception("Invalid direction: $direction")
            }
        }


    }

    return Pair(newHeadPos, newTailPos)
}

fun move(pos: Coord, direction: String): Coord {
    return when (direction) {
        "U" -> Coord(pos.x, pos.y + 1)
        "D" -> Coord(pos.x, pos.y - 1)
        "L" -> Coord(pos.x - 1, pos.y)
        "R" -> Coord(pos.x + 1, pos.y)
        else -> throw IllegalArgumentException("Invalid direction: $direction")
    }
}