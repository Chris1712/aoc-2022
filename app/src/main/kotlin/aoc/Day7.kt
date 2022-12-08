package aoc

fun day7Part1(): Int {
    val lines = loadResource("day7-input").split("\n").filter(String::isNotBlank)
    val dirSizes = sumDirs(lines)
    return dirSizes.values.filter { it <= 100000 }.sum()
}

fun day7Part2(): Int {
    val lines = loadResource("day7-input").split("\n").filter(String::isNotBlank)
    val dirSizes = sumDirs(lines)

    // We need to make unused space at least 30000000
    val freeSpace = 70000000 - (dirSizes["//"] ?: 0)
    val additionalFreeSpaceNeeded = 30000000 - freeSpace

    return dirSizes
        .filterValues { it >= additionalFreeSpaceNeeded }
        .map { it.value }
        .sorted()
        .first()
}

/**
 * Return a map of directory names -> sizes
 * Assumes we don't list the same directory twice
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
                val currentDirFullPath = (currentPath.lastOrNull() ?: "") + "$changedDir/"
                currentPath.add(currentDirFullPath)
            }
        } else if (line.matches(Regex("""^(\d+) .*"""))) {
            // We have a file size, add it to each dir in our path
            val size = line.split(" ").first().toInt()
            currentPath.forEach { dir ->
                dirSizes[dir] = dirSizes.getOrDefault(dir, 0) + size
            }
        }
    }

    return dirSizes
}