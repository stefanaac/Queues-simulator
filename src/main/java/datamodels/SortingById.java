package datamodels;

import java.util.Comparator;

//clasa care implementeaza interfata Comparator
//o folosesc pentru sortarea clientilor in functie de Id
public class SortingById implements Comparator<Client> {

    @Override
    public int compare(Client o1, Client o2) {
        return o1.getID()-o2.getID();
    }
}
