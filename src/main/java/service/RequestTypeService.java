package service;

import dto.RequestTypeDto;
import entity.RequestType;
import mapper.RequestTypeMapper;
import repository.RequestTypeRepo;

import java.util.ArrayList;
import java.util.List;

public class RequestTypeService {
    private RequestTypeRepo requestTypeRepo;
    private RequestTypeMapper requestTypeMapper;

    public RequestTypeService(){
        requestTypeRepo = new RequestTypeRepo();
        requestTypeMapper = new RequestTypeMapper();
    }

    public List<RequestTypeDto> getAllRequestTypes(){
        List<RequestTypeDto> requestTypeDtoList = new ArrayList<RequestTypeDto>();
        List<RequestType> foundRT =requestTypeRepo.findAllRequestTypes();
        for(RequestType r : foundRT){
            requestTypeDtoList.add(requestTypeMapper.requestTypeToDto(r));
        }
        return requestTypeDtoList;
    }

    public RequestTypeDto getRequestTypeById(String id){
        return requestTypeMapper.requestTypeToDto(requestTypeRepo.findRequestTypeById(id));
    }
}
