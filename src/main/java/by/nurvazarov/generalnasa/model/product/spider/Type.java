package by.nurvazarov.generalnasa.model.product.spider;

public enum Type {

    GROUND(1, "Наземный"),
    TREE(2, "Древесный"),
    BURROW(3, "Норный");

    private final int index;
    private final String rusText;

    Type(int index, String rusText) {
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
