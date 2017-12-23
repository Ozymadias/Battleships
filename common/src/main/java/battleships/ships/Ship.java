package battleships.ships;

import battleships.communication.Messagable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Ship implements Messagable{
    private List<Mast> masts;

    public Ship() {}

    public static Ship viaList(List<Integer> positions){
        List<Mast> masts = new ArrayList<>();
        positions.stream().forEach(p -> masts.add(new Mast(p)));
        return new Ship(masts);
    }

    public static Ship createShip(Integer... positions) {
        List<Mast> masts=  new ArrayList<>();
        for(Integer i: positions) {
            masts.add(new Mast(i));
        }
        return new Ship(masts);
    }

    private Ship(List<Mast> shipMasts) {
        this.masts = shipMasts;
    }

    public void setMasts(List<Mast> masts) {
        this.masts = masts;
    }

    public List<Mast> getMasts() {
        return masts;
    }

    private Integer getHitPointsLeft() {
        return (int) masts.stream().filter(Mast::isAlive).count();
    }

    public boolean isSunk() {
        return getHitPointsLeft() == 0;
    }

    public void killMast(Integer position) {
        masts.stream()
                .filter((p) -> p.getPosition().equals(position))
                .forEach(Mast::kill);
    }

    public String toString(){
        return masts.stream()
                .map(Mast::toString)
                .collect(Collectors.joining(" ,"));
    }
}
