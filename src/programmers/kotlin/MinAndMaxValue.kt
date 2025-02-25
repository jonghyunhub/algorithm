package programmers.kotlin

import java.util.stream.Collectors

// https://school.programmers.co.kr/learn/courses/30/lessons/12939?language=kotlin
class MinAndMaxValue {
    fun solution(s: String): String {
        val split = s.split(" ")
        val toList = split.stream()
            .map { it.toInt() }
            .sorted()
            .collect(Collectors.toList())
        return toList.get(0).toString() + " " +  toList.get(toList.size - 1).toString()
    }
}


fun main() {
    val minAndMaxValue = MinAndMaxValue()
    val s1 = "1 2 3 4"
    val s2 = "-1 -2 -3 -4"
    val s3 = "-1 -1"
    val solution = minAndMaxValue.solution(s3)
    println(solution)
}