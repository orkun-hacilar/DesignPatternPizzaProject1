import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Restaurant {
    private int ID;
    private String name;
    private HashMap<String,Integer> inventory;

    public Restaurant(){}

    public Restaurant(int ID, String name) {
        this.ID = ID;
        this.name = name;
        this.inventory = null;
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public HashMap<String, Integer> getInventory() {
        return inventory;
    }

    public void setInventory(HashMap<String, Integer> inventory) {
        this.inventory = inventory;
    }

    public boolean isRestaurantOpen(){
        System.out.println("Checking if restaurant is open: True\n");
        return true;
    }

    public boolean needSupply(){
        for (Map.Entry<String,Integer> itemEntry : inventory.entrySet()){
            if(itemEntry.getValue() <= 20){
                return true;
            }
        }
        return false;
    }
}

//Singleton CLASS
class Distributor {
    private static Distributor distributor;
    private int sausageCount;
    private int cornCount;
    private int pepperCount;
    private int mushroomCount;
    private int cheeseCount;

    private Distributor(){
        this.sausageCount = 1000;
        this.cheeseCount = 1000;
        this.cornCount = 1000;
        this.mushroomCount = 1000;
        this.pepperCount = 1000;
    }

    public static Distributor getInstance(){
        if(distributor == null)
            distributor = new Distributor();
        return distributor;
    }

    public boolean isDistributorAvailable(){
        System.out.println("Checking if distributor is available: True\n");
        return true;
    }

    public void sendSupply(Restaurant restaurant){
        HashMap<Integer, Restaurant> supplyNeededRestaurants = new HashMap<>();
        supplyNeededRestaurants.put(restaurant.getID(),restaurant);

        TruckDriver truckDriver = new TruckDriver(supplyNeededRestaurants);
        truckDriver.startTruck();

    }
}

class TruckDriver {
    private static final String[] county = {"Örnekköy","Buca","Alaybey","Bostanlı","Balcova","Karsiyaka","Taksim","Bornova"};
    public static HashMap<Integer, Restaurant> supplyNeededRestaurants;

    TruckDriver(){}

    TruckDriver(HashMap<Integer, Restaurant> supplyNeededRestaurants){
        TruckDriver.supplyNeededRestaurants = supplyNeededRestaurants;
    }

    void startTruck(){
        GEN();
    }

    void GEN(){
        String path1 = county[0] + "->" + county[5] + "->" + county[2];
        String path2 = county[2] + "->" + county[4] + "->" + county[1];
        String path3 = county[5] + "->" + county[3] + "->" + county[6];
        String path4 = county[7] + "->" + county[6] + "->" + county[4];

        String[] path = new String[4];
        path[0] = path1;
        path[1] = path2;
        path[2] = path3;
        path[3] = path4;

        TSP(path);
    }

    void TSP(String[] path){
        Random random = new Random();

        for(Map.Entry<Integer,Restaurant> restaurant : supplyNeededRestaurants.entrySet()){
            String finalPath = path[random.nextInt(0,4)];
            System.out.print("\nPath is: \n" + finalPath);
            System.out.println("->" + restaurant.getValue().getName());
        }

        for(Map.Entry<Integer,Restaurant> restaurantEntry : supplyNeededRestaurants.entrySet()){
            HashMap<String, Integer> inventory = restaurantEntry.getValue().getInventory();
            for(Map.Entry<String, Integer> item : inventory.entrySet()){
                item.setValue(item.getValue()+100);
            }
            //restaurantEntry.getValue().setInventory(inventory);
            PizzaShop.restaurants.put(restaurantEntry.getKey(),restaurantEntry.getValue());
        }

        supplyNeededRestaurants.clear();
    }

    public boolean isThereAnyDriver(){
        System.out.println("Checking if truck driver is available: True\n");
        return true;
    }
}
