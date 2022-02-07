sealed class Expression{

    class Value(val v: Int) : Expression()

    class Add(val left: Expression, val right: Expression) : Expression()

    class Subtract(val left: Expression, val right: Expression) : Expression()

    class Multiply(val left: Expression, val right: Expression): Expression()
    class Divide(val left: Expression, val right: Expression): Expression()
}

fun evaluate(ex: Expression): Int {
    return when (ex) {
        is Expression.Value -> ex.v
        is Expression.Add -> evaluate(ex.left) + evaluate(ex.right)
        is Expression.Subtract -> evaluate(ex.left) - evaluate(ex.right)
        is Expression.Divide -> evaluate(ex.left) / evaluate(ex.right)
        is Expression.Multiply -> evaluate(ex.left) * evaluate(ex.right)
    }
}

fun evalToString(ex: Expression): String {
    return when (ex) {
        is Expression.Value -> ex.v.toString()
        is Expression.Add -> "(${evalToString(ex.left)} + ${evalToString(ex.right)})"
        is Expression.Subtract -> "(${evalToString(ex.left)} - ${evalToString(ex.right)})"
        is Expression.Divide -> "(${evalToString(ex.left)} / ${evalToString(ex.right)})"
        is Expression.Multiply -> "(${evalToString(ex.left)} * ${evalToString(ex.right)})"
    }
}