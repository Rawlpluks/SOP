module org.openjfx.sop {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.sql;

    opens org.openjfx.sop to javafx.fxml;
    exports org.openjfx.sop;
}
