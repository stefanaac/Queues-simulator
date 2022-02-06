package datamodels;

import java.util.Comparator;


//metoda care o folosesc pentru sortarea clientilor din lista de clienti in functie de timpul sosirii la coada
public class SortingByArrival implements Comparator<Client> {

    @Override
    public int compare(Client o1, Client o2) {
        return o1.gettArrival()-o2.gettArrival();
    }
}
