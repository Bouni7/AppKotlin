package vues
import controller.Controller
import controller.FXMLController
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.scene.control.TextField
import javafx.scene.layout.VBox
import javafx.stage.FileChooser
import javafx.stage.Stage
import java.io.File
import java.io.IOException
import java.util.*

class Menu(@FXML var mainVbox: VBox? = null, @FXML var fichier: TextField? = null,private var controller: Controller? = null ,private var file: File? = null) : Vue(), VueInteractive {
    val top: Parent?
        get() = mainVbox
    private val bundle: ResourceBundle = ResourceBundle.getBundle("strings")
    fun browse(actionEvent: ActionEvent) {
        val fileChooser = FileChooser()
        fileChooser.extensionFilters.add(FileChooser.ExtensionFilter("CSV", "*.csv"))
        file = fileChooser.showOpenDialog(stage)
        if (file== null) fichier!!.text = bundle.getString("erreurFichierNull") else fichier!!.text = file.toString()
    }
    fun execute(actionEvent: ActionEvent) {
        if (file == null) { fichier!!.text = bundle.getString("erreurFichierNull")
        } else {
            val fxmlLoader = FXMLLoader(javaClass.getResource("fileSort.fxml"), bundle)
            val root1: Parent?
            root1 = fxmlLoader.load<Any>() as Parent
            val fxmlController = fxmlLoader.getController<FXMLController>()
            val stage = Stage()
            stage.apply {  stage!!.title = bundle.getString("titleWindow");stage.scene = Scene(root1);stage.show()}
            fxmlController.apply { setStage(stage); initialiserVue(file!!)}
        }
    }
    override fun setControleur(controller: Controller?) { this.controller = controller }
    companion object {
        fun creerVue(controller: Controller, stage: Stage): Menu {
            val bundle = ResourceBundle.getBundle("strings")
            val fxmlLoader = FXMLLoader(Menu::class.java.getResource("menu.fxml"), bundle)
            try {fxmlLoader.load<Any>()} catch (e: IOException) { println("Probleme de construction de vue Formulaire") }
            val vue = fxmlLoader.getController<Menu>()
            vue.apply { vue.stage=stage;setControleur(controller);vue.scene=Scene(vue.top)}
            stage.title = bundle.getString("titleMenu")
            return vue
        }
    }
}