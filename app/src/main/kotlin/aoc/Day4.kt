package aoc

fun day4Part1(): Int {
    return loadResource("day4-input")
        .split("\n")
        .filter { line -> line.isNotEmpty() }
        .filter { line -> checkForContainedRange(line) }
        .count()
}

fun checkForContainedRange(line: String): Boolean {
    // Given eg '2-4,6-8'  determines if one range subsumes the other
    val positions = line
        .split(',')
        .flatMap { s -> s.split('-') }
        .map { s -> s.toInt() }
        .toList()

    return (positions[0] <= positions[2] && positions[1] >= positions[3]) // Second range inside first
            || (positions[2] <= positions[0] && positions[3] >= positions[1]) // First range inside second
}