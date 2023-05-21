import board.Reflector
import machine.Machine
import utils.ConsoleColors
import utils.MachineUtils
import utils.MachineUtils.Companion.saveFile
import utils.MachineUtils.Companion.toMachine
import utils.MachineUtils.Companion.toSaveString
import java.io.File
import java.util.*
import javax.swing.JFileChooser
import javax.swing.JPanel
import javax.swing.filechooser.FileNameExtensionFilter

fun main() {

    var m = MachineUtils.generateRandom()
    drawEnigmaMachine()
    drawMenu()
    var input: String
    do {
        input = readln()
        print(ConsoleColors.RESET)
        when (input) {
            "1" -> {
                clearConsole()
                print("${ConsoleColors.YELLOW_BOLD_BRIGHT}Enter your string to encode :- ${ConsoleColors.WHITE_UNDERLINED}")
                val s = readln()
                print("${ConsoleColors.RESET}${ConsoleColors.YELLOW_BOLD_BRIGHT}Your Encoded String :- ")
                for (i in s.indices) {
                    print(ConsoleColors.CYAN_BRIGHT + m.getEncoded(s[i]))
                    Thread.sleep(200)
                }
                print(ConsoleColors.RESET)
                drawMenu()
                m.resetMachine()
            }

            "2" -> {
                // handle decryption
                clearConsole()
                print("${ConsoleColors.YELLOW_BOLD_BRIGHT}Enter your encoded string to decode :- ${ConsoleColors.WHITE_UNDERLINED}")
                val s = readln()
                print("${ConsoleColors.RESET}${ConsoleColors.YELLOW_BOLD_BRIGHT}Your Decoded String :- ")
                for (i in s.indices) {
                    print(ConsoleColors.CYAN_BRIGHT + m.getEncoded(s[i]))
                    Thread.sleep(200)
                }
                print(ConsoleColors.RESET)
                drawMenu()
                m.resetMachine()
            }

            "3" -> {
                // Enigma File Load
                val jFC = JFileChooser()
                jFC.fileFilter = FileNameExtensionFilter("Enigma File...", "enigma")
                val ret = jFC.showOpenDialog(JPanel())
                if (ret == JFileChooser.APPROVE_OPTION && jFC.selectedFile?.extension == "enigma") {
                    try {
                        val f = jFC.selectedFile
                        m = f.toMachine()
                        //Handle Success msg
                        print(ConsoleColors.GREEN_BRIGHT)
                        print("Well Ahh!! Your machine is ready...")
                        print(ConsoleColors.RESET)
                        Thread.sleep(2000)
                        clearConsole()
                        drawMenu()
                    } catch (e: Exception) {
                        print(ConsoleColors.RED)
                        print(e.message)
                        print(ConsoleColors.RESET)
                        clearConsole()
                        drawMenu()
                    }

                } else {
                    print(ConsoleColors.RED)
                    print("Please Select a file..")
                    print(ConsoleColors.RESET)
                    clearConsole()
                    drawMenu()
                }
            }

            "4" -> {
                // new Machine
                clearConsole()
                drawEnigmaMachine()

                var ref = Reflector.`UKW-A`
                val rotors = arrayOf(0, 0, 0, 0, 0, 0)
                val plug = mutableListOf<Pair<Char, Char>>()

                println("${ConsoleColors.PURPLE_BRIGHT}\nReflectors --\n")
                println("${ConsoleColors.YELLOW_BOLD_BRIGHT}  1 ${ConsoleColors.RED_BRIGHT}- ${ConsoleColors.CYAN_BOLD}UKW-A")
                println("${ConsoleColors.YELLOW_BOLD_BRIGHT}  2 ${ConsoleColors.RED_BRIGHT}- ${ConsoleColors.CYAN_BOLD}UKW-B")
                println("${ConsoleColors.YELLOW_BOLD_BRIGHT}  3 ${ConsoleColors.RED_BRIGHT}- ${ConsoleColors.CYAN_BOLD}UKW-C\n")
                print("${ConsoleColors.WHITE_BOLD_BRIGHT}Select Reflector form above : ")


                var inp = ""
                inp = readln()
                while (inp[0] !in '1'..'3') {
                    print("${ConsoleColors.RED_BRIGHT}Invalid choice, please try again : ${ConsoleColors.YELLOW_BOLD_BRIGHT}")
                    inp = readln()
                }
                ref = inp[0].toString().toInt()

                val sc = Scanner(System.`in`)

                println("${ConsoleColors.PURPLE_BRIGHT}\nRotors --\n")
                println("${ConsoleColors.YELLOW_BOLD_BRIGHT}  1 ${ConsoleColors.RED_BRIGHT}- ${ConsoleColors.CYAN_BOLD}I")
                println("${ConsoleColors.YELLOW_BOLD_BRIGHT}  2 ${ConsoleColors.RED_BRIGHT}- ${ConsoleColors.CYAN_BOLD}II")
                println("${ConsoleColors.YELLOW_BOLD_BRIGHT}  3 ${ConsoleColors.RED_BRIGHT}- ${ConsoleColors.CYAN_BOLD}III")
                println("${ConsoleColors.YELLOW_BOLD_BRIGHT}  4 ${ConsoleColors.RED_BRIGHT}- ${ConsoleColors.CYAN_BOLD}VI")
                println("${ConsoleColors.YELLOW_BOLD_BRIGHT}  5 ${ConsoleColors.RED_BRIGHT}- ${ConsoleColors.CYAN_BOLD}V\n")
                print("${ConsoleColors.WHITE_BOLD_BRIGHT}Select 3 Rotors form above : ${ConsoleColors.YELLOW_BOLD_BRIGHT}")

                for (i in 0..2) {
                    rotors[i] = sc.nextInt()
                    while (rotors[i] !in 1..5) {
                        print("${ConsoleColors.RED_BRIGHT}Invalid choice, please try again : ${ConsoleColors.YELLOW_BOLD_BRIGHT}")
                        rotors[i] = sc.nextInt()
                    }
                }

                println("${ConsoleColors.PURPLE_BRIGHT}\nRotors starting Positions")
                print("${ConsoleColors.WHITE_BOLD_BRIGHT}\nEnter rotor start positions of \nthree rotors respectively -- ${ConsoleColors.YELLOW_BOLD_BRIGHT}")

                for (i in 3..5) {
                    rotors[i] = sc.nextInt()
                    while (rotors[i] !in 1..26) {
                        print("${ConsoleColors.RED_BRIGHT}Invalid choice, please try again : ${ConsoleColors.YELLOW_BOLD_BRIGHT}")
                        rotors[i] = sc.nextInt()
                    }
                }


                println("${ConsoleColors.PURPLE_BRIGHT}\n-- Plug-Board --")
                print("${ConsoleColors.WHITE_BOLD_BRIGHT}\nEnter space separated pairs of chars a-z -- ${ConsoleColors.YELLOW_BOLD_BRIGHT}")

                inp = readln()
                var result = inp.replace("\\s".toRegex(), "")

                val pattern = Regex("[a-zA-Z]+")
                while (!result.matches(pattern) || (result.length % 2) != 0) {
                    print("${ConsoleColors.RED_BRIGHT}Invalid chars, please try again : ${ConsoleColors.YELLOW_BOLD_BRIGHT}")
                    inp = readln()
                    result = inp.replace("\\s".toRegex(), "")
                }

                for (i in result.indices step 2) {
                    plug.add(Pair(result[i], result[i + 1]))
                }

                m = Machine.Builder()
                    .setReflector(ref - 1)
                    .setRotars(rotors.copyOfRange(0, 3), rotors.copyOfRange(3, 6))
                    .setPlugBoard(plug.toTypedArray())
                    .build()

                clearConsole()
                printMachine(m)

            }

            "5" -> {
                // Save enigma File
                clearConsole()
                print("${ConsoleColors.PURPLE_BRIGHT}  Name of your machine : ${ConsoleColors.YELLOW_BOLD_BRIGHT}")
                val name = readln()
                val fileEnigma = File("machines/", "$name.enigma")
                fileEnigma.parentFile.mkdirs()
                m.saveFile(fileEnigma)
                //Handle Success msg
                print(ConsoleColors.GREEN_BRIGHT)
                print("${ConsoleColors.GREEN_BRIGHT}  Your File is Saved in ${ConsoleColors.WHITE_BOLD_BRIGHT}${fileEnigma.absolutePath}")
                print(ConsoleColors.RESET)
                Thread.sleep(2000)
                drawMenu()
            }

            "6" -> {
                // Print Enigma
                clearConsole()
                printMachine(m)
            }

            "7" -> {
                clearConsole()
                println("${ConsoleColors.RED_BACKGROUND_BRIGHT}${ConsoleColors.WHITE_BOLD_BRIGHT}Exiting program...${ConsoleColors.RESET}")
                return
            }

            else -> {
                print("${ConsoleColors.RED_BRIGHT}Invalid choice, please try again : ${ConsoleColors.YELLOW_BOLD_BRIGHT}")
            }
        }
    } while (true)


}

fun printMachine(m : Machine){
    println("${ConsoleColors.RED_BACKGROUND_BRIGHT}${ConsoleColors.WHITE_BOLD_BRIGHT}   Your Current Machine Settings are ---   ${ConsoleColors.RESET}\n")
    val mach = m.toSaveString().split('\n')
    val ref = when (mach[0][10]) {
        '0' -> "UKW-A"
        '1' -> "UKW-B"
        else -> "UKW-C"
    }
    println("   ${ConsoleColors.CYAN_BOLD}Reflector  ${ConsoleColors.RED_BRIGHT}:- ${ConsoleColors.YELLOW_BOLD_BRIGHT}$ref")
    println(
        "   ${ConsoleColors.CYAN_BOLD}Rotors     ${ConsoleColors.RED_BRIGHT}:- ${ConsoleColors.YELLOW_BOLD_BRIGHT}${
            mach[1].subSequence(
                7,
                mach[1].length
            )
        }"
    )
    println(
        "   ${ConsoleColors.CYAN_BOLD}RotorsPos  ${ConsoleColors.RED_BRIGHT}:- ${ConsoleColors.YELLOW_BOLD_BRIGHT}${
            mach[2].subSequence(
                10,
                mach[2].length
            )
        }"
    )
    println(
        "   ${ConsoleColors.CYAN_BOLD}Plug Pairs ${ConsoleColors.RED_BRIGHT}:- ${ConsoleColors.YELLOW_BOLD_BRIGHT}${
            mach[3].subSequence(
                6,
                mach[3].length
            )
        }"
    )
    Thread.sleep(2_000)
    drawMenu()
}

fun clearConsole() {
    val processBuilder = ProcessBuilder("clear")
    val environment = processBuilder.environment()
    environment["TERM"] = "xterm" // Set the TERM environment variable to "xterm"
    processBuilder.inheritIO().start().waitFor()
}

fun drawMenu() {
    print(
        "\n     ${ConsoleColors.RESET}${ConsoleColors.WHITE_BOLD_BRIGHT}Enigma Machine Simulation Menu\n" +
                "\n" +
                "     ${ConsoleColors.YELLOW_BOLD_BRIGHT}1. ${ConsoleColors.PURPLE_BRIGHT}Encrypt message\n" +
                "     ${ConsoleColors.YELLOW_BOLD_BRIGHT}2. ${ConsoleColors.PURPLE_BRIGHT}Decrypt message\n" +
                "     ${ConsoleColors.YELLOW_BOLD_BRIGHT}3. ${ConsoleColors.PURPLE_BRIGHT}Load Enigma from file\n" +
                "     ${ConsoleColors.YELLOW_BOLD_BRIGHT}4. ${ConsoleColors.PURPLE_BRIGHT}Create new Machine\n" +
                "     ${ConsoleColors.YELLOW_BOLD_BRIGHT}5. ${ConsoleColors.PURPLE_BRIGHT}Save current Enigma Machine as File\n" +
                "     ${ConsoleColors.YELLOW_BOLD_BRIGHT}6. ${ConsoleColors.PURPLE_BRIGHT}Print Enigma settings as String\n" +
                "     ${ConsoleColors.YELLOW_BOLD_BRIGHT}7. ${ConsoleColors.PURPLE_BRIGHT}Quit\n" +
                "\n" +
                "${ConsoleColors.WHITE_BOLD_BRIGHT}     Enter your choice: ${ConsoleColors.YELLOW_BOLD_BRIGHT}"
    )
}

fun drawEnigmaMachine() {
    clearConsole()
    val machine = """
         __________________________________________
        |               ${ConsoleColors.YELLOW_BOLD_BRIGHT}ENIGMA MACHINE${ConsoleColors.RED_BRIGHT}             |
        |  ________       ________       ________  |
        | |        |     |        |     |        | |
        | |________|     |________|     |________| |
        | |  ____  |     |  ____  |     |  ____  | |
        | | |    | |     | |    | |     | |    | | |
        | | |____| |     | |____| |     | |____| | |
        | |  ____  |     |  ____  |     |  ____  | |
        | | |    | |     | |    | |     | |    | | |
        | | |____| |     | |____| |     | |____| | |
        | |________|     |________|     |________| |
        |__________________________________________|
    """.trimIndent()
    print(ConsoleColors.RED_BRIGHT)
    println(machine)
}