package by.nurvazarov.generalnasa.model.product.scorpion;

public enum ScorpionsEnum {

    PANDINUS_IMPERATOR(1, "Императорский скорпион"),
    HADRUINS_ARISZONENSIS(2, "Пустынный волосатый скорпион"),
    ANDROCTONUS_CRASSICAUDA(3, "Толстохвостый скорпион"),
    ANDROCTONUS_AUSTRALIS(4, "Андроктонус Аустралис"),
    HETEROMETRUS_SPINIFER(5, "Гетерометрус Спинифер");


    private final int index;
    private final String rusName;


    ScorpionsEnum(int index, String rusName) {
        this.index = index;
        this.rusName = rusName;
    }

    public int getIndex() {
        return index;
    }

    public String getRusName() {
        return rusName;
    }
}
