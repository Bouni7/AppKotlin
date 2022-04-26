package controller
import javafx.stage.Stage
import vues.Menu
class Controller(stageMenu: Stage?) {
    private val menu: Menu
    init { menu = stageMenu?.let { Menu.creerVue(this, it) }!! }
    private fun showMenu() { menu.show() }
    fun run() { showMenu() }
}