package entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "requests")
public class Request {
    @Id
    private String id;

    @Column
    private int type;

    @Column
    private int approved;

    @Column
    private int active;

    @Column
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "owner_combo_id")
    private UserAddress owner;


    public Request(UserAddress owner, int type){
        this.owner = owner;
        this.type = type;
        this.approved = 0;
        this.active = 1;
        this.date = LocalDate.now();
    }

    public Request() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getApproved() {
        return approved;
    }

    public void setApproved(int approved) {
        this.approved = approved;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public UserAddress getOwner() {
        return owner;
    }

    public void setOwner(UserAddress owner) {
        this.owner = owner;
    }
}
