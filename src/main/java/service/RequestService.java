package service;

import dto.RequestDto;
import entity.Address;
import entity.Request;
import entity.RequestType;
import mapper.AddressMapper;
import mapper.RequestMapper;
import repository.RequestRepo;
import repository.RequestTypeRepo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class RequestService {
    private AddressService addressService;
    private RequestTypeRepo requestTypeRepo;
    private RequestMapper requestMapper;
    private AddressMapper addressMapper;
    private RequestRepo requestRepo;

    public RequestService(){
        addressService = new AddressService();
        requestMapper = new RequestMapper();
        requestRepo = new RequestRepo();
        requestTypeRepo = new RequestTypeRepo();
        addressMapper = new AddressMapper();
    }

    public void insertNewRequest(RequestDto requestDto, String address_id, String type_id){
        Request request = requestMapper.requestDtoToRequest(requestDto);
        RequestType requestType = requestTypeRepo.findRequestTypeById(type_id);
        Address address = addressMapper.addressDtoToAddress(addressService.getAddressById(address_id));
        request.setOwner_address(address);
        request.setType(requestType);
        requestRepo.insertRequest(request);
    }

    public List<RequestDto> getRequestsByUserID(String user_id){
        List<RequestDto> requestDtoList = new ArrayList<RequestDto>();
        List<Request> requests = requestRepo.findRequestsByUserId(user_id);
        for(Request r : requests){
            requestDtoList.add(requestMapper.requestToDto(r));
        }
        return requestDtoList;
    }

    public List<RequestDto> getAllRequests(){
        List<RequestDto> requestDtoList = new ArrayList<RequestDto>();
        List<Request> requests = requestRepo.findAllRequests();
        for(Request r : requests){
            requestDtoList.add(requestMapper.requestToDto(r));
        }
        return requestDtoList;
    }

    public void editRequest(RequestDto request, String newAddressID, String newTypeID){
        Request r = requestMapper.requestDtoToRequest(request);
        RequestType requestType = requestTypeRepo.findRequestTypeById(newTypeID);
        Address address = addressMapper.addressDtoToAddress(addressService.getAddressById(newAddressID));
        r.setOwner_address(address);
        r.setType(requestType);
        requestRepo.updateRequest(r);
    }

    public void updateRequest(RequestDto request){
        requestRepo.updateRequest(requestMapper.requestDtoToRequest(request));
    }

    public void deleteRequest(RequestDto request){
        Request r = requestMapper.requestDtoToRequest(request);
        requestRepo.deleteRequest(r);
    }

    public int countCurrentYearRequestsByAddressAndType(String addressID, String typeID){
        List<Request> requests = requestRepo.findRequestsByAddressAndType(addressID, typeID);
        int count = 0;
        int currentYear = LocalDate.now().getYear();
        for(Request r : requests){
            if(r.getDate().getYear() == currentYear)
                count++;
        }
        return count;
    }

    public Set<LocalDate> getRequestsDateSet(){
        List<Request> requests = requestRepo.findAllRequests();
        Set<LocalDate> dates = new TreeSet<LocalDate>();
        for(Request r : requests)
            dates.add(r.getDate());
        return dates;
    }

    public List<RequestDto> getRequestsByDate(LocalDate date){
        List<Request> found = requestRepo.findRequestsByDate(date);
        List<RequestDto> requests = new ArrayList<RequestDto>();
        for(Request r : found){
            requests.add(requestMapper.requestToDto(r));
        }
        return requests;
    }
}
