package utils

import machine.Machine
import java.io.File
import java.io.FileOutputStream
import kotlin.jvm.Throws
import kotlin.random.Random

class MachineUtils {
    companion object {
        fun Machine.toSaveString(): String {
            val sb = StringBuilder()
            sb.append("Reflector:${this.reflectorInfo}")
            sb.append('\n')
            sb.append("Rotars:${this.rotarsInfo[0]},${this.rotarsInfo[1]},${this.rotarsInfo[2]}")
            sb.append('\n')
            sb.append("RotarsPos:${this.rotarsInfo[3]},${this.rotarsInfo[4]},${this.rotarsInfo[5]}")
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
        fun Machine.saveFile(parentFol: File): File {
            if (!parentFol.isDirectory) throw Exception("Provided file is not Directory")
            val outFile = File(parentFol,"machine.enigma")
            FileOutputStream(outFile).use { fos ->
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
                    return outFile
                } catch (_: Exception) {
                    throw Exception("Some Error Occurred")
                }
            }
        }

//        @Throws(Exception::class)
//        fun File.toMachine(): Machine {
//
//        }
//
//        @Throws(Exception::class)
//        fun String.toMachine(): Machine {
//
//        }

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