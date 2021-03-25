package mapper;

import dto.RequestDto;
import entity.Request;

public class RequestMapper {

    public RequestMapper(){}

    public RequestDto requestToDto(Request r){
        RequestDto rd = new RequestDto();
        rd.setId(r.getId());
        rd.setDate(r.getDate());
        rd.setApproved(r.getApproved());
        rd.setContent(r.getContent());
        rd.setType(r.getType());
        rd.setOwner_address(r.getOwner_address());
        return rd;
    }

    public Request requestDtoToRequest(RequestDto rd){
        Request r = new Request();
        r.setId(rd.getId());
        r.setDate(rd.getDate());
        r.setApproved(rd.getApproved());
        r.setContent(rd.getContent());
        r.setType(rd.getType());
        r.setOwner_address(rd.getOwner_address());
        return r;
    }
}
