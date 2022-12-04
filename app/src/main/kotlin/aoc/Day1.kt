package aoc

import java.lang.Integer.max

/**
 * Return the largest number of calories held by any single elf
 */
fun mostCals(): Int {
    // Get list of food item calorie values. Double line break indicates a different elf
    val lines: List<String> = loadResource("day1-input").split("\n")

    var elfHeldCals: Int = 0
    var maxElfHeldCals: Int = 0
    for (i in lines.indices) {
        if (lines[i] == "") {
            maxElfHeldCals = max(elfHeldCals, maxElfHeldCals)
            elfHeldCals = 0
            continue;
        } else {
            elfHeldCals += lines[i].toInt()
        }
    }
    return maxElfHeldCals
}
