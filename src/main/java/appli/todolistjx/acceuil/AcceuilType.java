package appli.todolistjx.acceuil;

import appli.todolistjx.StartApplication;
import appli.todolistjx.entity.Liste;
import appli.todolistjx.entity.Tache;
import appli.todolistjx.entity.Type;
import appli.todolistjx.repository.TypeRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class AcceuilType implements Initializable {
    @FXML
    private Button ajouttype;

    @FXML
    private Label erreur;

    @FXML
    private Button modifier;

    @FXML
    private Button retour;

    @FXML
    private Button supprimer;

    @FXML
    private TableView<Type> tableautype;

    private Type type;

    TypeRepository typeRepository = new TypeRepository();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String [][] colonnes = {
                {"ID Tache","id"},
                {"Nom", "nom"},
                {"Code Couleur", "codeCouleur"},
        };

        for (int i = 0; i <colonnes.length; i++){
            TableColumn<Type,String> maColonne = new TableColumn<>(colonnes[i][0]);
            maColonne.setCellValueFactory(new PropertyValueFactory<>(colonnes[i][1]));
            tableautype.getColumns().add(maColonne);
        }
        ArrayList<Type> list = typeRepository.recupererType();
        tableautype.getItems().addAll(list);

        modifier.setVisible(false);
        supprimer.setVisible(false);
    }

    @FXML
    void retour(ActionEvent event) {
        StartApplication.changeScene("acceuil/acceuil","Acceuil");
    }

    @FXML
    void ajoutType(ActionEvent event){
        StartApplication.changeScene("acceuil/ajoutType","Ajout Type de tache");
    }

    @FXML
    void onListeSelection(MouseEvent event){
        if(event.getButton() == MouseButton.PRIMARY && event.getClickCount()==2){
            TablePosition cell = tableautype.getSelectionModel().getSelectedCells().get(0);
            int indexLigne = cell.getRow();
            TableColumn colone = cell.getTableColumn();
            Type typesel = tableautype.getItems().get(indexLigne);
            System.out.println("Double-clique ligne "+indexLigne+" , colone  "+colone.getText()+ " : "+ typesel);
            StartApplication.changeScene("acceuil/editerTypeview",new EditerTypeController(typesel));
        } else if (event.getButton() == MouseButton.PRIMARY && event.getClickCount() ==1) {
            TablePosition cell = tableautype.getSelectionModel().getSelectedCells().get(0);
            int indexLigne = cell.getRow();
            TableColumn colone = cell.getTableColumn();
            Type typesel = tableautype.getItems().get(indexLigne);
            System.out.println("Simple-click ligne "+indexLigne+" colonne "+colone.getText()+ " : "+typesel);
            supprimer.setVisible(true);
            int id = typesel.getId();
            supprimer.setOnAction(event1 -> {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation de suppression");
                alert.setHeaderText("Êtes-vous sûr de vouloir supprimer la liste ?");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    typeRepository.deleteType(id,erreur);
                    supprimer.setVisible(false);
                }else {
                    erreur.setText("Supression annulée");
                    supprimer.setVisible(false);
                }
            });

        }
    }


}
