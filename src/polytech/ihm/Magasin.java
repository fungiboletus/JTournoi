package stockm.ihm;

import java.util.ArrayList;

import stockm.exceptions.BadArgumentException;
import stockm.exceptions.BikeNotCompleteException;
import stockm.exceptions.FieldAlreadyFilledException;
import stockm.exceptions.InvalideXmlException;
import stockm.exceptions.OutOfStockException;
import stockm.stock.Stock;
import stockm.types.Bike;
import stockm.types.Part;
import stockm.xml.SalesXML;
import stockm.xml.StockageXML;

public class Magasin {
    public static void main(String[] args) throws BadArgumentException {
        Stock.init();
        StockManagementTextInterface manager = new StockManagementTextInterface();
        manager.run();
        try {
            SalesXML.save();
        } catch (InvalideXmlException e) {
            System.out.println("Erreur lors de la sauvegarde des ventes.");
        }
        try {
            StockageXML.save();
        } catch (InvalideXmlException e) {
            System.out.println("Erreur lors de la sauvegarde du stock.");
        }
    }
}
