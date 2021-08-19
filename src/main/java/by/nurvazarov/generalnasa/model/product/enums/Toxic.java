package by.nurvazarov.generalnasa.model.product.enums;

public enum Toxic {
    LOW(1, "Низкая"),
    PRE_MIDDLE(2, "Ниже среднего"),
    MIDDLE(3, "Средняя"),
    PRE_HIGH(4, "Выше среднего"),
    HIGH(5, "Высокая");

    private final int index;
    private final String rusText;

    Toxic(int index, String rusText) {
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
