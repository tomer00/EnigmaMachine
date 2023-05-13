package ui

import machine.Machine
import utils.MachineUtils.Companion.toSaveString

fun main(args: Array<String>) {

    val m = Machine.Builder()
        .setReflector(2)
        .setRotars(arrayOf(2, 2, 0), arrayOf(24, 15, 6))
        .build()

    m.toSaveString()

    println(m.getEncoded("fpvisvhcsbrurwoxoiznxixciylw"))


}