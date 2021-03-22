package Queries;

public class AddressQueries {
    public static final String FIND_ADDRESS_BY_USER_ID = "SELECT a from Address a JOIN FETCH a.user WHERE owner_id = :id";

}
