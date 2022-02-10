package tokens

class Token {

    private val type: TokenType
    private val data: String?

    constructor(type: TokenType, data: String?) {
        this.type = type
        this.data = data.toString()
    }

    fun getId(): TokenType {
        return type
    }

    fun getData(): String? {
        return data
    }
}

fun checkToken(string: String): TokenType {
    if (Character.isDigit(string[0])) {
        return TokenType.NUMBER
    } else {
        return when (string) {
            // Mathematical
            "+" -> TokenType.PLUS
            "-" -> TokenType.MINUS
            "/" -> TokenType.DIVIDE
            "*" -> TokenType.TIMES
            "%" -> TokenType.MODULO

            // Brackets
            "{" -> TokenType.L_CURLY_BRACKET
            "}" -> TokenType.R_CURLY_BRACKET
            "[" -> TokenType.L_SQUARE_BRACKET
            "]" -> TokenType.R_SQUARE_BRACKET
            "(" -> TokenType.L_ROUND_BRACKET
            ")" -> TokenType.R_ROUND_BRACKET

            // Comparison
            "<" -> TokenType.SMALLER_THEN
            ">" -> TokenType.GREATER_THEN
            "<=" -> TokenType.SMALLER_OR_EQUAL
            ">=" -> TokenType.GREATER_OR_EQUAL
            "=" -> TokenType.EQUAL

            // Special Chars
            "?" -> TokenType.QUESTION_MARK
            "!" -> TokenType.EXCLAMATION_MARK
            "," -> TokenType.COMMA
            ":>" -> TokenType.RABBIT
            ":" -> TokenType.COLON
            "::" -> TokenType.DOUBLE_COLON

            // Quotes
            "\"" -> TokenType.DOUBLE_QUOTE
            "'" -> TokenType.SINGLE_QUOTE
            "`" -> TokenType.BACKTICK

            // Logical
            "true" -> TokenType.TRUE
            "false" -> TokenType.FALSE
            "AND", "&&"  -> TokenType.AND
            "OR", "||" -> TokenType.OR
            "XOR" -> TokenType.XOR
            "NAND" -> TokenType.NAND
            "NOT" -> TokenType.NOT
            "NOR" -> TokenType.NOR
            "==" -> TokenType.DOUBLE_EQUALS
            "+=" -> TokenType.PLUS_EQUALS
            "-=" -> TokenType.MINUS_EQUALS
            "/=" -> TokenType.DIVIDE_EQUALS
            "*=" -> TokenType.TIMES_EQUALS
            "%=" -> TokenType.MODULO_EQUALS

            // Keywords
            "mod" -> TokenType.MODULE
            "use" ->  TokenType.USE
            "let" -> TokenType.LET
            "main" -> TokenType.MAIN
            "ret" -> TokenType.RETURN
            "fn" -> TokenType.FUNCTION


            else -> {
                // NONE
                return TokenType.NAME
            }
        }
    }
}

enum class TokenType {

    // None
    NONE,

    // Mathematical
    PLUS, MINUS,
    DIVIDE, TIMES, MODULO,

    // Brackets
    L_CURLY_BRACKET, R_CURLY_BRACKET,
    L_SQUARE_BRACKET, R_SQUARE_BRACKET,
    L_ROUND_BRACKET, R_ROUND_BRACKET,

    // Special Chars
    RABBIT, COLON, DOUBLE_COLON,
    QUESTION_MARK, EXCLAMATION_MARK, COMMA,

    // vars
    NUMBER,

    // Logical operators
    TRUE, FALSE,
    AND, OR, XOR, NAND, NOT, NOR,
    GREATER_THEN, SMALLER_THEN,
    GREATER_OR_EQUAL, SMALLER_OR_EQUAL,
    DOUBLE_EQUALS,

    // Assignment
    EQUAL, PLUS_EQUALS, MINUS_EQUALS,
    TIMES_EQUALS, DIVIDE_EQUALS,
    MODULO_EQUALS,

    // Quotes
    DOUBLE_QUOTE, SINGLE_QUOTE, BACKTICK,

    // Keywords
    FUNCTION, RETURN, LET, MAIN,
    NAME, MODULE, USE,
}
