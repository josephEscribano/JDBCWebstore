/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import dao.DAOPurchases;
import dao.DraftDAOpurchases;
import model.Purchase;

/**
 *
 * @author dam2
 */
public class PurchasesServices {

    public List<Purchase> getAllPurchases() {
        DAOPurchases dp = new DraftDAOpurchases();
        return dp.getAll();
    }

    public ArrayList<Purchase> searchByDate(String date) {
        ArrayList<Purchase> purch =  null;
        return purch;
    }

    public List<Purchase> getPurchasesByClientId(int id) {
        List<Purchase> purch =  null;

        return purch;
    }
    public List<Purchase> getPurchasesByItemId(int id) {
        DraftDAOpurchases dp = new DraftDAOpurchases();
        return dp.getByItem(id);
    }

    public void deletePurchase(Purchase purchase) {
        DAOPurchases dp = new DraftDAOpurchases();
        dp.delete(purchase);


     }

    public void addPurchase(int customerId, int itemId, LocalDate date) {
        Purchase newPurchase = new Purchase(customerId,itemId,date);
        DAOPurchases dp = new DraftDAOpurchases();
        List<Purchase> lp = dp.getAll();
        newPurchase.setIdPurchase(lp.get(lp.size() -1).getIdPurchase() + 1);
        dp.save(newPurchase);

    }

}
