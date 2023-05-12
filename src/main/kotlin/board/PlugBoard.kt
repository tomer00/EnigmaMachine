package board

class PlugBoard {
    private val board = mutableMapOf<Char, Char>()

    fun setPair(c1:Char,c2:Char){
        if (board.containsKey(c1)){
            board.remove(board[c1]!!)
            board.remove(c1)
        }
        if (board.containsKey(c2)){
            board.remove(board[c2]!!)
            board.remove(c2)
        }

        board[c1] = c2
        board[c2] = c1
    }


    fun getChar(c: Char): Char {
        return if (board.containsKey(c)) board[c]!! else c
    }
    fun getBoard() = board
}