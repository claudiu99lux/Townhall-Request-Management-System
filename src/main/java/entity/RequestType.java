package entity;

import javax.persistence.*;

@Entity
@Table(name = "request_type")
public class RequestType {
    @Id
    private String id;

    @Column
    private String name;

    @OneToOne(mappedBy = "type")
    private Request request;

    public RequestType(String id, String name){
        this.id = id;
        this.name = name;
    }

    public RequestType(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }
}
