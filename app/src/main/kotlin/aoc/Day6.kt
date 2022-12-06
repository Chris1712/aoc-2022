package aoc

fun day6Part1(): Int {
    val line = loadResource("day6-input").split("\n")[0]

    return getPositionOf4Diff(line)
}

fun getPositionOf4Diff(input: String): Int {
    val mostRecent4 = mutableListOf<Char>()
    //get first 4 chars of input
    for (i in 0..3) {
        mostRecent4.add(input[i])
    }

    for (i in 4 until input.length) {
        if (mostRecent4.toHashSet().size == 4) {
            return i
        } else {
            mostRecent4.removeAt(0)
            mostRecent4.add(input[i])
        }
    }

    return -1
}