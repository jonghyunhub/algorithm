package programmers.kotlin

// https://school.programmers.co.kr/learn/courses/30/lessons/12951
class JadenCase {
    fun solution(s: String): String {
        val split = s.split(" ")

        val resultBuilder = StringBuilder()
        for((i,str) in split.withIndex()){
            // 공백문자는 스킵
            if(str.equals("")){
                if(i != split.size - 1) resultBuilder.append(str).append(" ")
                continue;
            }

            // 첫글자가 알파벳이 아닌것으로 시작하는 케이스
            if((str.get(0) >= 'a' && str.get(0) <= 'z') || (str.get(0) >= 'A' && str.get(0) <= 'Z')) {
                resultBuilder.append(str).append(" ")
                continue
            }

            // 단어 대소문자 변환
            val StringBuilder = StringBuilder()
            for((index, char) in str.withIndex()){
                if(index == 0){ // 첫 글자 소문자 경우
                    if(char >= 'a' && char <= 'z'){
                        StringBuilder.append(char.uppercaseChar())
                        continue;
                    }
                }
                else {
                    if(char >= 'A' && char <= 'Z'){ // 첫글자를 제외한 다른 글자가 대문자인 경우
                        StringBuilder.append(char.lowercaseChar())
                        continue;
                    }
                }
                StringBuilder.append(char)
            }

            val converted = StringBuilder.toString()
            resultBuilder.append(converted)
            if(i != split.size - 1) resultBuilder.append(" ")
        }

        return resultBuilder.toString()
    }
}

fun main() {
    val s1 = "3people unFollowed me"
    val s2 = "for the last week"
    val s3 = "3people  unFollowed me"
    val s6 = "  for the what 1what  "
    val result = JadenCase().solution(s6)
    print(result)
}