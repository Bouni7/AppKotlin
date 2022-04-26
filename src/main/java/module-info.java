module permissions {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires kotlin.stdlib;

    opens vues to javafx.fxml;
    opens controller to javafx.fxml;
    exports pnt;
}