package board

import java.util.*

class Rotor(data: Array<Int>, private val next: Rotor?) {


    private val left = LinkedList<Int>()
    private val right = LinkedList<Int>()

    init {
        var i = 0
        data.forEach {
            this.right.add(it)
            this.left.add(i++)
        }
    }

    fun geTop() = left.first + 1

    fun rotate() {
        left.add(left[0])
        right.add(right[0])


        if (geTop() == 26) next!!.rotate()

        left.removeFirst()
        right.removeFirst()
    }

    private fun LinkedList<Int>.findIndex(no: Int): Int {
        this.forEachIndexed { index, i ->
            if (i == no) return index
        }
        return -1
    }

    fun forward(char: Int): Int {
        return left.findIndex(right[char])
    }

    fun backward(char: Int): Int {
        return right.findIndex(left[char])
    }
}