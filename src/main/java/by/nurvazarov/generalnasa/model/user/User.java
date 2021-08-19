package by.nurvazarov.generalnasa.model.user;

import by.nurvazarov.generalnasa.model.order.OrderByUser;
import org.apache.logging.log4j.util.Strings;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pid;

    @Column(nullable = false, length = 100, unique = true)
    private String login;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, length = 100)
    private String firstName;

    @Column(length = 100)
    private String middleName;

    @Column(nullable = false, length = 100)
    private String lastName;

    @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles")
    private List<Role> roles = new ArrayList<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<OrderByUser> orderByUserList;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false, columnDefinition = "varchar(20) default 'ACTIVE'")
    private Status status;

    @Transient
    public String getFio() {
        String first = "";
        String middle = "";
        String last = "";

        if (Strings.isNotEmpty(getFirstName())) {
            first = getFirstName().toUpperCase().charAt(0) + ".";
        }
        if (Strings.isNotEmpty(getMiddleName())) {
            middle = getMiddleName().toUpperCase().charAt(0) + ".";
        }
        if (Strings.isNotEmpty(getLastName())) {
            last = getLastName();
        }
        return last + " " + first + middle;
    }

    @Transient
    public String getRolesStr() {
        return roles.stream().map(Role::getName).collect(Collectors.joining(","));
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public List<OrderByUser> getOrderByUserList() {
        return orderByUserList;
    }

    public void setOrderByUserList(List<OrderByUser> orderByUserList) {
        this.orderByUserList = orderByUserList;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return pid.equals(user.pid) &&
                login.equals(user.login);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pid, login);
    }
}
