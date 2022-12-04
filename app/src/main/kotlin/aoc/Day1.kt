package aoc

/**
 * Return the largest number of calories held by any single elf
 */
fun mostCals(): Int {
    return mostCalsTopN(1)[0]
}

/**
 * Return the total calories held by the top 3 highest calorie-holding elves
 */
fun top3Sum(): Int {
    return mostCalsTopN(3).sum()
}

/**
 * Return ordered list of calories held by top N calorie-holding elves
 */
fun mostCalsTopN(n: Int): List<Int> {
    // Get list of food item calorie values. Double line break indicates a different elf
    val lines: List<String> = loadResource("day1-input").split("\n")

    var elfHeldCals = 0
    var topHeldCals: List<Int> = List(n) {0} // Ordered list to hold n highest values
    for (i in lines.indices) {
        if (lines[i] == "") {
            // We've finished this elf's items
            if (elfHeldCals > topHeldCals.minOrNull() ?: 0) {
                topHeldCals = topHeldCals.subList(1, n).plus(elfHeldCals).sorted()
            }
            elfHeldCals = 0
            continue;
        } else {
            elfHeldCals += lines[i].toInt()
        }
    }
    return topHeldCals
}