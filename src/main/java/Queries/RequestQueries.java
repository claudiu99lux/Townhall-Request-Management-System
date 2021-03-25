package Queries;

public class RequestQueries {
    public static final String FIND_REQUESTS_BY_USER_ID = "SELECT r from Request r JOIN FETCH r.owner_address AS o JOIN FETCH o.user WHERE owner_id = :id";
    public static final String FIND_ALL_REQUESTS = "FROM Request r";
    public static final String FIND_REQUESTS_BY_ADDR_AND_TYPE = "From Request r WHERE address_id=:addressID AND request_type=:typeID";
    public static final String FIND_REQUESTS_BY_DATE = "From Request r WHERE date = :date";
    public static final String FIND_REQUESTS_BY_TYPE = "From Request r WHERE request_type=:typeID";

}
