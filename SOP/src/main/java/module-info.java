module org.openjfx.sop {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.openjfx.sop to javafx.fxml;
    exports org.openjfx.sop;
}
