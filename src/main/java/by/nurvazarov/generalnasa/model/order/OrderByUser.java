package by.nurvazarov.generalnasa.model.order;

import by.nurvazarov.generalnasa.model.product.equipment.Equipment;
import by.nurvazarov.generalnasa.model.product.insect.Insect;
import by.nurvazarov.generalnasa.model.product.lizard.Lizard;
import by.nurvazarov.generalnasa.model.product.scorpion.Scorpion;
import by.nurvazarov.generalnasa.model.product.snake.Snake;
import by.nurvazarov.generalnasa.model.product.spider.Spider;
import by.nurvazarov.generalnasa.model.user.User;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class OrderByUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "pid", nullable = false)
    private User user;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false, columnDefinition = "varchar(20) default 'IS_OPEN'")
    private OrderStatus orderStatus;

    @Column(length = 100)
    private String comment;

    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createDate;

    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date lastUpdateDate;

    @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinTable(name = "orders_spiders")
    private List<Spider> spiders = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinTable(name = "orders_snakes")
    private List<Snake> snakes = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinTable(name = "orders_scorpions")
    private List<Scorpion> scorpions = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinTable(name = "orders_lizards")
    private List<Lizard> lizards = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinTable(name = "orders_insects")
    private List<Insect> insects = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinTable(name = "orders_equipments")
    private List<Equipment> equipments = new ArrayList<>();

    @Transient
    public String dateHelper(String date) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(date);
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public List<Spider> getSpiders() {
        return spiders;
    }

    public void setSpiders(List<Spider> spiders) {
        this.spiders = spiders;
    }

    public List<Snake> getSnakes() {
        return snakes;
    }

    public void setSnakes(List<Snake> snakes) {
        this.snakes = snakes;
    }

    public List<Scorpion> getScorpions() {
        return scorpions;
    }

    public void setScorpions(List<Scorpion> scorpions) {
        this.scorpions = scorpions;
    }

    public List<Lizard> getLizards() {
        return lizards;
    }

    public void setLizards(List<Lizard> lizards) {
        this.lizards = lizards;
    }

    public List<Insect> getInsects() {
        return insects;
    }

    public void setInsects(List<Insect> insects) {
        this.insects = insects;
    }

    public List<Equipment> getEquipments() {
        return equipments;
    }

    public void setEquipments(List<Equipment> equipments) {
        this.equipments = equipments;
    }

}
