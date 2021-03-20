package entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "addresses")
@DiscriminatorColumn(
        discriminatorType = DiscriminatorType.INTEGER,
        name = "buildingType")
public abstract class Address {
    @Id
    private String id;

    @Column
    private int buildingType; //1 = house, 2 = apartment building

    @Column
    private String street;

    @Column
    private String number; //String because number can be 102A

    @OneToMany(mappedBy = "addresses")
    private List<UserAddress> userAddresses = new ArrayList<>();

    public Address(){}

    public Address(int buildingType, String street, String number){
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

}
