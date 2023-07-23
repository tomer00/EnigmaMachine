import ui.MainScreen
import utils.Providers
import java.awt.Dimension
import javax.swing.ImageIcon
import javax.swing.JFrame

fun main() {
    MainScreen().apply {
        title = "Enigma Machine"
        size = Dimension(1000, 600)
        setLocationRelativeTo(null)
        iconImage = ImageIcon(Providers.PATH_SRC + "iconEnigma.png").image
        isVisible = true
        defaultCloseOperation = JFrame.DISPOSE_ON_CLOSE
    }
}