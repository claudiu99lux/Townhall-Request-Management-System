package entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "address")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
        name = "buildingType",
        discriminatorType = DiscriminatorType.INTEGER)
public abstract class Address {
    @Id
    private String id;

    @Column(name="buildingType", insertable = false, updatable = false)
    private int buildingType; //1 = house, 2 = apartment building

    @Column
    private String street;

    @Column
    private String number; //String because number can be 102A

    @ManyToOne
    @JoinColumn(name="owner_id")
    private User user;

    @OneToMany(mappedBy = "owner_address")
    private List<Request> requests;

    public Address(){}

    public Address(int buildingType, String street, String number){
        this.id = UUID.randomUUID().toString();
        this.buildingType = buildingType;
        this.street = street;
        this.number = number;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getBuildingType() {
        return buildingType;
    }

    public void setBuildingType(int buildingType) {
        this.buildingType = buildingType;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Request> getRequests() {
        return requests;
    }

    public void setRequests(List<Request> requests) {
        this.requests = requests;
    }

    @Override
    public String toString() {
        String type;
        if(buildingType==1)
            type = "House";
        else
            type = "Apartment";
        return type + ": " + "str. " + street + ", nr. " + number;
    }
}
