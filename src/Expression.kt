sealed class Expression {

    sealed class Literal : Expression() {
        class Value(val v: Int) : Literal()
    }

    sealed class Binary : Expression() {
        class Add(val left: Expression, val right: Expression) : Binary()
        class Subtract(val left: Expression, val right: Expression) : Binary()
        class Multiply(val left: Expression, val right: Expression) : Binary()
        class Divide(val left: Expression, val right: Expression) : Binary()
    }

    sealed class Unary : Expression() {
        class Negate(val left: Expression) : Unary()
    }
}


fun evaluate(ex: Expression): Int {
    return when (ex) {
        // Literal
        is Expression.Literal.Value -> ex.v
        // Binary
        is Expression.Binary.Add -> evaluate(ex.left) + evaluate(ex.right)
        is Expression.Binary.Subtract -> evaluate(ex.left) - evaluate(ex.right)
        is Expression.Binary.Divide -> evaluate(ex.left) / evaluate(ex.right)
        is Expression.Binary.Multiply -> evaluate(ex.left) * evaluate(ex.right)
        // Unary
        is Expression.Unary.Negate -> -1 * evaluate(ex.left)
    }
}

fun evalToString(ex: Expression): String {
    return when (ex) {
        // Literal
        is Expression.Literal.Value -> ex.v.toString()
        // Binary
        is Expression.Binary.Add -> "(${evalToString(ex.left)} + ${evalToString(ex.right)})"
        is Expression.Binary.Subtract -> "(${evalToString(ex.left)} - ${evalToString(ex.right)})"
        is Expression.Binary.Divide -> "(${evalToString(ex.left)} / ${evalToString(ex.right)})"
        is Expression.Binary.Multiply -> "(${evalToString(ex.left)} * ${evalToString(ex.right)})"
        // Unary
        is Expression.Unary.Negate -> "(-${evalToString(ex.left)})"
    }
}