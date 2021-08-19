package by.nurvazarov.generalnasa.model.product.snake;

public enum SnakesEnum {

    BCI(1, "Императорский удав"),

    PYTHON_REGIUS(2, "Питон региус"),

    LAMPROPELTIS_CALIFORNIAE(3, "Калифорнийская королевская змея"),

    LAMPROPELTIS_CAMPBELLI(4, "Королевская змея"),

    PANTHEROPHIS_GUTTATUS(5, "Маисовый полоз"),

    ELAPHE_TAENIURUS(6, "Тонкохвостый полоз"),

    PYTHON_RETICULATUS(7, "Сетчатый питон"),

    PYTHON_MOLURUS(8, "Тигровый питон");

    private final int index;
    private final String rusName;

    SnakesEnum(int index, String rusName) {
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
