import utils.ConsoleColors
import utils.MachineUtils
import utils.MachineUtils.Companion.saveFile
import utils.MachineUtils.Companion.toMachine
import utils.MachineUtils.Companion.toSaveString
import java.io.File
import javax.swing.JFileChooser
import javax.swing.JPanel
import javax.swing.filechooser.FileNameExtensionFilter

fun main() {

    var m = MachineUtils.generateRandom()
    drawEnigmaMachine()
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
                println("${ConsoleColors.RED_BACKGROUND_BRIGHT}${ConsoleColors.WHITE_BOLD_BRIGHT}   Your Current Machine Settings are ---   ${ConsoleColors.RESET}\n")
                val mach = m.toSaveString().split('\n')
                val ref = when(mach[0][10]){
                    '0'-> "UKW-A"
                    '1'-> "UKW-B"
                    else-> "UKW-C"
                }
                println("   ${ConsoleColors.CYAN_BOLD}Reflector  ${ConsoleColors.RED_BRIGHT}:- ${ConsoleColors.YELLOW_BOLD_BRIGHT}$ref")
                println("   ${ConsoleColors.CYAN_BOLD}Rotors     ${ConsoleColors.RED_BRIGHT}:- ${ConsoleColors.YELLOW_BOLD_BRIGHT}${mach[1].subSequence(7,mach[1].length)}")
                println("   ${ConsoleColors.CYAN_BOLD}RotorsPos  ${ConsoleColors.RED_BRIGHT}:- ${ConsoleColors.YELLOW_BOLD_BRIGHT}${mach[2].subSequence(10,mach[2].length)}")
                println("   ${ConsoleColors.CYAN_BOLD}Plug Pairs ${ConsoleColors.RED_BRIGHT}:- ${ConsoleColors.YELLOW_BOLD_BRIGHT}${mach[3].subSequence(6,mach[3].length)}")
                Thread.sleep(2_000)
                drawMenu()
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
    drawMenu()
}