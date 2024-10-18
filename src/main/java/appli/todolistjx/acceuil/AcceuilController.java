package appli.todolistjx.acceuil;

import appli.todolistjx.StartApplication;
import appli.todolistjx.entity.Liste;
import appli.todolistjx.repository.ListeRepository;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;


import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AcceuilController implements Initializable {

    @FXML
    private TableView<Liste> tableauListe;

    @FXML
    private Label labelErreur;

    @FXML
    private Button disable;

    ListeRepository listeRepo = new ListeRepository();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String [][] colonnes = {
                {"ID Liste","idListe"},
                {"Nom", "nom"}
        };

        for (int i = 0; i <colonnes.length; i++){
            TableColumn<Liste,String> maColonne = new TableColumn<>(colonnes[i][0]);
            maColonne.setCellValueFactory(new PropertyValueFactory<>(colonnes[i][1]));
            tableauListe.getColumns().add(maColonne);
        }
        ArrayList<Liste> list = listeRepo.recupererListe();
        tableauListe.getItems().addAll(list);

        disable.setVisible(false);



    }
    @FXML
    void ajoutListe(ActionEvent actionEvent){
        StartApplication.changeScene("acceuil/ajoutListe","Ajouter une liste");
    }


    @FXML
    void onListeSelection(MouseEvent event){
        if(event.getButton() == MouseButton.PRIMARY && event.getClickCount()==2){
            TablePosition cell = tableauListe.getSelectionModel().getSelectedCells().get(0);
            int indexLigne = cell.getRow();
            TableColumn colone = cell.getTableColumn();
            Liste listesel = tableauListe.getItems().get(indexLigne);
            System.out.println("Double-clique ligne "+indexLigne+" , colone  "+colone.getText()+ " : "+ listesel);
            StartApplication.changeScene("acceuil/editerListeView",new EditerListeController(listesel));
        } else if (event.getButton() == MouseButton.PRIMARY && event.getClickCount() ==1) {
            TablePosition cell = tableauListe.getSelectionModel().getSelectedCells().get(0);
            int indexLigne = cell.getRow();
            TableColumn colone = cell.getTableColumn();
            Liste listesel = tableauListe.getItems().get(indexLigne);
            System.out.println("Simple-click ligne "+indexLigne+" colonne "+colone.getText()+ " : "+listesel);
            disable.setVisible(true);
            int id = listesel.getIdListe();
            disable.setOnAction(event1 -> {
                listeRepo.deleteListe(id,labelErreur);
                disable.setVisible(false);
            });

        }
    }



}
