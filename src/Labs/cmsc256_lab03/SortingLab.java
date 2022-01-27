//package Labs.cmsc256_lab03;
package cmsc256;

import bridges.connect.Bridges;
import bridges.connect.DataSource;
import bridges.data_src_dependent.ActorMovieIMDB;
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


    }
}
