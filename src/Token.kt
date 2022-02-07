class Token {

    private val id: Tokenizer.Tokens
    private val data: String?

    constructor(id: Tokenizer.Tokens, data: String?) {
        this.id = id
        this.data = data.toString()
    }

    fun getId(): Tokenizer.Tokens {
        return id
    }

    fun getData(): String? {
        return data
    }



}