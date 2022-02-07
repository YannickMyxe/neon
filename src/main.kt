fun main() {

    val tokens = Tokenizer()
    println("Complete!")

    //printFile(outFile)
}


fun isString(c: Char): Boolean {
    when (c) {
        '"' -> return true
        '\'' -> return true
        '`' -> return true
    }
    return false
}

fun isLetter(c: Char): Boolean {
    return (c in 'a'..'z' || c in 'A'..'Z')
}

fun isNumber(c: Char): Boolean {
    return (c in '0'..'9')
}

fun isBracket(char: Char): Boolean {
    when (char) {
        '{' -> return true
        '}' -> return true
        '[' -> return true
        ']' -> return true
        '(' -> return true
        ')' -> return true
    }
    return false
}

fun isSpecialCharacter(char: Char): Boolean {
    return (isString(char) || isBracket(char) || isLetter(char))
}