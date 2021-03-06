package by.it.akhmelev.jd02_01.classroom;

public class Buyer implements Runnable,IBuyer,IBacket {

    private int number;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Buyer(int number) {
        this.number=number;
        this.setName("Buyer №"+number);
    }

    @Override
    public void run() {
        enterToMarket();
        takeBacket();
        chooseGoods();
        goToOut();
    }

    @Override
    public void enterToMarket() {
        System.out.println(this+" enter to Market");
    }

    @Override
    public void chooseGoods() {
        for (int i = 1; i < Helper.rnd(1,4); i++) {
            Helper.sleep(Helper.rnd(100,200));
            String goodName=Goods.random();
            System.out.println(this+" choosed good: "+goodName);
            putGoodsToBacket();
        }
    }

    @Override
    public void goToOut() {
        System.out.println(this+" go to out from Market");
    }

    @Override
    public String toString() {
        return "Buyer №" + number;
        //return this.getName();
    }

    @Override
    public void takeBacket() {
        Helper.sleep(Helper.rnd(100,200));
        System.out.println(this+" take backet");
    }

    @Override
    public void putGoodsToBacket() {
        Helper.sleep(Helper.rnd(100,200));
        System.out.println(this+" put goods to backet");
    }
}
