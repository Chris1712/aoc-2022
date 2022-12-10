package aoc.day10

import aoc.util.loadResource

fun day10Part1(): Int {
    val lines = loadResource("day10-input").split("\n").filter(String::isNotBlank)
    val cycles = listOf(20,60,100,140,180,220)
    return getCycleValSum(lines, cycles)
}

/**
 * Returns the sum of the values of the register during the given cycles.
 */
fun getCycleValSum(lines: List<String>, cycles: List<Int>): Int {
    val deque = ArrayDeque(lines)

    var cycle = 1 // Current cycle, 1-indexed
    var wait = getOpWait(deque.first()) // delay before applying op
    var registerSum = 0;
    var registerVal = 1


    while (wait > 0) {
        println("Start of cycle $cycle, registerVal = $registerVal, wait = $wait, next op = ${deque.first()}")
        if (cycles.contains(cycle)) {
            registerSum += (registerVal * cycle)
        }

        // End of cycle. Apply instruction
        if (wait == 1) {
            // apply op
            val op = deque.removeFirst().split(" ")
            println("Applying op $op")
            if (op[0] == "addx") {
                registerVal += op[1].toInt()
            }

            // set up next op
            wait =  getOpWait(deque.firstOrNull())
        } else {
            wait--
        }
        println("After cycle $cycle, registerVal = $registerVal, wait = $wait, next op = ${deque.firstOrNull()}")
        cycle++
    }

    return registerSum
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


