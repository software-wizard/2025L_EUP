package pl.psi.map.resources;
import lombok.Value;

@Value
public class Resources {

    int gold; //no need for private cause lombok Value marks it as private
    int wood;
    int ore;
    int mercury;
    int sulphur;
    int crystal;
    int gems;

    public Resources change(Resources value) { //can be used for both addition and substraction
        return new Resources(
                gold + value.gold,
                wood + value.wood,
                ore + value.ore,
                mercury + value.mercury,
                sulphur + value.sulphur,
                crystal + value.crystal,
                gems + value.gems
        );
    }

    public boolean enoughToPay(Resources cost) { //check if the cost in resources isn't bigger than the supply
        return this.gold >= cost.gold &&
                this.wood >= cost.wood &&
                this.ore >= cost.ore &&
                this.mercury >= cost.mercury &&
                this.sulphur >= cost.sulphur &&
                this.crystal >= cost.crystal &&
                this.gems >= cost.gems;
    }

    public Resources pay(){ //to pay, need to invoke the method .pay on the cost object
        return new Resources(
                -gold,
                -wood,
                -ore,
                -mercury,
                -sulphur,
                -crystal,
                -gems
        );
    }

    @Override
    public String toString() {
        return String.format("[Gold: %d, Wood: %d, Ore: %d, Mercury: %d, Sulphur: %d, Crystal: %d, Gems: %d]",
                gold, wood, ore, mercury, sulphur, crystal, gems
        );
    }

    public boolean enoughToPayGold(int cost) {
        return gold >= cost;
    }
}
