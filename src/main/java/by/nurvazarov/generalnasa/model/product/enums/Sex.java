package by.nurvazarov.generalnasa.model.product.enums;

public enum Sex {

    MALE(1, "Самец"),
    FEMALE(2, "Самка"),
    UNDEFINED(3, "Н/О");

    private final int index;
    private final String rusText;

    Sex(int index, String rusText) {
        this.index = index;
        this.rusText = rusText;
    }

    public int getIndex() {
        return index;
    }

    public String getRusText() {
        return rusText;
    }
}
