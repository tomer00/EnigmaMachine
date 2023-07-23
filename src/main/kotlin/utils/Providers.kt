package utils

class Providers {
    companion object {

        const val PATH_SRC = "/home/tom/test/enigma/"

        fun provideRef(int: Int): Map<Char, Char> {
            return when (int) {
                0 -> {
                    mapOf(
                        'a' to 'e','b' to 'j','c' to 'm','d' to 'z','e' to 'a',
                        'f' to 'l','g' to 'y','h' to 'x','i' to 'v','j' to 'b',
                        'k' to 'w','l' to 'f','m' to 'c','n' to 'r','o' to 'q',
                        'p' to 'u','q' to 'o','r' to 'n','s' to 't','t' to 's',
                        'u' to 'p','v' to 'i','w' to 'k','x' to 'h','y' to 'g','z' to 'd'
                    )
                }
                1 -> {
                    mapOf(
                        'a' to 'y','b' to 'r','c' to 'u','d' to 'h','e' to 'q',
                        'f' to 's','g' to 'l','h' to 'd','i' to 'p','j' to 'x',
                        'k' to 'n','l' to 'g','m' to 'o','n' to 'k','o' to 'm',
                        'p' to 'i','q' to 'e','r' to 'b','s' to 'f','t' to 'z',
                        'u' to 'c','v' to 'w','w' to 'v','x' to 'j','y' to 'a','z' to 't'
                    )
                }
                else -> {
                    mapOf(
                        'a' to 'f','b' to 'v','c' to 'p','d' to 'j','e' to 'i',
                        'f' to 'a','g' to 'o','h' to 'y','i' to 'e','j' to 'd',
                        'k' to 'r','l' to 'z','m' to 'x','n' to 'w','o' to 'g',
                        'p' to 'c','q' to 't','r' to 'k','s' to 'u','t' to 'q',
                        'u' to 's','v' to 'b','w' to 'n','x' to 'm','y' to 'h','z' to 'l'
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