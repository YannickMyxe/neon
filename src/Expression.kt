sealed class Expression{

    class Value(val v: Int) : Expression()

    class Add(val left: Expression, val right: Expression) : Expression()

    class Subtract(val left: Expression, val right: Expression) : Expression()

}

fun evaluate(ex: Expression): Int {
    return when (ex) {
        is Expression.Value -> {
            ex.v
        }
        is Expression.Add -> {
            evaluate(ex.left) + evaluate(ex.right)
        }
        is Expression.Subtract -> {
            evaluate(ex.left) - evaluate(ex.right)
        }
    }
}