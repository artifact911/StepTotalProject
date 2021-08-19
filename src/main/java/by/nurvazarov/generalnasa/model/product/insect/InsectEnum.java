package by.nurvazarov.generalnasa.model.product.insect;

public enum InsectEnum {

    EUBLABERUS_DISTANTI(1, "Таракан шеститочечный"),

    SHELFORDELLA_TARTARA(2, "Таракан туркменский"),

    ARHIMANDRITA_TESSELATA(3, "Архимандрит"),

    NAUPHOETA_CINEREA(4, "Таракан мраморный"),

    LUCIHORMETICA_SUBCINCTA(5, "Автомобильчик");

    private final int index;
    private final String rusName;

    InsectEnum(int index, String rusName) {
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
