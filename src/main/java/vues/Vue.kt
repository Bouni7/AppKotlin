package vues

import javafx.scene.Scene
import javafx.stage.Stage
abstract class Vue(var stage: Stage? = null, var scene: Scene? = null) {
    fun show() {
        stage!!.scene = scene
        stage!!.show()
    }
}