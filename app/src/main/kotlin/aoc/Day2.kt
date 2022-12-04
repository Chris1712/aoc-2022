package aoc

// Scoring
// Pick: 1 rock, 2 paper, 3 scissors
// Result: 0 lose, 3 draw, 6 win
// Rock: A/X Paper: B/Y, Scissors: C/Z

fun totalScore(): Int {
    val lines: List<String> = loadResource("day2-input").split("\n")
    return lines.filter { s -> s.isNotEmpty() }.map{ s -> roundScore(s[0], s[2])}.sum()
}

fun resultScore(opponent: Char, self: Char): Int {
    return if (
        (opponent == 'A' && self == 'X')
        || (opponent == 'B' && self == 'Y')
        || (opponent == 'C' && self == 'Z')
    ) {
        // Tie
        3
    } else if (
        (opponent == 'A' && self == 'Y')
        || (opponent == 'B' && self == 'Z')
        || (opponent == 'C' && self == 'X')
    ) {
        // We wont!
        6
    } else {
        // We lost :'(
        0
    }
}

/**
 * Determine the score for a single round - combines pick score and result score
 */
fun roundScore(opponent: Char, self: Char): Int {
    val pickScore = when(self) {
        'X' -> 1
        'Y' -> 2
        'Z' -> 3
        else -> 0
    }
    val resultScore = resultScore(opponent, self)
    return pickScore + resultScore
}