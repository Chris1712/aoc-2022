package aoc

fun loadResource(path: String): String {
    return Thread.currentThread().contextClassLoader.getResource(path).readText()
}