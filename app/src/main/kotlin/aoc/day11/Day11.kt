package aoc.day11

data class Monkey(val items: List<Int>,
                  val operation: (Int) -> Int,
                  val test: (Int) -> Boolean,
                  val trueDestination: Int,
                  val falseDestination: Int)


fun parseInput(input: List<String>): List<Monkey> {
    val monkeys = mutableListOf<Monkey>()
    for (i in 0 until input.size step 7) {
        val itemsStr = Regex("""\s+Starting items: (.*)""").find(input[i+1])!!.destructured
        val items = itemsStr.component1().split(", ").map(String::toInt)

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
fun monkeyTurn(monkeys: List<Monkey>, index: Int): List<Monkey> {
    // todo
    return emptyList()
}

fun round(monkeysInput: List<Monkey>): List<Monkey> {
    var monkeys = monkeysInput
    // iterate through list
    for (i in monkeys.indices) {
        // take turn
        monkeys = monkeyTurn(monkeys, i)
    }
    return monkeys
}
