package machine

import utils.Providers
import board.PlugBoard
import board.Reflector
import board.Rotor

class Machine private constructor() {

    private val rotors = arrayOf<Rotor?>(
        null, null, null
    )
    private var ref = Reflector(Providers.provideRef(0))
    private val plugBoard = PlugBoard()


    val rotarsInfo = mutableListOf<Int>() // which rotar{1,2,0} startPos{25,23,12}
    var reflectorInfo = 0
    fun getPlugBoard() = plugBoard.getBoard()


    fun getEncoded(c: Char): Char {

        if (c in 'a'..'z') {

            var cn = c
            cn = plugBoard.getChar(cn)

            var cinn = rotors[2]!!.forward(cn - 'a')
            cinn = rotors[1]!!.forward(cinn)
            cinn = rotors[0]!!.forward(cinn)

            cn = ref.reflect('a' + cinn)

            cinn = rotors[0]!!.backward(cn - 'a')
            cinn = rotors[1]!!.backward(cinn)
            cinn = rotors[2]!!.backward(cinn)



            rotors[2]!!.rotate()
            return plugBoard.getChar('a' + cinn) - 32
        } else if (c in 'A'..'Z')
            return getEncoded(c + 32)

        return c
    }

    fun getEncoded(string: String): String {
        val str = StringBuilder()
        string.lowercase().forEach {
            str.append(getEncoded(it))
        }
        return str.toString()
    }
    fun resetMachine() {
        val r1 = Rotor(Providers.provideRotor(rotarsInfo[0]), null)
        val r2 = Rotor(Providers.provideRotor(rotarsInfo[1]), r1)
        val r3 = Rotor(Providers.provideRotor(rotarsInfo[2]), r2)
        this.rotors[0] = r1
        this.rotors[1] = r2
        this.rotors[2] = r3

        for (i in 0..2) {
            val p = rotarsInfo[i + 3] % 26
            for (j in 0 until p) this.rotors[i]!!.rotate()
        }
    }

    //region ::BUILDER--->>

    class Builder internal constructor() {

        private val internalMachine = Machine()

        fun build(): Machine = internalMachine

        fun setRotars(rotars: Array<Int>, pos: Array<Int>): Builder {
            val r1 = Rotor(Providers.provideRotor(rotars[0]), null)
            val r2 = Rotor(Providers.provideRotor(rotars[1]), r1)
            val r3 = Rotor(Providers.provideRotor(rotars[2]), r2)
            internalMachine.rotors[0] = r1
            internalMachine.rotors[1] = r2
            internalMachine.rotors[2] = r3

            for (i in 0..2) {
                val p = pos[i] % 26
                for (j in 0 until p) internalMachine.rotors[i]!!.rotate()
            }

            internalMachine.rotarsInfo.addAll(rotars)
            internalMachine.rotarsInfo.addAll(pos)
            return this
        }

        fun setReflector(ref: Int): Builder {
            internalMachine.ref = Reflector(Providers.provideRef(ref))
            internalMachine.reflectorInfo = ref
            return this
        }

        fun setPlugBoard(pairs: Array<Pair<Char, Char>>): Builder {
            for (p in pairs) {
                internalMachine.plugBoard.setPair(p.first, p.second)
            }
            return this
        }
    }

    //endregion ::BUILDER--->>

}