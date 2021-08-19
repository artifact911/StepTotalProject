package by.nurvazarov.generalnasa.model.product.lizard;

public enum LizardsEnum {

    CHAMAELEONIDAE(1, "Хамелеон"),

    POGONA_BARBATA(2, "Бородатая Агама"),

    EUBLEPHARIS(3, "Эублфар"),

    SCINCIDAE(4, "Сцинк");

    private final int index;
    private final String rusName;

    LizardsEnum(int index, String rusName) {
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
