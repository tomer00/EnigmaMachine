package board

class Reflector(private val ref: Map<Char, Char>) {

    companion object{
        const val `UKW-A` = 0
        const val `UKW-B` = 1
        const val `UKW-C` = 2
    }
    fun reflect(c: Char): Char = ref[c]!!
}