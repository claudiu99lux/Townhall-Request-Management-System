package entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "request")
public class Request {
    @Id
    private String id;

    @OneToOne
    @JoinColumn(name="request_type")
    private RequestType type;

    @Column
    private int approved;

    @Column
    private int active;

    @Column
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;


    public Request(Address address, RequestType type){
        this.address = address;
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

    public RequestType getType() {
        return type;
    }

    public void setType(RequestType type) {
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
