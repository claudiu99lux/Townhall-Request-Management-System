package dto;

import entity.Request;
import java.util.List;
import java.util.UUID;

public class RequestTypeDto {
    private String id;
    private String name;
    private List<Request> requests;

    public RequestTypeDto(){
        this.id = UUID.randomUUID().toString();
    }

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

    @Override
    public String toString() {
        return name;
    }
}
