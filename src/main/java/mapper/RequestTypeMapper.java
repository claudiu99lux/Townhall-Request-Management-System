package mapper;

import dto.RequestTypeDto;
import entity.RequestType;

public class RequestTypeMapper {
    public RequestTypeMapper(){}

    public RequestTypeDto requestTypeToDto(RequestType r){
        RequestTypeDto rd = new RequestTypeDto();
        rd.setId(r.getId());
        rd.setName(r.getName());
        return rd;
    }

    public RequestType requestTypeDtoToRequestType(RequestTypeDto rd){
        RequestType r = new RequestType();
        r.setId(rd.getId());
        r.setName(rd.getName());
        return r;
    }
}
