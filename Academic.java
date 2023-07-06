import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Academic extends Person{
    private String memberType = "A";    // This variable stores the type of membership associated with a user. In this case, it is initialized as "A" for academic membership.
    private int academicID;             // This variable stores the unique ID number associated with an academic user.
    public ArrayList<Books> academicBookID = new ArrayList<Books>();            // This ArrayList stores the ID numbers of books currently checked out by an academic user.
    public ArrayList<Books> academicReadBookID = new ArrayList<Books>();    // This ArrayList stores the ID numbers of books previously read by an academic user.
    private int bookLimit = 4;          // This variable stores the maximum number of books that an academic user is allowed to check out at one time.
    private long maxExtendTime = 2;     // This variable stores the maximum number of times an academic user can extend the due date for a checked out book (in weeks).

    // Constructors
    public Academic(int academicID){
        super(academicID);
        this.academicID = academicID;
    }
    // Getters and Setters
    @Override
    public String getMemberType() {
        return memberType;
    }
    @Override
    public void setMemberType(String memberType) {
        this.memberType = memberType;
    }
    public int getAcademicID() {
        return academicID;
    }
    public void setAcademicID(int academicID) {
        this.academicID = academicID;
    }
    public int getBookLimit() {
        return bookLimit;
    }

    public void setBookLimit(int bookLimit) {
        this.bookLimit = bookLimit;
    }

    public long getMaxExtendTime() {
        return maxExtendTime;
    }

    public void setMaxExtendTime(long maxExtendTime) {
        this.maxExtendTime = maxExtendTime;
    }


}
