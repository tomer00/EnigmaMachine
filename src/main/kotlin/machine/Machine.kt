package machine

import board.PlugBoard
import board.Reflector
import board.Rotar

class Machine private constructor() {

    private val rotars = arrayOf<Rotar?>(
        null, null, null
    )
    private var ref = Reflector(Reflector.reflectors[0])
    private val plugBoard = PlugBoard()


    class Builder internal constructor() {

        private val internalMachine = Machine()

        fun build(): Machine = internalMachine

        fun setRotars(rotars: Array<Int>, pos: Array<Int>): Builder {
            internalMachine.rotars[0] = Rotar(Rotar.rotars[0].first, Rotar.rotars[0].second, pos[0])
            internalMachine.rotars[1] = Rotar(Rotar.rotars[1].first, Rotar.rotars[1].second, pos[1])
            internalMachine.rotars[2] = Rotar(Rotar.rotars[2].first, Rotar.rotars[2].second, pos[2])
            return this
        }

        fun setReflector(ref: Int): Builder {
            internalMachine.ref = Reflector(Reflector.reflectors[ref])
            return this
        }

        fun setPlugBoard(pairs: Array<Pair<Char, Char>>) :Builder{
            for (p in pairs) {
                internalMachine.plugBoard.setPair(p.first, p.second)
            }
            return this
        }
    }

}