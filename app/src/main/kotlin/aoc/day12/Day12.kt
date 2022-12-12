package aoc.day12

import aoc.util.loadResource

fun day12Part1(): Int {
    val input = loadResource("day12-input").split("\n").filter { it.isNotEmpty() }
    val stepGrid = findPath(input, 'S', ::canReach)
    val ePosition = findChar(input, 'E')
    return stepGrid[ePosition.first][ePosition.second]
}

fun day12Part2(): Int {
    val input = loadResource("day12-input").split("\n").filter { it.isNotEmpty() }
    return shortestReversePath(input)
}

fun shortestReversePath(input: List<String>): Int {
    val stepGrid = findPath(input, 'E', ::canReachReverse)

    var shortestPath = Int.MAX_VALUE
    for (i in stepGrid.indices) {
        for (j in stepGrid[i].indices) {
            if (input[i][j] == 'a'  && stepGrid[i][j] >= 0) {
                shortestPath = Math.min(shortestPath, stepGrid[i][j])
            }
        }
    }
    return shortestPath
}

fun findPath(input: List<String>, startChar: Char, canStep: (Char, Char) -> Boolean): List<List<Int>> {
    // grid of minimal steps to each position
    var stepGrid: List<MutableList<Int>> = List(input.size) { MutableList(input[0].length) { -1 } }

    val startPosition = findChar(input, startChar)
    stepGrid[startPosition.first][startPosition.second] = 0


    var iteration = 0
    var blocked = false
    while (!blocked) {
        // copy existing step grid
        val newStepGrid = stepGrid.map { it.toMutableList() }

        // iterate through the each positions on the step grid and see where else we can step
        blocked = true // assume we've found no new steps
        for (i in stepGrid.indices) {
            for (j in stepGrid[i].indices) {
                if (stepGrid[i][j] == iteration) {
                    // this is a position we can reach in iteration steps
                    // check the 4 adjacent positions
                    if (i > 0 && stepGrid[i-1][j] == -1 && canStep(input[i][j], input[i-1][j])) {
                        newStepGrid[i-1][j] = iteration + 1
                        blocked = false
                    }
                    if (i < stepGrid.size - 1 && stepGrid[i+1][j] == -1 && canStep(input[i][j], input[i+1][j])) {
                        newStepGrid[i+1][j] = iteration + 1
                        blocked = false
                    }
                    if (j > 0 && stepGrid[i][j-1] == -1 && canStep(input[i][j], input[i][j-1])) {
                        newStepGrid[i][j-1] = iteration + 1
                        blocked = false
                    }
                    if (j < stepGrid[i].size - 1 && stepGrid[i][j+1] == -1 && canStep(input[i][j], input[i][j+1])) {
                        newStepGrid[i][j+1] = iteration + 1
                        blocked = false
                    }
                }
            }
        }

        stepGrid = newStepGrid
        iteration++
    }

    return stepGrid
}

fun findChar(input: List<String>, target: Char): Pair<Int, Int> {
    for (i in input.indices) {
        for (j in input[i].indices) {
            if (input[i][j] == target) {
                return Pair(i, j)
            }
        }
    }
    throw IllegalArgumentException("No $target found in input")
}

fun canReach(currHeight: Char, destHeight: Char): Boolean {
    val effectiveCurr = if (currHeight == 'S') {
        'a'
    } else {
        currHeight
    }

    val effectiveDest = if (destHeight == 'E') {
        'z'
    } else {
        destHeight
    }
    return effectiveCurr + 1 >= effectiveDest
}

fun canReachReverse(currHeight: Char, destHeight: Char): Boolean {
    val effectiveCurr = when (currHeight) {
        'S' -> 'a'
        'E' -> 'z'
        else -> currHeight
    }

    val effectiveDest = when (destHeight) {
        'S' -> 'a'
        'E' -> 'z'
        else -> destHeight
    }

    return effectiveDest + 1 >= effectiveCurr || effectiveDest >= effectiveCurr
}
