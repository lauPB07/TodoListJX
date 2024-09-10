module appli.todolistjx {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;

    opens appli.todolistjx to javafx.fxml;
    exports appli.todolistjx;
    exports appli.todolistjx.acceuil;
    opens appli.todolistjx.acceuil to javafx.fxml;
}