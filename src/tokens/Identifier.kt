package tokens

import FileManager
import java.util.*

class Identifier(filename: String) {
    private val list: Vector<Any> = Vector()
    private val file = FileManager(filename)

    private fun inList(item: Any): Int {
        list.forEachIndexed { index, current ->
            if(item == current) return index
        }
        return -1
    }

    fun getFile(): FileManager {
        return file
    }

    fun add(item: Any): Int {
        val index = inList(item)
        if (index >= 0) {
           return index
        }
        else {
            list.add(item)
            return list.size
        }
    }

    fun getList(): Vector<Any> {
        return list
    }

}