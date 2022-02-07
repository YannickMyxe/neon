class SyntaxTree {
    private val outFile: FileManager

    constructor(filename: String) {
        this.outFile = FileManager(filename)
    }

}