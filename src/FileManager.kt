import java.io.File

class FileManager {

    private val filename: String
    private val file: File

    constructor(filename: String) {
        this.filename = filename
        file = File(filename)
    }

    fun fileExists(): Boolean {
        return file.exists()
    }

    fun createFile(): Boolean {
        if (!fileExists()) {
            file.createNewFile()
            return true
        }
        return false
    }

    fun getFile(): File {
        return file
    }

    fun canWrite(): Boolean {
        if (fileExists()) {
            return file.canWrite()
        }
        return createFile()
    }

    fun getFileName(): String {
        return filename
    }

    fun printFile() {
        file.readLines().forEachIndexed { index, line ->
            println("[$index]: $line")
        }
    }
}