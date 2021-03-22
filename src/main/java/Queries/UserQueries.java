package Queries;

public class UserQueries {
    public static final String FIND_USER_BY_EMAIL = "SELECT u FROM User u WHERE u.email = :user_email";
}
