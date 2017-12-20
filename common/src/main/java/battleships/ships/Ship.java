package battleships.ships;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Ship {
    private List<Mast> masts;

    public static Ship createShip(Integer... positions) {
        List<Mast> masts = new ArrayList<>();
        for (Integer i : positions) {
            masts.add(new Mast(i));
        }
        return new Ship(masts);
    }

    private Ship(List<Mast> shipMasts) {
        this.masts = shipMasts;
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

}
