package by.nurvazarov.generalnasa.model.product.lizard;

import by.nurvazarov.generalnasa.model.order.OrderByUser;
import by.nurvazarov.generalnasa.model.product.Products;
import by.nurvazarov.generalnasa.model.product.enums.Difficulty;
import by.nurvazarov.generalnasa.model.product.enums.Sex;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Lizard implements Products {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pid;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false, columnDefinition = "varchar(40) default 'CHAMAELEONIDAE'")
    private LizardsEnum name;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false, columnDefinition = "varchar(15) default 'LOW'")
    private Difficulty difficulty;

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

    @ManyToMany(mappedBy = "lizards", fetch = FetchType.LAZY)
    private List<OrderByUser> orders = new ArrayList<>();


    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public LizardsEnum getName() {
        return name;
    }

    public void setName(LizardsEnum name) {
        this.name = name;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
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
