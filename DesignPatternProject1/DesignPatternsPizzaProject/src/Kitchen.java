
//Template Method..

abstract class Kitchen {

    abstract void addSausage();

    abstract void addCorn();

    abstract void addPepper();

    abstract void addMushroom();

    abstract void addCheese();

    abstract void addDoubleSausage();

    abstract void addDoubleCorn();

    abstract void addDoublePepper();

    abstract void addDoubleMushroom();

    abstract void addDoubleCheese();


    boolean doYouWantDoubleSausage() {
        return false;
    }

    boolean doYouWantDoubleCorn() {
        return false;
    }

    boolean doYouWantDoublePepper() {
        return false;
    }

    boolean doYouWantDoubleMushroom() {return false;}

    boolean doYouWantDoubleCheese() {
        return false;
    }

    final void template() {
        System.out.println("Chef is preparing the order!\n");
        addSausage();
        if(doYouWantDoubleSausage()){
            addDoubleSausage();
        }
        addCorn();
        if(doYouWantDoubleCorn()){
            addDoubleCorn();
        }
        addPepper();
        if(doYouWantDoublePepper()){
            addDoublePepper();
        }
        addMushroom();
        if(doYouWantDoubleMushroom()){
            addDoubleMushroom();
        }
        addCheese();
        if(doYouWantDoubleCheese()){
            addDoubleCheese();
        }
    }
}

class Chef extends Kitchen {

    @Override
    void addSausage() {
        System.out.println("Your sausages have been added to the pizza.");
    }

    @Override
    void addCorn() {
        System.out.println("Your Corn have been added to the pizza.");
    }

    @Override
    void addPepper() {
        System.out.println("Your Pepper have been added to the pizza.");
    }

    @Override
    void addMushroom() {
        System.out.println("Your Mushroom have been added to the pizza.");
    }

    @Override
    void addCheese() {
        System.out.println("Your Cheese have been added to the pizza.");
    }

    @Override
    void addDoubleSausage() {
        System.out.println("Your DoubleSausage have been added to the pizza.");
    }

    @Override
    void addDoubleCorn() {
        System.out.println("Your DoubleCorn have been added to the pizza.");
    }

    @Override
    void addDoublePepper() {
        System.out.println("Your DoublePepper have been added to the pizza.");
    }

    @Override
    void addDoubleMushroom() {
        System.out.println("Your DoubleMushroom have been added to the pizza.");
    }

    @Override
    void addDoubleCheese() {
        System.out.println("Your DoubleCheese have been added to the pizza.");
    }

    boolean doYouWantDoubleSausage() {
        return true;
    }

    boolean doYouWantDoubleCorn() {
        return false;
    }

    boolean doYouWantDoublePepper() {
        return true;
    }

    boolean doYouWantDoubleMushroom() {
        return false;
    }

    boolean doYouWantDoubleCheese() {
        return true;
    }
}
