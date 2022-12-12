package aoc.day11

import aoc.util.loadResource

data class Monkey(val items: MutableList<Int>,
                  val operation: (Int) -> Int,
                  val test: (Int) -> Boolean,
                  val trueDestination: Int,
                  val falseDestination: Int,
                  var inspectionCount: Int = 0)


fun day11Part1(): Int {
    val lines = loadResource("day11-input").split("\n")
    val monkeys = parseInput(lines)
    return getMonkeyBusiness(monkeys, 20)
}


fun parseInput(input: List<String>): List<Monkey> {
    val monkeys = mutableListOf<Monkey>()
    for (i in 0 until input.size step 7) {
        val itemsStr = Regex("""\s+Starting items: (.*)""").find(input[i+1])!!.destructured
        val items = itemsStr.component1().split(", ").map(String::toInt).toMutableList()

        val (opStr, opNo) = Regex("""\s+Operation: new = old (.) (.+)""").find(input[i+2])!!.destructured
        println("monkey $i, opStr = $opStr, opNo = $opNo")

        val operation = if (opNo == "old") {
            { x: Int -> x * x }
        } else {
            when (opStr) {
                "+" -> { x: Int -> x + opNo.toInt() }
                "*" -> { x: Int -> x * opNo.toInt() }
                else -> throw IllegalArgumentException("Unknown operation: $opStr")
            }
        }

        println(input[i+3])
        val testSr = Regex("""\s+Test: divisible by (\d+)""").find(input[i + 3])!!.destructured
        val test = { x: Int -> x % testSr.component1().toInt() == 0 }

        val trueDest = Regex("""\s+If true: throw to monkey (\d+)""").find(input[i+4])!!.destructured.component1().toInt()
        val falseDest = Regex("""\s+If false: throw to monkey (\d+)""").find(input[i+5])!!.destructured.component1().toInt()

        monkeys.add(Monkey(items, operation, test, trueDest, falseDest))
        println("Added monkey ${monkeys.last()}")
    }
    return monkeys
}

/**
 * Take turn of monkey with index int, returning a new list of monkeys resulting
 */
fun monkeyTurn(monkeys: List<Monkey>, index: Int) {
    println("Monkey $index:")
    for (i in monkeys[index].items.indices) {
        val itemWorry = monkeys[index].items[i]
        println("  Monkey inspects an item with a worry level of $itemWorry")
        val newItemWorry = monkeys[index].operation(itemWorry) / 3
        println("  Monkey performs operation on item, resulting in a worry level of $newItemWorry")
        val testResult = monkeys[index].test(newItemWorry)
        println("  Monkey tests item, resulting in a test result of $testResult")
        val dest = if (monkeys[index].test(newItemWorry)) monkeys[index].trueDestination else monkeys[index].falseDestination
        println("  Item with worry level $newItemWorry is thrown to monkey $dest")
        monkeys[dest].items.add(newItemWorry)
    }
    monkeys[index].inspectionCount += monkeys[index].items.size
    monkeys[index].items.clear()
}

fun round(monkeysInput: List<Monkey>) {
    var monkeys = monkeysInput
    for (i in monkeys.indices) {
        monkeyTurn(monkeys, i)
    }
}

fun getMonkeyBusiness(monkeys: List<Monkey>, rounds: Int): Int {
    // Play the rounds
    repeat (rounds) {
        round(monkeys)
    }

    println("After $rounds rounds:")
    for (i in monkeys.indices) {
        println("  Monkey $i has inspected ${monkeys[i].inspectionCount} items")
    }
    var maxTwo = monkeys.map { it.inspectionCount }.sorted().takeLast(2);
    println("Two highest counts are $maxTwo")
    return maxTwo[0] * maxTwo[1]
}