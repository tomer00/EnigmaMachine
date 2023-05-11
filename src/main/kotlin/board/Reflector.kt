package board

class Reflector(private val ref: Map<Char, Char>) {

    //region CREATING DEFAULT REFLECTOR

    companion object {
        val reflectors = arrayOf(
            mapOf(
                Pair('b', 'n'), Pair('n', 'b'),
                Pair('m', 'l'), Pair('l', 'm'),
                Pair('d', 'o'), Pair('o', 'd'),
                Pair('k', 't'), Pair('t', 'k'),
                Pair('c', 'x'), Pair('x', 'c'),
                Pair('i', 'v'), Pair('v', 'i'),
                Pair('j', 'r'), Pair('r', 'j'),
                Pair('f', 'w'), Pair('w', 'f'),
                Pair('g', 'q'), Pair('q', 'g'),
                Pair('h', 'p'), Pair('p', 'h'),
                Pair('e', 'z'), Pair('z', 'e'),
                Pair('l', 's'), Pair('s', 'l'),
                Pair('a', 'u'), Pair('u', 'a')
            ), mapOf(
                Pair('b','o'),Pair('o','b'),
                Pair('h','s'),Pair('s','h'),
                Pair('d','m'),Pair('m','d'),
                Pair('m','p'),Pair('p','m'),
                Pair('e','v'),Pair('v','e'),
                Pair('g','w'),Pair('w','g'),
                Pair('k','n'),Pair('n','k'),
                Pair('j','u'),Pair('u','j'),
                Pair('c','t'),Pair('t','c'),
                Pair('a','r'),Pair('r','a'),
                Pair('f','x'),Pair('x','f'),
                Pair('i','q'),Pair('q','i'),
                Pair('l','z'),Pair('z','l')
            ), mapOf(
                Pair('h','q'),Pair('q','h'),
                Pair('g','n'),Pair('n','g'),
                Pair('f','s'),Pair('s','f'),
                Pair('k','l'),Pair('l','k'),
                Pair('j','p'),Pair('p','j'),
                Pair('e','w'),Pair('w','e'),
                Pair('m','z'),Pair('z','m'),
                Pair('a','t'),Pair('t','a'),
                Pair('d','v'),Pair('v','d'),
                Pair('c','x'),Pair('x','c'),
                Pair('l','o'),Pair('o','l'),
                Pair('b','r'),Pair('r','b'),
                Pair('i','u'),Pair('u','i')
            )
        )

    }

    //endregion CREATING DEFAULT REFLECTOR

    fun reflect(c: Char): Char = ref[c]!!
}