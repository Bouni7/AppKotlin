package controller

import javafx.beans.property.SimpleStringProperty
import javafx.beans.property.StringProperty
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.fxml.FXML
import javafx.geometry.Insets
import javafx.scene.control.CheckBox
import javafx.scene.control.ScrollPane
import javafx.scene.control.TableColumn
import javafx.scene.control.TableView
import javafx.scene.control.TextField
import javafx.scene.layout.VBox
import javafx.stage.Stage
import utils.split
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.util.*
class FXMLController(var data:ObservableList<kotlin.collections.List<StringProperty>> = FXCollections.observableArrayList(),@FXML var recherche: TextField?=null,@FXML var vBoxScrollPane: VBox? = null, @FXML var scrollPane: ScrollPane? = null, private var stage: Stage? = null, @FXML var mainVBox: VBox? =null, @FXML var vBox:VBox? = null, @FXML var stringTable: TableView<List<StringProperty>>? = null) {
    fun setStage(stage: Stage) { this.stage = stage }
    fun initialiserVue(file: File) {
        var line: String? = ""
        val reader = BufferedReader(FileReader(file.toString()))
        val lineOne = split(reader.readLine())
        for (i in lineOne.indices) {
            val column = TableColumn<List<StringProperty>, String>(lineOne[i].uppercase(Locale.getDefault()))
            stringTable!!.columns.add(column.apply { setCellValueFactory { d: TableColumn.CellDataFeatures<List<StringProperty>, String> -> d.value[i] };column.styleClass.add("col") })
            val checkBox = CheckBox(lineOne[i].uppercase(Locale.getDefault())).apply { isSelected = true;styleClass.add("checkbox");onAction = EventHandler { if (!this.isSelected) stringTable!!.columns[i].setVisible(false) else stringTable!!.columns[i].setVisible(true) } }
            vBoxScrollPane!!.children.add(checkBox)
            VBox.setMargin(checkBox, Insets(10.0, 0.0, 0.0, 20.0))
        }
        scrollPane!!.isFitToHeight = true
        scrollPane!!.isFitToWidth = true
        while (reader.readLine().also { line = it } != null) {
            val row: MutableList<StringProperty> = ArrayList()
            line?.let { split(it) }!!.map { row.add(SimpleStringProperty(it)) }
            data.add(row)
        }
        stringTable!!.items = data
    }
    fun close(actionEvent: ActionEvent?) = stage!!.close()
    fun research(actionEvent: ActionEvent?){
        val motAChercher:StringProperty=SimpleStringProperty(recherche!!.text)
        val dataBis: ObservableList<kotlin.collections.List<StringProperty>> = FXCollections.observableArrayList()
        if(motAChercher.toString() == SimpleStringProperty("").toString()) stringTable!!.items = data
        else{
            data.map { row -> row.map { mot -> if((mot.toString())==motAChercher.toString()) dataBis.add(row) } }
            stringTable!!.items = dataBis
        }
    }
}