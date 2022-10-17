import java.util.Scanner;

public class Run {
    public static void main(String[] args) {

        IUser user = menu(); //menu() Factory classdan dönen nesneyi alıyor ve kullanmamız için bize veriyor.

        PizzaShop pizzaShop = new PizzaShop(args,user);  //Facade classını kullanmak için.

        while(true){
            pizzaShop.orderPizza(); //Facade içindeki bir metod ile birden fazla class içindeki işlevsellikler ile çalışıldı.
        }
    }

    public static IUser menu(){
        System.out.println("*************************************************************");
        System.out.println("\t\t\t\tWELCOME TO PIZZA ORDERING APP");
        System.out.print("\tSelect User Type (1-VIP, 2-Normal): ");
        Scanner scanner = new Scanner(System.in);
        int selection = scanner.nextInt();

        scanner.nextLine();
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Surname: ");
        String surname = scanner.nextLine();
        System.out.print("Region (1-TR/2-US): ");
        int region = scanner.nextInt();

        IUser user;
        if(selection == 1){
            user = UserGenerator.createUser(name,surname,true, region); // userfactory kullanarak bir nesne yaratımında new anahtarını kullanmaktan kaçındık. Factory sayesinde nesnelerimizi yarattık.
        }
        else
            user = UserGenerator.createUser(name,surname,false, region); // userfactory kullanarak bir nesne yaratımında new anahtarını kullanmaktan kaçındık. Factory sayesinde nesnelerimizi yarattık.

        return user; // main() için User döndürüyoruz çünkü uygulamamızın içinde kullanmak için bu nesneye ihtiyacımız var. factory.
    }
}
