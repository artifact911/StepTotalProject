package by.nurvazarov.generalnasa.model.product.snake;

import by.nurvazarov.generalnasa.model.order.OrderByUser;
import by.nurvazarov.generalnasa.model.product.enums.Danger;
import by.nurvazarov.generalnasa.model.product.enums.Sex;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Snake {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pid;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false, columnDefinition = "varchar(40) default 'BCI'")
    private SnakesEnum name;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false, columnDefinition = "varchar(20) default 'LOW'")
    private Danger danger;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false, columnDefinition = "varchar(20) default 'UNDEFINED'")
    private Sex sex;

    @Column(columnDefinition = "INT default 0")
    private Integer quantity;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Lob
    private byte[] image;

    @Column(columnDefinition = "DOUBLE default 0.0")
    private Double price;

    @ManyToMany(mappedBy = "snakes", fetch = FetchType.LAZY)
    private List<OrderByUser> orders = new ArrayList<>();


    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public SnakesEnum getName() {
        return name;
    }

    public void setName(SnakesEnum name) {
        this.name = name;
    }

    public Danger getDanger() {
        return danger;
    }

    public void setDanger(Danger danger) {
        this.danger = danger;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public List<OrderByUser> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderByUser> orders) {
        this.orders = orders;
    }
}
