package cmsc256;

import bridges.connect.Bridges;
import bridges.connect.DataSource;
import bridges.data_src_dependent.ActorMovieIMDB;

import java.util.ArrayList;
import java.util.List;

public class SortingLab {
    public static void main(String[] args) {
        Bridges bridges = new Bridges(3,"wardpl2","133617755398");
        DataSource ds = bridges.getDataSource();

        List<ActorMovieIMDB> movieData = null;

        try {
            movieData = ds.getActorMovieIMDBData(Integer.MAX_VALUE);
        } catch (Exception e) {
            System.out.println("Unable to connect to Bridges.");
        }

        for (int i = 0; i < 5; i++) {
            ActorMovieIMDB entry = movieData.get(i);
            System.out.println("" + i + ".  " + entry.getActor() + " was in " + entry.getMovie());
        }

        ArrayList<ActorMovieIMDB> filteredMovieList = new ArrayList<>();

        int numActors = 0;
        for (ActorMovieIMDB A : movieData) {
            if (A.getMovie().equals("Being_John_Malkovich_(1999)")) {
                System.out.println(A.getActor());
                numActors++;
                filteredMovieList.add(A);
            }
        }
        System.out.println(numActors);

        filteredMovieList.sort(new ActorComparator());

        for (ActorMovieIMDB A : filteredMovieList) {
            System.out.println(A.getActor());
        }

    }
}
