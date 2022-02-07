import Expression.*

fun main() {
    // The constructor will initialize! Change the constructors to change this behavior
    val tokens = Tokenizer("comp.neon")
    //if(tokens.initialize()) println("Complete!")

    val ex = Add(Subtract(Value(10), Value(3)), Value(5))
    println("Evaluation: ${evaluate(ex)}")

}