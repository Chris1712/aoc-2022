package aoc

fun day7Part1(): Int {
    val lines = loadResource("day7-input").split("\n").filter(String::isNotBlank)
    val dirMap = sumDirs(lines)
    return dirMap.values.filter { it <= 100000 }.sum()
}

/**
 * Return a map of directory names -> sizes
 */
fun sumDirs(consoleLines: List<String>): Map<String, Int> {

    val dirSizes = mutableMapOf<String, Int>()
    val currentPath = mutableListOf<String>()

    for (line in consoleLines) {
        if (line.startsWith("$ cd")) {
            val changedDir = line.split(" ").last()
            if (changedDir == "..") {
                currentPath.removeLast()
            } else {
                currentPath.add(changedDir)
            }
            println("line was $line, currentPath is now $currentPath")
        } else if (line.matches(Regex("""^(\d+) .*"""))) {
            // We have a file size, add it to each dir in our path
            val size = line.split(" ").first().toInt()
            println("Adding size $size to dirs $currentPath")
            currentPath.forEach { dir ->
                dirSizes[dir] = dirSizes.getOrDefault(dir, 0) + size
            }
        } else {
            println("IGNORED - line was $line")
        }
    }

    return dirSizes
}