module com.rewdev.tts {
    requires javafx.controls;
    requires javafx.fxml;
    requires freetts;
    requires java.desktop;

    opens com.rewdev.tts to javafx.fxml;
    exports com.rewdev.tts;
}
