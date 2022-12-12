package aoc.day11

import aoc.util.loadResource

data class Monkey(val items: MutableList<Long>,
                  val operation: (Long) -> Long,
                  val test: (Long) -> Boolean,
                  val testNo: Long,
                  val trueDestination: Int,
                  val falseDestination: Int,
                  var inspectionCount: Long = 0)


fun day11Part1(): Long {
    val lines = loadResource("day11-input").split("\n")
    val monkeys = parseInput(lines)
    return getMonkeyBusiness(monkeys, 20, 3)
}

fun day11Part2(): Long {
    val lines = loadResource("day11-input").split("\n")
    val monkeys = parseInput(lines)
    return getMonkeyBusiness(monkeys, 10000, 1)
}

fun parseInput(input: List<String>): List<Monkey> {
    val monkeys = mutableListOf<Monkey>()
    for (i in 0 until input.size step 7) {
        val itemsStr = Regex("""\s+Starting items: (.*)""").find(input[i+1])!!.destructured
        val items = itemsStr.component1().split(", ").map(String::toLong).toMutableList()

        val (opStr, opNo) = Regex("""\s+Operation: new = old (.) (.+)""").find(input[i+2])!!.destructured

        val operation = if (opNo == "old") {
            { x: Long -> x * x }
        } else {
            when (opStr) {
                "+" -> { x: Long -> x + opNo.toLong() }
                "*" -> { x: Long -> x * opNo.toLong() }
                else -> throw IllegalArgumentException("Unknown operation: $opStr")
            }
        }

        val testVal = Regex("""\s+Test: divisible by (\d+)""").find(input[i + 3])!!.destructured.component1().toLong()
        val test = { x: Long -> x % testVal == 0L }

        val trueDest = Regex("""\s+If true: throw to monkey (\d+)""").find(input[i+4])!!.destructured.component1().toInt()
        val falseDest = Regex("""\s+If false: throw to monkey (\d+)""").find(input[i+5])!!.destructured.component1().toInt()

        monkeys.add(Monkey(items, operation, test, testVal, trueDest, falseDest))
    }
    return monkeys
}

/**
 * Take turn of monkey with index int, returning a new list of monkeys resulting
 */
fun monkeyTurn(monkeys: List<Monkey>, index: Int, worryLevelReduction: Int, sharedMod: Long) {
    for (i in monkeys[index].items.indices) {
        val itemWorry = monkeys[index].items[i] % sharedMod
        val newItemWorry = monkeys[index].operation(itemWorry) / worryLevelReduction.toLong()
        val dest = if (monkeys[index].test(newItemWorry)) monkeys[index].trueDestination else monkeys[index].falseDestination
        monkeys[dest].items.add(newItemWorry)
    }
    monkeys[index].inspectionCount += monkeys[index].items.size.toLong()
    monkeys[index].items.clear()
}

fun round(monkeysInput: List<Monkey>, worryLevelReduction: Int, sharedMod: Long) {
    var monkeys = monkeysInput
    for (i in monkeys.indices) {
        monkeyTurn(monkeys, i, worryLevelReduction, sharedMod)
    }
}

fun getMonkeyBusiness(monkeys: List<Monkey>, rounds: Int, worryLevelReduction: Int): Long {
    val sharedMod = monkeys.map { it.testNo }.reduce { acc, i -> acc * i } // Keep worries at modulo of product of test vals

    repeat (rounds) {
        round(monkeys, worryLevelReduction, sharedMod)
    }

    println("After $rounds rounds:")
    for (i in monkeys.indices) {
        println("  Monkey $i has inspected ${monkeys[i].inspectionCount} items")
    }
    var maxTwo = monkeys.map { it.inspectionCount }.sorted().takeLast(2);
    println("Two highest counts are $maxTwo")
    return maxTwo[0] * maxTwo[1]
}