package aoc.day5

import aoc.util.loadResource

data class CrateMove(val count: Int, val from: Int, val to: Int)

fun day5Part1(): String {
    val lines = loadResource("day5-input").split("\n")
    val stackList = getStacksFromInput(lines)

    getMovesFromInput(lines).forEach { move -> applyMove(stackList, move) }

    return stackList.map { it.first() }.joinToString("")
}

fun day5Part2(): String {
    val lines = loadResource("day5-input").split("\n")
    val stackList = getStacksFromInput(lines)

    getMovesFromInput(lines).forEach {
        applyMoveMulti(stackList, it)
    }

    return stackList
        .filter { !it.isEmpty() }
        .map { s -> s.first() }.joinToString("")
}

fun getMovesFromInput(input: List<String>): List<CrateMove> {
    val moveRegex = Regex("""move (\d+) from (\d+) to (\d+)""")

    return input
        .filter { line -> line.startsWith("move") }
        .map{ line -> moveRegex.find(line)!! }
        .map { mr -> CrateMove(mr.groupValues[1].toInt(), mr.groupValues[2].toInt(), mr.groupValues[3].toInt()) }
        .toList()
}

fun getStacksFromInput(input: List<String>): List<ArrayDeque<Char>> {
    val reversedLines = input
        .takeWhile { line -> ! line.matches(Regex(""" \d.*""")) }
        .reversed()

    val numStacks = (reversedLines[0].length + 1) / 4 // 3 chars per stack separated by spaces
    val stackList = List(numStacks) { ArrayDeque<Char>() }

    // for each line in reversedlines pick out the char
    for (line in reversedLines) {
        for (i in 0 until numStacks) {
            val charIndex = (4 * i) + 1
            val char = line[charIndex]
            if (char != ' ') {
                stackList[i].addFirst(char)
            }
        }
    }

    return stackList
}

/**
 * Mutate the supplied stacklist using the move. Moves a single crate at a time
 */
fun applyMove(stackList: List<ArrayDeque<Char>>, move: CrateMove) {
    // Mutates the stacks which is kinda ugly
    val fromStack = stackList[move.from - 1]
    val toStack = stackList[move.to - 1]

    for (i in 0 until move.count) {
        toStack.addFirst(fromStack.removeFirst())
    }
}

/**
 * Mutate the supplied stacklist using the move. Moves all crates at once
 */
fun applyMoveMulti(stackList: List<ArrayDeque<Char>>, move: CrateMove) {
    // Mutates the stacks which is kinda ugly
    val fromStack = stackList[move.from - 1]
    val toStack = stackList[move.to - 1]

    toStack.addAll(0, fromStack.take(move.count))

    (0 until move.count).forEach { fromStack.removeFirst() }

}