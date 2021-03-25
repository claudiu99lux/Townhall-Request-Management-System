package dto;

import entity.Address;
import entity.RequestType;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class RequestDto {

    private String id;
    private RequestType type;
    private int approved;
    private LocalDate date;
    private Address owner_address;
    private String content;

    public RequestDto(Address address, RequestType type, String content){
        this.id = UUID.randomUUID().toString();
        this.owner_address = address;
        this.type = type;
        this.approved = 0;
        this.date = LocalDate.now();
        this.content = content;
    }

    public RequestDto(){
        this.id = UUID.randomUUID().toString();
        this.date = LocalDate.now();
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

    public void setOwner_address(Address owner_address) {
        this.owner_address = owner_address;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStatus(){
        if(approved==0)
            return "Pending";
        else if(approved==1)
            return "Approved";
        else
            return "Declined";
    }

    public String getContentPreview(){
        String preview = this.content.replace("\n", " ").replace("\r", " ");
        // limit to 36 characters
        if(preview.length()<36)
            preview = preview.substring(0, preview.length());
        else
            preview = preview.substring(0, 36);

        return preview;
    }

    public String getFormattedDate(){
        String formattedDate = date.format(DateTimeFormatter.ofPattern("dd-MMM-YYYY"));
        return formattedDate;
    }
}
