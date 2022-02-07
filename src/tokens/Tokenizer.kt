package tokens

import FileManager
import java.util.*

class Tokenizer {
    private val file: FileManager
    private val tokenFile: FileManager
    private val namedTokensFile: FileManager

    private val tokensList: Vector<String> = Vector()
    private val tokenMap: TokenMap = TokenMap()

    private val idList: Identifier

    constructor(filename: String) {
        file = FileManager(filename)
        tokenFile = FileManager(file.getFileName() + ".tokens")
        namedTokensFile = FileManager(file.getFileName() + ".named")
        idList = Identifier(file.getFileName()+".id")
    }

    constructor(filename: String, tokenFile: String, namedTokensFile: String, identifiersFile: String) {
        this.file = FileManager(filename)
        this.tokenFile = FileManager(tokenFile)
        this.namedTokensFile = FileManager(namedTokensFile)
        this.idList = Identifier(identifiersFile)
    }

    fun initialize(): Boolean {
        // # FILE HANDLING
        if (!file.canWrite()) {
            error("Cannot write to file: ${file.getFileName()}")
        }
        if (!tokenFile.canWrite()) {
            error("Cannot write to file: ${tokenFile.getFileName()}")
        }
        if (!namedTokensFile.canWrite()) {
            error("Cannot write to file: ${namedTokensFile.getFileName()}")
        }
        println("File: ${file.getFileName()}")


        // # READING FILE
        val data = file.getFile().readLines()
        data.forEach { line ->
            val i = line.indexOf("//")
            var newLine = line
            if (i >= 0) {
                newLine = line.substring(0, i)
            }

            var text = ""
            newLine.forEachIndexed { _, char ->
                if (!isLetter(char) && !isNumber(char)) { // Chars to expand the text var
                    if (text != "") {
                        // add to the tokens list and clear the var
                        tokensList.add(text)
                        text = ""
                    }
                    if (!char.isWhitespace()) {
                        tokensList.add(char.toString())
                    }
                } else {
                    text += char
                }
            }
            if (text != "") {
                tokensList.add(text)
            }
        }
        // Find double spots and put the index of the second token in the indices vector
        val indices: Vector<Int> = Vector()
        for ((index, _) in tokensList.withIndex()) {
            if (index + 1 <= tokensList.size) {
                val char = tokensList[index][0]
                if (!isSpecialCharacter(char)) {
                    val nextChar = tokensList[index + 1][0]
                    if (!isSpecialCharacter(nextChar) && !isNumber(nextChar)) {
                        tokensList[index] += tokensList[index + 1]
                        indices.add(index + 1 - indices.size)
                    }
                }
            }
        }
        // Remove the indexes collected in the vector
        for (item in indices) {
            // println("Removing index: $item :> " + tokens[item])
            tokensList.removeElementAt(item)
        }

        // Write the tokens to a file
        tokenFile.getFile().writeText("Tokens: \n")
        // println("Tokens: ")
        tokensList.forEachIndexed { index, token ->
            // println("$index => '$token'")
            tokenFile.getFile().appendText("$index : $token\n")
        }

        // # NAMED TOKENS
        // Add names to the tokens
        for (item in tokensList) {
            val currentToken = checkToken(item)
            if (currentToken == Tokens.NAME || currentToken == Tokens.NUMBER) {
                val index = idList.add(item)
                tokenMap.add(Token(currentToken, "id_$index"))
            } else {
                tokenMap.add(Token(currentToken, null))
            }
        }

        // Write the named tokens to a file
        namedTokensFile.getFile().writeText("Named tokens: \n")
        tokenMap.getList().forEach { token ->
            namedTokensFile.getFile().appendText("[${token.getId().name}]${
                if(token.getData() == "null") {
                    ""
                } else {
                    " : ${token.getData()}"
                }
            }\n")
        }

        val f = idList.getFile().getFile()
        f.writeText("Identifiers: \n")
        idList.getList().forEachIndexed {
            index, item ->
            f.appendText("$index : $item\n")
        }

        return true
    }


    private fun isString(c: Char): Boolean {
        when (c) {
            '"' -> return true
            '\'' -> return true
            '`' -> return true
        }
        return false
    }

    private fun isLetter(c: Char): Boolean {
        return (c in 'a'..'z' || c in 'A'..'Z')
    }

    private fun isNumber(c: Char): Boolean {
        return (c in '0'..'9')
    }

    private fun isBracket(char: Char): Boolean {
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

    private fun isSpecialCharacter(char: Char): Boolean {
        return (isString(char) || isBracket(char) || isLetter(char))
    }

    fun getTokens(): Vector<String> {
        return tokensList
    }

    private fun checkToken(string: String): Tokens {
        when (string) {
            "mod" -> return Tokens.MODULE
            "use" -> return Tokens.USE
            "+" -> return Tokens.PLUS
            "-" -> return Tokens.MINUS
            "/" -> return Tokens.DIVIDE
            "*" -> return Tokens.TIMES
            "%" -> return Tokens.MODULO
            "{" -> return Tokens.L_CURLY_BRACKET
            "}" -> return Tokens.R_CURLY_BRACKET
            "[" -> return Tokens.L_SQUARE_BRACKET
            "]" -> return Tokens.R_SQUARE_BRACKET
            "(" -> return Tokens.L_ROUND_BRACKET
            ")" -> return Tokens.R_ROUND_BRACKET
            ":>" -> return Tokens.RABBIT
            ":" -> return Tokens.COLON
            "::" -> return Tokens.DOUBLE_COLON
            "fn" -> return Tokens.FUNCTION
            "<" -> return Tokens.SMALLER_THEN
            ">" -> return Tokens.GREATER_THEN
            "<=" -> return Tokens.SMALLER_OR_EQUAL
            ">=" -> return Tokens.GREATER_OR_EQUAL
            "=" -> return Tokens.EQUAL
            "==" -> return Tokens.DOUBLE_EQUALS
            "+=" -> return Tokens.PLUS_EQUALS
            "-=" -> return Tokens.MINUS_EQUALS
            "/=" -> return Tokens.DIVIDE_EQUALS
            "*=" -> return Tokens.TIMES_EQUALS
            "%=" -> return Tokens.MODULO_EQUALS
            "\"" -> return Tokens.DOUBLE_QUOTE
            "'" -> return Tokens.SINGLE_QUOTE
            "`" -> return Tokens.BACKTICK
            "?" -> return Tokens.QUESTION_MARK
            "!" -> return Tokens.EXCLAMATION_MARK
            "ret" -> return Tokens.RETURN
            "," -> return Tokens.COMMA
            "let" -> return Tokens.LET
            "main" -> return Tokens.MAIN
            "" -> return Tokens.NONE
        }
        if (isNumber(string[0])) {
            return Tokens.NUMBER
        }
        return Tokens.NAME
    }

    enum class Tokens {
        NONE,
        NAME,
        MODULE,
        USE,
        PLUS,
        MINUS,
        DIVIDE,
        TIMES,
        MODULO,
        L_CURLY_BRACKET,
        R_CURLY_BRACKET,
        L_SQUARE_BRACKET,
        R_SQUARE_BRACKET,
        L_ROUND_BRACKET,
        R_ROUND_BRACKET,
        RABBIT,
        COLON,
        DOUBLE_COLON,
        NUMBER,
        FUNCTION,
        GREATER_THEN,
        SMALLER_THEN,
        GREATER_OR_EQUAL,
        SMALLER_OR_EQUAL,
        EQUAL,
        DOUBLE_EQUALS,
        PLUS_EQUALS,
        MINUS_EQUALS,
        TIMES_EQUALS,
        DIVIDE_EQUALS,
        MODULO_EQUALS,
        DOUBLE_QUOTE,
        SINGLE_QUOTE,
        BACKTICK,
        QUESTION_MARK,
        EXCLAMATION_MARK,
        RETURN,
        COMMA,
        LET,
        MAIN

    }

}