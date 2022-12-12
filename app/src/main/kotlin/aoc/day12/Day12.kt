package aoc.day12

import aoc.util.loadResource

fun day12Part1(): Int {
    val input = loadResource("day12-input").split("\n").filter { it.isNotEmpty() }
    val stepGrid = findPath(input)
    val ePosition = findChar(input, 'E')
    return stepGrid[ePosition.first][ePosition.second]
}

fun findPath(input: List<String>): List<List<Int>> {
    // grid of minimal steps to each position
    var stepGrid: List<MutableList<Int>> = List(input.size) { MutableList(input[0].length) { -1 } }
    // Start at S

    val sPosition = findChar(input, 'S')
    stepGrid[sPosition.first][sPosition.second] = 0

    println("Start! Initial stepGrid:")
    println(stepGrid.joinToString("\n") { it.joinToString("") })

    // iterate through the stepMap, generate a new one
    var iteration = 0
    var blocked = false
    while (!blocked) {
        // copy existing step grid
        val newStepGrid = stepGrid.map { it.toMutableList() }

        blocked = true // assume we've found no new steps
        for (i in stepGrid.indices) {
            for (j in stepGrid[i].indices) {
                if (stepGrid[i][j] == iteration) {
                    // this is a position we can reach in iteration steps
                    // check the 4 adjacent positions
                    if (i > 0 && stepGrid[i-1][j] == -1 && canReach(input[i][j], input[i-1][j])) {
                        newStepGrid[i-1][j] = iteration + 1
                        blocked = false
//                        println("Updated ${i-1},${j} to ${iteration+1}")
                    }
                    if (i < stepGrid.size - 1 && stepGrid[i+1][j] == -1 && canReach(input[i][j], input[i+1][j])) {
                        newStepGrid[i+1][j] = iteration + 1
                        blocked = false
//                        println("Updated ${i+1},${j} to ${iteration+1}")
                    }
                    if (j > 0 && stepGrid[i][j-1] == -1 && canReach(input[i][j], input[i][j-1])) {
                        newStepGrid[i][j-1] = iteration + 1
                        blocked = false
//                        println("Updated ${i},${j-1} to ${iteration+1}")
                    }
                    if (j < stepGrid[i].size - 1 && stepGrid[i][j+1] == -1 && canReach(input[i][j], input[i][j+1])) {
                        newStepGrid[i][j+1] = iteration + 1
                        blocked = false
//                        println("Updated ${i},${j+1} to ${iteration+1}")
                    }
                }
            }
        }

        stepGrid = newStepGrid
        iteration++
//        println("After iteration $iteration, stepGrid is:")
//        println(stepGrid.joinToString("\n") { it.joinToString("") })
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
