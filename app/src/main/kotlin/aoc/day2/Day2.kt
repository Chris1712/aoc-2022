package aoc.day2

import aoc.util.loadResource

// Scoring
// Pick: 1 rock, 2 paper, 3 scissors
// Result: 0 lose, 3 draw, 6 win
// Rock: A/X Paper: B/Y, Scissors: C/Z

enum class Move {
    ROCK, PAPER, SCISSORS
}

enum class Outcome {
    LOSE, DRAW, WIN
}

/**
 * Interprets first col as move, second col as move
 * Converts EG "A Y" -> (Rock, Paper)
 */
fun translatePart1(row: String): Pair<Move, Move> {
    val opponent = when(row[0]) {
        'A' -> Move.ROCK
        'B' -> Move.PAPER
        'C' -> Move.SCISSORS
        else -> throw Exception("Bad opponent move code ${row[0]}")
    }
    val self = when(row[2]) {
        'X' -> Move.ROCK
        'Y' -> Move.PAPER
        'Z' -> Move.SCISSORS
        else -> throw Exception("Bad self move code ${row[2]}")
    }
    return Pair(opponent, self)
}

/**
 * Interprets first col as move, second col as outcome
 * Still returns move.
 * Converts EG "A Y" -> (Rock, Paper)
 */
fun translatePart2(row: String): Pair<Move, Move> {
    val moveMap = mapOf( // Map of opponent move -> desired outcome -> your move
        Move.ROCK to mapOf(
            Outcome.LOSE to Move.SCISSORS,
            Outcome.DRAW to Move.ROCK,
            Outcome.WIN to Move.PAPER),
        Move.PAPER to mapOf(
            Outcome.LOSE to Move.ROCK,
            Outcome.DRAW to Move.PAPER,
            Outcome.WIN to Move.SCISSORS),
        Move.SCISSORS to mapOf(
            Outcome.LOSE to Move.PAPER,
            Outcome.DRAW to Move.SCISSORS,
            Outcome.WIN to Move.ROCK)
    )

    val opponent = when(row[0]) {
        'A' -> Move.ROCK
        'B' -> Move.PAPER
        'C' -> Move.SCISSORS
        else -> throw Exception("Bad opponent move code ${row[0]}")
    }
    val outcome = when(row[2]) {
        'X' -> Outcome.LOSE
        'Y' -> Outcome.DRAW
        'Z' -> Outcome.WIN
        else -> throw Exception("Bad self move code ${row[2]}")
    }
    return Pair(opponent, moveMap[opponent]!![outcome]!!)
}


fun day2Part1(): Int {
    val lines: List<String> = loadResource("day2-input").split("\n")
    return lines
        .filter { line -> line.isNotEmpty() }
        .map { line -> translatePart1(line) }
        .map{ moves -> roundScore(moves.first, moves.second) }
        .sum()
}

fun day2Part2(): Int {
    val lines: List<String> = loadResource("day2-input").split("\n")
    return lines
        .filter { line -> line.isNotEmpty() }
        .map { line -> translatePart2(line) }
        .map{ moves -> roundScore(moves.first, moves.second) }
        .sum()
}

/**
 * Determine the points scored for winning/losing/drawing
 */
fun victoryScore(opponent: Move, self: Move): Int {
    return if (opponent == self) {
        3 // Tie
    } else if (
        (opponent == Move.ROCK && self == Move.PAPER)
        || (opponent == Move.PAPER && self == Move.SCISSORS)
        || (opponent == Move.SCISSORS && self == Move.ROCK)
    ) {
        6 // We wont!
    } else {
        0 // We lost :'(
    }
}

/**
 * Determine the score for a single round - combines pick score and victory score
 */
fun roundScore(opponent: Move, self: Move): Int {
    val pickScore = when(self) {
        Move.ROCK -> 1
        Move.PAPER -> 2
        Move.SCISSORS -> 3
    }
    return pickScore + victoryScore(opponent, self)
}