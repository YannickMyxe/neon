import tokens.Tokenizer
import kotlin.system.measureTimeMillis

fun main() {
    // The constructor will not initialize! Change the constructors to change this behavior
    val tokens = Tokenizer("comp.neon")
    val tokenizerTime = measureTimeMillis {
        if (tokens.initialize()) println("Complete!")
        else println("Failed!")
    }
    println("[tokenizer] took [$tokenizerTime ms] to tokenize the file")
}