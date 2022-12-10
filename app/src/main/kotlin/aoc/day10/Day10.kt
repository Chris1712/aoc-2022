package aoc.day10

import aoc.util.loadResource
import kotlin.math.abs

fun day10Part1(): Int {
    val lines = loadResource("day10-input").split("\n").filter(String::isNotBlank)
    val cycles = listOf(20,60,100,140,180,220)
    return multiplyRegisterVals(lines, cycles)
}

fun day10Part2() {
    val lines = loadResource("day10-input").split("\n").filter(String::isNotBlank)
    val screenLines = printPixels(registerValsToPixels(getRegisterVals(lines)))
    println(screenLines.joinToString("\n"))
}

fun multiplyRegisterVals(lines: List<String>, cycles: List<Int>): Int {
    val cycleValues = getRegisterVals(lines)
    return cycles.map{ it * cycleValues[it-1] }.sum()
}

/**
 * Returns list of register values at the beginning of each cycle
 */
fun getRegisterVals(lines: List<String>): List<Int> {
    val instructions = ArrayDeque(lines)

    var cycle = 1 // Current cycle, 1-indexed
    var wait = getOpWait(instructions.first()) // delay before applying op
    var registerVal = 1
    var registerValHistory = mutableListOf<Int>()


    while (wait > 0) {
        println("Start of cycle $cycle, registerVal = $registerVal, wait = $wait, next op = ${instructions.first()}")
        registerValHistory.add(registerVal)

        // End of cycle. Apply instruction if we're ready
        if (wait == 1) {
            val op = instructions.removeFirst().split(" ")
            println("Applying op $op")
            if (op[0] == "addx") {
                registerVal += op[1].toInt()
            }

            // set up next op
            wait =  getOpWait(instructions.firstOrNull())
        } else {
            wait--
        }
        cycle++
    }

    return registerValHistory
}

fun getOpWait(op: String?): Int {
    if (op == null) {
        return 0
    }
    val opParts = op.split(" ")
    return when (opParts[0]) {
        "noop" -> 1
        "addx" -> 2
        else -> throw IllegalArgumentException("Unknown op: $op")
    }
}

fun registerValsToPixels(registerVals: List<Int>): List<Boolean> {
    val pixels = mutableListOf<Boolean>()

    for (i in 0 until registerVals.size) {
        val position = i % 40

        pixels.add(abs(registerVals[i] - position) <= 1)
    }
    return pixels
}

fun printPixels(pixels: List<Boolean> ): List<String> {
    val width = 40
    val rowList = mutableListOf<String>()
    for (i in 0 until pixels.size step width) {
        val row = pixels.subList(i, i + width)
        rowList.add((row.map { if (it) "#" else "." }.joinToString("")))
    }
    return rowList
}
