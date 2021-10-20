package dao;


import configuration.ConfigProperties;
import javafx.scene.control.Alert;

import model.Purchase;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.nio.file.StandardOpenOption.*;

public class DraftDAOpurchases implements DAOPurchases{
    private Alert alert = new Alert(Alert.AlertType.INFORMATION);
    private Path file = Paths.get(ConfigProperties.getInstance().getProperty("Filepurchases"));
    @Override
    public Purchase get(int id) {
        return null;
    }

    public List<Purchase> getByItem(int id){
        List<Purchase> listPurchases = getAll().stream().filter(purchase -> purchase.getIditem() == id).collect(Collectors.toList());
        return listPurchases;
    }

    @Override
    public List<Purchase> getAll() {
        List<Purchase> lp = new ArrayList<>();

        try {
            BufferedReader reader = Files.newBufferedReader(file);
            String line ;
            while ((line = reader.readLine())  != null){
                Purchase p = new Purchase(line);
                lp.add(p);
            }
            reader.close();

        }catch (IOException e){
            alert.setContentText("error,when read the purchase file");
            alert.showAndWait();
        }
        return lp;
    }

    @Override
    public void save(Purchase t) {
        OpenOption[] options = new OpenOption[2];
        options[0] = APPEND;
        options[1] = WRITE;
        try(BufferedWriter writer = Files.newBufferedWriter(file,options)) {
            writer.write(t.toStringTexto(),0,t.toStringTexto().length());
            writer.newLine();
        }catch (IOException e){
            alert.setContentText("error, when save the purchases");
            alert.showAndWait();
        }
    }

    @Override
    public void update(Purchase t) {

    }


    @Override
    public void delete(Purchase t) {

        OpenOption[] options = new OpenOption[2];
        options[0] = WRITE;
        options[1] = TRUNCATE_EXISTING;
        List<Purchase> lp = getAll();
        lp.remove(t);
        try(BufferedWriter writer = Files.newBufferedWriter(file,options);
            BufferedWriter bw = new BufferedWriter(writer)) {
            for(int i = 0; i < lp.size();i++){
                bw.write(lp.get(i).toStringTexto());
                bw.newLine();
            }

        }catch (IOException e){
            alert.setContentText("error, when delete the purchase");
            alert.showAndWait();
        }

    }


}
