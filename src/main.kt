import tokens.Tokenizer
import kotlin.system.measureTimeMillis

fun main() {
    // The constructor will not initialize! Change the constructors to change this behavior
    val tokens = Tokenizer("comp.neon")
    val tokenizerTime = measureTimeMillis {
        if (tokens.initialize()) println("Complete!")
        else println("Failed!")
    }
    println("[tokenizer] took [${tokenizerTime} ms] to tokenize file '${tokens.getFileName()}'")

    val expressionTimer = measureTimeMillis {
        val e: Expression =
            Expression.Binary.Add(
                Expression.Literal.Value(2), Expression.Binary.Subtract(
                    Expression.Literal.Value(5), Expression.Literal.Value(3)
                )
            )
        println("Expression: ${evalToString(e)} = ${evaluate(e)}")
    }
    println("[Evaluation] : Evaluation time: [${expressionTimer} ms]")


}