package cmsc256;

import bridges.data_src_dependent.ActorMovieIMDB;

import java.util.Comparator;

public class ActorComparator implements Comparator<ActorMovieIMDB> {

    @Override
    public int compare(ActorMovieIMDB o1, ActorMovieIMDB o2) {
        return o1.getActor().compareTo(o2.getActor());
    }
}
