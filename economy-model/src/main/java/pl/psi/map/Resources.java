package pl.psi.map;
import lombok.Value;


public class Resources {

    @Value
    public static class Gold {
        int amount;
    }

    @Value
    public static class Wood {
        int amount;
    }

    @Value
    public static class Ore {
        int amount;
    }

//    public void addResources(Resources resources, Resources resourcesToAdd) {
//        resources.setGold(resources.getGold() + resourcesToAdd.getGold());
//        resources.setWood(resources.getWood() + resourcesToAdd.getWood());
//        resources.setOre(resources.getOre() + resourcesToAdd.getOre());
//    }
    //TODO trzeba dokończyć resources (napisanie klasy + gui) -> pamiętać o dodaniu kopalni 
}
