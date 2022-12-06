package aoc

fun day6Part1(): Int {
    val line = loadResource("day6-input").split("\n")[0]

    return getPositionOfNDistinct(line, 4)
}

fun day6Part2(): Int {
    val line = loadResource("day6-input").split("\n")[0]

    return getPositionOfNDistinct(line, 14)
}

fun getPositionOfNDistinct(input: String, n: Int): Int {
    val mostRecent4 = mutableListOf<Char>()
    //get first n chars of input
    for (i in 0 until n) {
        mostRecent4.add(input[i])
    }

    for (i in n until input.length) {
        if (mostRecent4.toHashSet().size == n) {
            return i
        } else {
            mostRecent4.removeAt(0)
            mostRecent4.add(input[i])
        }
    }

    return -1
}