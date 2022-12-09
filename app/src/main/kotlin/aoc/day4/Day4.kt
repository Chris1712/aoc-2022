package aoc.day4

import aoc.util.loadResource

fun day4Part1(): Int {
    return loadResource("day4-input")
        .split("\n")
        .filter { line -> line.isNotEmpty() }
        .filter { line -> checkForContainedRange(line) }
        .count()
}

fun day4Part2(): Int {
    return loadResource("day4-input")
        .split("\n")
        .filter { line -> line.isNotEmpty() }
        .filter { line -> checkForOverlap(line) }
        .count()
}

fun getPositions(line: String): List<Int> {
    return line
        .split(',')
        .flatMap { s -> s.split('-') }
        .map { s -> s.toInt() }
        .toList()
}

fun checkForContainedRange(line: String): Boolean {
    // Given eg '2-4,6-8'  determines if one range subsumes the other
    val positions = getPositions(line)

    return (positions[0] <= positions[2] && positions[1] >= positions[3]) // Second range inside first
            || (positions[2] <= positions[0] && positions[3] >= positions[1]) // First range inside second
}

fun checkForOverlap(line: String): Boolean {
    // Given eg '2-4,6-8'  determines if one range overlaps the other
    val positions = getPositions(line)

    // We overlap when the start of range 1 is before the end of R2, and vice versa. Or... more vice versa

    return (positions[0] <= positions[3] && positions[1] >= positions[2])
            || (positions[3] <= positions[0] && positions[2] >= positions[1])
}