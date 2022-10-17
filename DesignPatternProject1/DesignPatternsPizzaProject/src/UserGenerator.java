//Factory CLASS

interface IUser {
    String getName();
    String getSurname();
    int getDiscount();
    int getRegion();
    void userInfo();
}

class NormalUser implements IUser{
    String name, surname;
    int discount, region;

    public NormalUser(String name, String surname, int discount, int region) {
        this.name = name;
        this.surname = surname;
        this.discount = discount;
        this.region = region;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getSurname() {
        return surname;
    }

    @Override
    public int getDiscount() {
        return discount;
    }

    public int getRegion() {
        return region;
    }

    @Override
    public void userInfo() {
        System.out.println("Name: " + name + "\nSurname: " + surname + "\nBudget: " + discount + "VIP: FALSE");
    }
}

class VIPUser implements IUser{
    String name, surname;
    int discount, region;

    public VIPUser(String name, String surname, int discount, int region) {
        this.name = name;
        this.surname = surname;
        this.discount = discount;
        this.region = region;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getSurname() {
        return surname;
    }

    @Override
    public int getDiscount() {
        return discount;
    }


    public int getRegion() {
        return region;
    }

    @Override
    public void userInfo() {
        System.out.println("Name: " + name + "\nSurname: " + surname + "\nBudget: " + discount + "VIP: TRUE");
    }
}

// Factory .....
public class UserGenerator {
    public static IUser createUser(String name, String surname,boolean isVIP, int region){
        IUser user;

        if(isVIP)
            user = new VIPUser(name,surname,10, region);
        else
            user = new NormalUser(name,surname,5, region);

        return user;
    }
}

