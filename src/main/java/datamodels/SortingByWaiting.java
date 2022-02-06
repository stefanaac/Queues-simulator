package datamodels;

import java.util.Comparator;

//o metoda care o folosesc pentru a sorta clientii din lista de clienti  in functie de timpul de servire
public class SortingByWaiting implements Comparator<Client> {

    @Override
    public int compare(Client o1, Client o2) {
        if(o1.gettArrival()==o2.gettArrival())
            return o1.gettService()-o2.gettService();
        else
            return 0;
    }
}
