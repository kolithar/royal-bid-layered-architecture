module lk.ijse.gdse71.royalbid {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;  // Add this line for Lombok support
    requires java.sql;
    requires java.desktop;
    requires mysql.connector.j;

    opens lk.ijse.gdse71.royalbid.DTO to javafx.base;
    opens lk.ijse.gdse71.royalbid.Controller to javafx.fxml;
    exports lk.ijse.gdse71.royalbid;
}
