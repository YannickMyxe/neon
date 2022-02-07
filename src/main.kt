import Expression.*

fun main() {
    // The constructor will not initialize! Change the constructors to change this behavior
    val tokens = Tokenizer("comp.neon")
    if(tokens.initialize()) println("Complete!")

    val ex = Add(Multiply(Value(2), Value(3)), Value(5))
    println("Expression: ${evalToString(ex)}")
    println("Evaluation: ${evaluate(ex)}")

}