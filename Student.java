import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Student extends Person {
    private String memberType = "S";        // This variable stores the type of membership associated with a user. In this case, it is initialized as "S" for student membership.
    private int studentID;                  // This variable stores the unique ID number associated with a student user.
    public ArrayList<Books> studentBookID = new ArrayList<Books>();         // This ArrayList stores the ID numbers of books currently checked out by a student user.
    public ArrayList<Books> studentReadBookID = new ArrayList<Books>(); // This ArrayList stores the ID numbers of books previously read by a student user.
    private int bookLimit = 2;             // This variable stores the maximum number of books that a student user is allowed to check out at one time.
    private long maxExtendTime = 1;        // This variable stores the maximum number of times a student user can extend the due date for a checked out book (in days).

    // Constructors
    public Student(int studentID) {
        super(studentID);
        this.studentID =studentID;
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

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
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
