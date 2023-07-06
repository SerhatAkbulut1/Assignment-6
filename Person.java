import java.time.LocalDate;
import java.time.LocalDateTime;

public class Person {
    private String memberType;  // This variable stores the type of membership associated with a user.
    private  int personID = 0;  // This variable stores the unique ID number associated with a user.
    private int memberBookID;   // This variable stores the ID number of the book currently checked out by a user.

    // Constructors
    public Person(int personID) {
        this.personID = personID;
    }
    // Getters and Setters
    public int getPersonID() {
        return personID;
    }
    public void setPersonID(int personID) {
        this.personID = personID;
    }
    public int getMemberBookID() {
        return memberBookID;
    }

    public void setMemberBookID(int memberBookID) {
        this.memberBookID = memberBookID;
    }

    public String getMemberType() {
        return memberType;
    }

    public void setMemberType(String memberType) {
        this.memberType = memberType;
    }
}
