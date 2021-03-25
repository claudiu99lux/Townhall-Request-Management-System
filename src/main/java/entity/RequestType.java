package entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "request_type")
public class RequestType {
    @Id
    private String id;

    @Column
    private String name;

    @OneToMany(mappedBy = "type", cascade = CascadeType.ALL)
    private List<Request> requests;

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

    public List<Request> getRequests() {
        return requests;
    }

    public void setRequests(List<Request> requests) {
        this.requests = requests;
    }
}
