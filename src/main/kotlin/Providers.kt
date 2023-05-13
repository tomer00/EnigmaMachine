class Providers {
    companion object {
        fun provideRef(int: Int): Map<Char, Char> {
            return when (int) {
                0 -> {
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
                        Pair('y', 's'), Pair('s', 'y'),
                        Pair('a', 'u'), Pair('u', 'a')
                    )
                }

                1 -> {
                    mapOf(
                        Pair('b', 'o'), Pair('o', 'b'),
                        Pair('h', 's'), Pair('s', 'h'),
                        Pair('d', 'm'), Pair('m', 'd'),
                        Pair('y', 'p'), Pair('p', 'y'),
                        Pair('e', 'v'), Pair('v', 'e'),
                        Pair('g', 'w'), Pair('w', 'g'),
                        Pair('k', 'n'), Pair('n', 'k'),
                        Pair('j', 'u'), Pair('u', 'j'),
                        Pair('c', 't'), Pair('t', 'c'),
                        Pair('a', 'r'), Pair('r', 'a'),
                        Pair('f', 'x'), Pair('x', 'f'),
                        Pair('i', 'q'), Pair('q', 'i'),
                        Pair('l', 'z'), Pair('z', 'l')
                    )
                }

                else -> {
                    mapOf(
                        Pair('h', 'q'), Pair('q', 'h'),
                        Pair('g', 'n'), Pair('n', 'g'),
                        Pair('f', 's'), Pair('s', 'f'),
                        Pair('k', 'l'), Pair('l', 'k'),
                        Pair('j', 'p'), Pair('p', 'j'),
                        Pair('e', 'w'), Pair('w', 'e'),
                        Pair('m', 'z'), Pair('z', 'm'),
                        Pair('a', 't'), Pair('t', 'a'),
                        Pair('d', 'v'), Pair('v', 'd'),
                        Pair('c', 'x'), Pair('x', 'c'),
                        Pair('y', 'o'), Pair('o', 'y'),
                        Pair('b', 'r'), Pair('r', 'b'),
                        Pair('i', 'u'), Pair('u', 'i')
                    )
                }
            }
        }

        fun provideRotor(int: Int): Array<Int> {
            return when (int) {
                0 -> {
                    arrayOf(4,10,12,5,11,6,3,16,21,25,13,19,14,22,24,7,23,20,18,15,0,8,1,17,2,9) // Rotor I
                }1 -> {
                    arrayOf(0,9,3,10,18,8,17,20,23,1,11,7,22,19,12,2,16,6,25,13,15,24,5,21,14,4) // Rotor II
                }2 -> {
                    arrayOf(1,3,5,7,9,11,2,15,17,19,23,21,25,13,24,4,8,22,6,0,10,12,20,18,16,14) // Rotor III
                }3 -> {
                    arrayOf(4,18,14,21,15,25,9,0,24,16,20,8,17,7,23,11,13,5,19,6,10,3,2,12,22,1) // Rotor IV
                }else -> {
                    arrayOf(21,25,1,17,6,8,19,24,20,15,18,3,13,7,11,23,0,22,12,9,16,14,5,4,2,10) // Rotor V
                }

            }
        }
    }
}