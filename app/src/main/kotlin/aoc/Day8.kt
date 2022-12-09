package aoc

fun day8Part1(): Int {
    val lines = loadResource("day8-input").split("\n").filter(String::isNotBlank)
    return getVisibleTrees(lines)
}

fun day8Part2(): Int {
    val lines = loadResource("day8-input").split("\n").filter(String::isNotBlank)
    return getBestScenicScore(lines)
}

fun getVisibleTrees(lines: List<String>): Int {
    var visibleCount = 0
    val treeGrid = treeLinesToGrid(lines);
    for (i in treeGrid.indices) {
        for (j in 0 until treeGrid[i].size) {
            if (checkTreeVisibility(treeGrid, i, j)) {
                visibleCount++
            }
        }
    }
    return visibleCount
}

fun checkTreeVisibility(treeGrid: List<IntArray>, lineNo: Int, colNo: Int): Boolean {
    val treeHeight = treeGrid[lineNo][colNo]
    val gridHeight = treeGrid.size
    val gridWidth = treeGrid[0].size

    val treesAcross: IntArray = treeGrid[lineNo]
    val treesDown: IntArray = treeGrid.map { it[colNo] }.toIntArray()

    return (
        treesAcross.take(colNo).all { it < treeHeight } ||
        treesAcross.takeLast(gridWidth - (colNo + 1)).all { it < treeHeight } ||
        treesDown.take(lineNo).all { it < treeHeight } ||
        treesDown.takeLast(gridHeight - (lineNo + 1)).all { it < treeHeight }
    )
}

fun getBestScenicScore(lines: List<String>): Int {
    var bestScore = 0

    val treeGrid = treeLinesToGrid(lines);
    for (i in treeGrid.indices) {
        for (j in 0 until treeGrid[i].size) {
            bestScore = maxOf(bestScore, getTreeScenicScore(treeGrid, i, j))
        }
    }
    return bestScore
}

fun getTreeScenicScore(treeGrid: List<IntArray>, lineNo: Int, colNo: Int): Int {
    val treeHeight = treeGrid[lineNo][colNo]
    val gridHeight = treeGrid.size
    val gridWidth = treeGrid[0].size

    val treesAcross: IntArray = treeGrid[lineNo]
    val treesDown: IntArray = treeGrid.map { it[colNo] }.toIntArray()

    return (
            getScenicScoreInDirection(treeHeight, treesAcross.take(colNo).reversed()) *
            getScenicScoreInDirection(treeHeight, treesAcross.takeLast(gridWidth - (colNo + 1))) *
            getScenicScoreInDirection(treeHeight, treesDown.take(lineNo).reversed()) *
            getScenicScoreInDirection(treeHeight, treesDown.takeLast(gridHeight - (lineNo + 1)))
    )
}

fun getScenicScoreInDirection(treeHeight: Int, trees: List<Int>): Int {
    println("treeHeight: $treeHeight, trees: $trees")
    return if (trees.all { it < treeHeight }) {
        println("all trees are lower than treeHeight")
        trees.size
    } else {
        println("not all trees are lower than treeHeight")
        trees.takeWhile { it < treeHeight }.count() + 1
    }
}

fun treeLinesToGrid(lines: List<String>): List<IntArray> {
    return lines.map { line -> line.toCharArray().map { it.toString().toInt() }.toIntArray() }
}