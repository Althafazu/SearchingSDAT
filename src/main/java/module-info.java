module org.example.searchingsdat {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires org.apache.commons.lang3;

    opens org.example.searchingsdat to javafx.fxml;
    exports org.example.searchingsdat;}