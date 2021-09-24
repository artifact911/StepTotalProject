package by.nurvazarov.generalnasa.model.product.scorpion;

import by.nurvazarov.generalnasa.model.order.OrderByUser;
import by.nurvazarov.generalnasa.model.product.Products;
import by.nurvazarov.generalnasa.model.product.enums.Danger;
import by.nurvazarov.generalnasa.model.product.enums.Toxic;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Scorpion implements Products {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pid;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false, columnDefinition = "varchar(40) default 'ANDROCTONUS_AUSTRALIS'")
    private ScorpionsEnum name;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false, columnDefinition = "varchar(20) default 'HIGH'")
    private Toxic toxic;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false, columnDefinition = "varchar(20) default 'SUPREME'")
    private Danger danger;

    @Column(columnDefinition = "INT default 0")
    private Integer quantity;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Lob
    private byte[] image;

    @Column(columnDefinition = "DOUBLE default 0.0")
    private Double price;

    @ManyToMany(mappedBy = "scorpions", fetch = FetchType.LAZY)
    private List<OrderByUser> orders = new ArrayList<>();


    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public ScorpionsEnum getName() {
        return name;
    }

    public void setName(ScorpionsEnum name) {
        this.name = name;
    }

    public Toxic getToxic() {
        return toxic;
    }

    public void setToxic(Toxic toxic) {
        this.toxic = toxic;
    }

    public Danger getDanger() {
        return danger;
    }

    public void setDanger(Danger danger) {
        this.danger = danger;
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
