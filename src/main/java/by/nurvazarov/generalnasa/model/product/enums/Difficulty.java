package by.nurvazarov.generalnasa.model.product.enums;

public enum Difficulty {

    LOW(1, "Низкая"),
    MIDDLE(2, "Средняя"),
    HIGH(3, "Высокая");

    private final int index;
    private final String rusText;

    Difficulty(int index, String rusText) {
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
