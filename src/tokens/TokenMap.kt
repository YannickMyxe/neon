package tokens

import java.util.*

class TokenMap {

    private val list: Vector<Token> = Vector()

    fun add(token: Token) {
        list.add(token)
    }

    // OVERLOAD [_]
    operator fun get(index: Int): Token {
        return list[index]
    }

    fun getIndex(index: Int): Token {
       return list[index]
    }

    fun getList(): Vector<Token> {
        return list
    }


}