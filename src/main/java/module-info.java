module cacttus.education.cacttusproductcrud {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires com.microsoft.sqlserver.jdbc;


    opens cacttus.education.cacttusproductcrud to javafx.fxml;
    exports cacttus.education.cacttusproductcrud;
    exports cacttus.education.cacttusproductcrud.controllers;
    opens cacttus.education.cacttusproductcrud.controllers to javafx.fxml;
}