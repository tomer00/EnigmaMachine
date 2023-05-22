package utils

import machine.Machine
import java.io.File
import java.io.FileOutputStream
import kotlin.random.Random

class MachineUtils {
    companion object {
        fun Machine.toSaveString(): String {
            val sb = StringBuilder()
            sb.append("Reflector:${this.reflectorInfo}")
            sb.append('\n')
            sb.append("Rotors:${this.rotarsInfo[0]+1},${this.rotarsInfo[1]+1},${this.rotarsInfo[2]+1}")
            sb.append('\n')
            sb.append("RotorsPos:${this.rotarsInfo[3]+1}-${this.rotarsInfo[4]+1}-${this.rotarsInfo[5]+1}")
            sb.append('\n')
            sb.append("Pairs:")

            val m = mutableMapOf(Pair('-', 0))
            this.getPlugBoard().forEach { entry ->
                if (!m.containsKey(entry.key)) {
                    m[entry.value] = 0
                    sb.append(entry.key)
                    sb.append(entry.value)
                    sb.append(' ')
                }
            }

            sb.deleteCharAt(sb.lastIndex)
            return sb.toString()
        }

        @Throws(Exception::class)
        fun Machine.saveFile(file: File) {
            FileOutputStream(file).use { fos ->
                try {
                    fos.write(this.reflectorInfo)
                    fos.write(this.rotarsInfo[0])
                    fos.write(this.rotarsInfo[1])
                    fos.write(this.rotarsInfo[2])
                    fos.write(this.rotarsInfo[3])
                    fos.write(this.rotarsInfo[4])
                    fos.write(this.rotarsInfo[5])

                    val m = mutableMapOf(Pair('-', 0))
                    this.getPlugBoard().forEach { entry ->
                        if (!m.containsKey(entry.key)) {
                            m[entry.value] = 0
                            fos.write(entry.key.code)
                            fos.write(entry.value.code)
                        }
                    }
                } catch (_: Exception) {
                    throw Exception("Some Error Occurred")
                }
            }
        }

        @Throws(Exception::class)
        fun File.toMachine(): Machine {
            if (this.extension != "enigma") throw Exception("Provide .enigma file")

            this.inputStream().use { ins ->
                try {
                    val bul = Machine.Builder()
                        .setReflector(ins.read())
                        .setRotars(
                            arrayOf(
                                ins.read(),
                                ins.read(),
                                ins.read()
                            ), arrayOf(
                                ins.read(),
                                ins.read(),
                                ins.read()
                            )
                        )
                    val p = mutableListOf<Pair<Char, Char>>()
                    while (ins.available() > 0) {
                        val c1 = ins.read().toChar()
                        val c2 = ins.read().toChar()
                        if (c1 !in 'a'..'z' || c2 !in 'a'..'z')
                            throw Exception("Provided enigma file is not correct...")
                        p.add(Pair(c1, c2))
                    }
                    return bul.setPlugBoard(p.toTypedArray()).build()
                } catch (e: Exception) {
                    throw e
                }
            }
        }

        @Throws(Exception::class)
        fun String.toMachine(): Machine {
            val sts = this.split('\n')
            if (sts.size < 4)
                throw Exception("Provide valid Enigma Machine String...")

            try {
                //Reflector
                val bul = Machine.Builder().setReflector(sts[0][sts[0].lastIndex].code - '0'.code)

                //Rotors
                val rotors = sts[1].subSequence(7, sts[1].length).split(',')
                val rotorsPos = sts[2].subSequence(10, sts[2].length).split('-')
                bul.setRotars(
                    arrayOf(
                        rotors[0].toInt()-1,
                        rotors[1].toInt()-1,
                        rotors[2].toInt()-1
                    ), arrayOf(
                        rotorsPos[0].toInt()-1,
                        rotorsPos[1].toInt()-1,
                        rotorsPos[2].toInt()-1
                    )
                )

                val p = mutableListOf<Pair<Char, Char>>()
                val pairs = sts[3].subSequence(6, sts[3].length).split(' ')
                pairs.forEach { kv ->
                    p.add(Pair(kv[0], kv[1]))
                }
                bul.setPlugBoard(p.toTypedArray())
                return bul.build()
            } catch (e: Exception) {
                throw e
            }

        }

        fun generateRandom(): Machine {
            val r = Random(System.currentTimeMillis())
            return Machine.Builder()
                .setRotars(
                    arrayOf(
                        r.nextInt(5),
                        r.nextInt(5),
                        r.nextInt(5)
                    ), arrayOf(
                        r.nextInt(26),
                        r.nextInt(26),
                        r.nextInt(26)
                    )
                )
                .setReflector(r.nextInt(3))
                .setPlugBoard(
                    arrayOf(
                        Pair('a' + r.nextInt(26), 'a' + r.nextInt(26)),
                        Pair('a' + r.nextInt(26), 'a' + r.nextInt(26)),
                        Pair('a' + r.nextInt(26), 'a' + r.nextInt(26)),
                        Pair('a' + r.nextInt(26), 'a' + r.nextInt(26))
                    )
                )
                .build()
        }
    }
}