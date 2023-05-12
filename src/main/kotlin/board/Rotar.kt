package board

class Rotar(private val forward: Array<Char>, private val backward: Array<Char>) {

    fun forward(char: Char):Char = forward[char-'a']
    fun backward(char: Char):Char = backward[char-'a']
}