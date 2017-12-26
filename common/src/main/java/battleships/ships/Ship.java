package battleships.ships;

import battleships.communication.Messagable;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class Ship implements Messagable {
    private final List<Mast> masts;

    public static Ship viaList(List<Integer> positions) {
        List<Mast> masts = new ArrayList<>();
        positions.forEach(p -> masts.add(new Mast(p)));
        return new Ship(masts);
    }

    public static Ship createShip(Integer... positions) {
        List<Mast> masts = new ArrayList<>();
        for (Integer i : positions) {
            masts.add(new Mast(i));
        }
        return new Ship(masts);
    }

    @JsonCreator
    private Ship(
            @JsonProperty("shipMasts") List<Mast> shipMasts) {
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
