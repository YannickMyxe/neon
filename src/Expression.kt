sealed class Expression {

    sealed class Literal : Expression() {
        class Value(val v: Int) : Literal()
    }

    sealed class Binary(val left: Expression, val right: Expression) : Expression() {
        class Add(left: Expression, right: Expression) : Binary(left, right)
        class Subtract(left: Expression, right: Expression) : Binary(left, right)
        class Multiply(left: Expression, right: Expression) : Binary(left, right)
        class Divide(left: Expression, right: Expression) : Binary(left, right)
    }

    sealed class Unary(val left: Expression) : Expression() {
        class Negate(left: Expression) : Unary(left)
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