package by.nurvazarov.generalnasa.model.order;

public enum OrderStatus {
    IS_OPEN(1, "Открыт"),
    PAID_FOR(2, "К оплате"),
    IS_CLOSED(3, "Закрыт"),
    CANCELED(4, "Отменён");

    private final int index;
    private final String rusText;

    OrderStatus(int index, String rusText) {
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
