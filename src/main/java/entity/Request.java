package entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "request")
public class Request {
    @Id
    private String id;

    @ManyToOne
    @JoinColumn(name="request_type")
    private RequestType type;

    @Column
    private int approved; //0 = pending, 1=approved, 2=declined

    @Column
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address owner_address;

    @Column
    private String content;


    public Request(Address address, RequestType type){
        this.id = UUID.randomUUID().toString();
        this.owner_address = address;
        this.date = LocalDate.now();
        this.type = type;
        this.approved = 0;
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

    public Address getOwner_address() {
        return owner_address;
    }

    public void setOwner_address(Address address) {
        this.owner_address = address;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
