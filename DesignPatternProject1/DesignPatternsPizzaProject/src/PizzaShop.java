import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import java.util.Scanner;

// Facade CLASS

public class PizzaShop {
    protected static HashMap<Integer, Restaurant> restaurants; // En büyük restoran mapimiz.
    protected static Distributor distributor;
    Kitchen kitchen;
    Chef chef;
    IUser user;
    TruckDriver truckDriver;

    PizzaShop(String[] args, IUser user) {
        restaurants = new HashMap<>();
        distributor = Distributor.getInstance(); // singleton oldugu için getInstance ile nesneye eriştim.. Distributorden 1 den fazla istemiyorum.
        this.chef = new Chef();
        this.user = user;
        this.truckDriver = new TruckDriver();
        this.kitchen = new Chef(); //Template Method Kullanımı için nesne oluşturduk.
        setThingsUp(args); // Aşağıdaki setThingsUp metodunu çağırıyorum ve ordada CSVnin içindeki değerleri maplere gömüyor.
    }

    public void orderPizza() {
        System.out.println("############ Open Pizza Restaurants ############\n");

        int i = 1;
        for(Map.Entry<Integer,Restaurant> restaurantEntry : restaurants.entrySet()){
            //Bütün restoranları göremizi sağlayan ve sout bastıran map yapısı.
            System.out.println(i + ". " + restaurantEntry.getValue().getName());
            i++;
        }

        System.out.print("\nChoice: ");
        Scanner scanner = new Scanner(System.in);
        int restaurantID = scanner.nextInt();

        Restaurant restaurant = restaurants.get(restaurantID); // İçine hangi key olcağını belirttik --> value -> restoran

        System.out.println();
        if (restaurant.isRestaurantOpen()) // Facade kullanımı
            chef.template(); // Template Method Kullandık

        for (Map.Entry<String,Integer> items : restaurant.getInventory().entrySet()){
            //pizza için malzeme kullanımı
            restaurant.getInventory().put(items.getKey(),items.getValue()-10);
        }

        //Pizza stoklarının güncel durumunu görüntüledik.
        System.out.println();
        System.out.println("New Inventory: ");
        for(Map.Entry<String,Integer> inv : restaurant.getInventory().entrySet()){
            System.out.println("Item: " + inv.getKey() + "\tCount: " + inv.getValue());
        }


           //facade kullanımı
        //Siparişten sonra stok kontrolü yapılıyor.
        if(restaurant.needSupply()){ //71 kod satırı bakıyor 20 nin altına malzeme düşüşü olduysa alt kodlar çalışıyor.
            System.out.println();
            if(distributor.isDistributorAvailable()){
                if(truckDriver.isThereAnyDriver()){
                    distributor.sendSupply(restaurant);
                }
            }
        }

        System.out.println();
        if(user.getDiscount() == 10)
            System.out.println("Hey + " + user.getName() + " " + user.getSurname() + "! You did 10% less payment as you are \"VIP\"");
        if(user.getDiscount() == 5)
            System.out.println("Hey + " + user.getName() + " " + user.getSurname() + "! You did 5% less payment as you are \"Normal User\"");

        if(user.getRegion() == 1){
            System.out.println("You cant pay using TRY. Exchanging your money to USD!");
            TRYMoney payment = new exchangeTRYtoUSD();
            int paymentValue = payment.usingTRY();
            Receipt receipt = new Receipt();
            receipt.generateReceipt(paymentValue);
        }
        else{
            DollarMoney dollarMoney = new DollarMoney();
            int paymentValue = dollarMoney.usingUSD();
            Receipt receipt = new Receipt();
            receipt.generateReceipt(paymentValue);
        }

        System.out.println("-------------------------------------------------------------------");
    }

    // CSVnin içindeki değerleri maplere gömüyor.
    private void setThingsUp(String[] args) {
        File restaurantFile = new File(args[0]);
        try {
            Scanner scanner = new Scanner(restaurantFile);
            scanner.nextLine();
            String[] items = {"Sausage", "Corn", "Pepper", "Mushroom", "Cheese"};

            while (scanner.hasNextLine()) {
                String readLine = scanner.nextLine();
                String[] readData = readLine.split(";");

                Restaurant restaurant = new Restaurant(Integer.parseInt(readData[1]), readData[0]);
                HashMap<String, Integer> inventory = new HashMap<>();

                for (int indexReadData = 2, indexItem = 0; indexReadData <= 6; indexReadData++, indexItem++) {
                    inventory.put(items[indexItem], Integer.parseInt(readData[indexReadData]));
                }

                restaurant.setInventory(inventory);
                PizzaShop.restaurants.put(restaurant.getID(), restaurant);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}

