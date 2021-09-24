package by.nurvazarov.generalnasa.model.product.spider;

import by.nurvazarov.generalnasa.model.order.OrderByUser;

import by.nurvazarov.generalnasa.model.product.Products;
import by.nurvazarov.generalnasa.model.product.enums.Danger;
import by.nurvazarov.generalnasa.model.product.enums.Difficulty;
import by.nurvazarov.generalnasa.model.product.enums.Sex;
import by.nurvazarov.generalnasa.model.product.enums.Toxic;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Spider implements Products {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pid;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false, columnDefinition = "varchar(40) default 'ACANTHOSCURRIA_GENICULATA'")
    private SpidersEnum name;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false, columnDefinition = "varchar(15) default 'GROUND'")
    private Type type;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false, columnDefinition = "varchar(15) default 'LOW'")
    private Toxic toxic;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false, columnDefinition = "varchar(15) default 'LOW'")
    private Difficulty difficulty;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false, columnDefinition = "varchar(15) default 'MIDDLE'")
    private Danger danger;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false, columnDefinition = "varchar(15) default 'UNDEFINED'")
    private Sex sex;

    @Column(columnDefinition = "INT default 0")
    private Integer quantity;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Lob
    private byte[] image;

    @Column(columnDefinition = "DOUBLE default 0.0")
    private Double price;

    @ManyToMany(mappedBy = "spiders", fetch = FetchType.LAZY)
    private List<OrderByUser> orders = new ArrayList<>();


    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public SpidersEnum getName() {
        return name;
    }

    public void setName(SpidersEnum name) {
        this.name = name;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Toxic getToxic() {
        return toxic;
    }

    public void setToxic(Toxic toxic) {
        this.toxic = toxic;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
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
