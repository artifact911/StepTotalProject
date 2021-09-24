package by.nurvazarov.generalnasa.model.product.equipment;

import by.nurvazarov.generalnasa.model.order.OrderByUser;
import by.nurvazarov.generalnasa.model.product.Products;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Equipment implements Products {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pid;

    @Column(nullable = false, columnDefinition = "varchar(40) default 'NoName'")
    private String name;

    @Column(columnDefinition = "INT default 0")
    private Integer quantity;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Lob
    private byte[] image;

    @Column(columnDefinition = "DOUBLE default 0.0")
    private Double price;

    @ManyToMany(mappedBy = "equipments", fetch = FetchType.LAZY)
    private List<OrderByUser> orders = new ArrayList<>();


    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
