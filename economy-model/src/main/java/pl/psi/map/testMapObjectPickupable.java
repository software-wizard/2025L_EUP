<<<<<<<< HEAD:economy-model/src/main/java/pl/psi/map/testMapObjectPickupable.java
package pl.psi.map;
========
package pl.psi.map.buildings.bank;
>>>>>>>> develop:economy-model/src/main/java/pl/psi/map/buildings/bank/Bank.java

import pl.psi.Point;
import pl.psi.hero.EconomyHero;
<<<<<<<< HEAD:economy-model/src/main/java/pl/psi/map/testMapObjectPickupable.java
import pl.psi.map.buildings.enterAction.EnterAction;
========
import pl.psi.map.buildings.BuildingIf;
import pl.psi.map.buildings.EnterAction;
import pl.psi.map.buildings.EnterActionType;
import pl.psi.map.resources.Resources;

public class Bank implements BuildingIf {
    final Resources prize;

    public Bank(Resources prize) {
        this.prize = prize;
    }
>>>>>>>> develop:economy-model/src/main/java/pl/psi/map/buildings/bank/Bank.java

public class testMapObjectPickupable implements MapObjectIf{
    @Override
    public String getPath() {
        return "/objects/bank.png";
    }

    @Override
    public void endOfTurn() {

    }

    @Override
    public void enter(EconomyHero hero) {

    }

    @Override
    public void generateResource() {

    }

    @Override
    public void interact(EconomyHero hero, Point point) {

    }

    @Override
    public typeOfObject getTypeOfObject() {
        return typeOfObject.PICKUPABLE;
    }

    @Override
    public EconomyHero getOwner() {
        return null;
    }

    @Override
    public EnterAction onEnter() {
        return null;
    }

    @Override
    public EnterAction secondInteraction() {
        return null;
    }
}
