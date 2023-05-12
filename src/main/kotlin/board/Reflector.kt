package board

class Reflector(private val ref: Map<Char, Char>) {

    fun reflect(c: Char): Char = ref[c]!!
}