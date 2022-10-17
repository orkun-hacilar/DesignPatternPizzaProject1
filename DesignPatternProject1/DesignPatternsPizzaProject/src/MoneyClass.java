
// Adaptern Class

interface TRYMoney {
    int usingTRY();
}

//This is the "Adaptee" class.
class DollarMoney {
    public int usingUSD() {
        System.out.println("Paying 20 USD!");
        return 20;
    }
}

//This is the "Adapter" class.
class exchangeTRYtoUSD extends DollarMoney implements TRYMoney {
    public int usingTRY() {
        int payment = usingUSD();
        return payment;
    }
}



//Utility Class.
class Receipt {
    public void generateReceipt(int paymentValue) {
        System.out.println("You paid: " + paymentValue + "$");
    }
}






