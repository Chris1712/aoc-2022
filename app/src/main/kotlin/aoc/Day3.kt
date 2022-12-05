package aoc



// Each row is a list of items in a rucksack, first half compartment one, second half compartment 2
// Item's prio is a=1, z = 26, Z = 52



fun itemPrio(item: Char): Int {
    // a has code 97, A has code 65. So subtract 96 for a-z, 38 for A-Z
    return if (item.code >= 97) item.code - 96 else item.code - 38
}

/**
 * Return a set holding the chars found in both halves of the string
 */
fun charsInBothHalves(input: String): Set<Char> {
    val left = input.take(input.length/2).toSet()
    val right = input.takeLast(input.length/2).toSet()
    return left.intersect(right)
}

fun day3Part1(): Int {
    return loadResource("day3-input").split("\n")
        .filter { line -> line.isNotEmpty() }
        .map { line -> charsInBothHalves(line) }
        .flatten()
        .map { c -> itemPrio(c) }
        .sum()
}

/**
 * Elves are in groups of 3. Find the item common to each group of 3, and sum the results.
 */
fun day3Part2(): Int {
    val elfPacks = loadResource("day3-input").split("\n").filter { line -> line.isNotEmpty() }.toList()
    return sumScoresForCommon(elfPacks)
}

fun sumScoresForCommon(elfPacks: List<String>): Int {
    val numGroups = elfPacks.size / 3
    var totalPrio = 0;
    for (i in 0 until numGroups) {
        val startIndex = 3 * i
        // Find the char shared between the three elves startIndex, startIndex+1, startIndex+2, get the prio, and add to the total

        totalPrio += elfPacks[startIndex].toSet()
            .intersect(elfPacks[startIndex+1].toSet())
            .intersect(elfPacks[startIndex+2].toSet())
            .map { c -> itemPrio(c) }
            .sum()
    }
    return totalPrio
}
