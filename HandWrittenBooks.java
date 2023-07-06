import java.time.LocalDate;
import java.util.ArrayList;

public class HandWrittenBooks extends Books {
    private LocalDate handwrittenBorrowedTime;  // This variable stores the date on which a handwritten book was borrowed.
    private LocalDate handwrittenReturnTime;    // This variable stores the date on which a handwritten book is due to be returned.
    private String bookType = "H";              // This variable stores the type of book. In this case, it is initialized as "H" for handwritten book.
    private int handWrittenBookID;              // This variable stores the unique ID number associated with a handwritten book.
    private boolean isBorrowable = true;        // This variable indicates whether a handwritten book is currently available to be borrowed.
    private int timeOfExtend = 1;               // This variable stores the maximum number of times a user can extend the due date for a handwritten book.
    private int ownerID = 0;                    // This variable stores the unique ID number associated with the user who currently has the handwritten book checked out. If the book is not currently checked out, this variable will be set to 0.

    // Constructors
    public HandWrittenBooks(int handWrittenBookID, boolean isBorrowable) {
        super(handWrittenBookID,isBorrowable);
        this.handWrittenBookID = handWrittenBookID;
        this.isBorrowable = isBorrowable;
    }
    // Getters and Setters
    public int getHandWrittenBookID() {
        return handWrittenBookID;
    }
    public void setHandWrittenBookID(int handWrittenBookID) {
        this.handWrittenBookID = handWrittenBookID;
    }

    @Override
    public boolean isBorrowable() {
        return isBorrowable;
    }

    public void setBorrowable(boolean borrowable) {
        this.isBorrowable = borrowable;
    }

    public String getBookType() {
        return bookType;
    }

    public void setBookType(String bookType) {
        this.bookType = bookType;
    }

    @Override
    public int getTimeOfExtend() {
        return timeOfExtend;
    }

    @Override
    public void setTimeOfExtend(int timeOfExtend) {
        this.timeOfExtend = timeOfExtend;
    }

    public LocalDate getHandwrittenBorrowedTime() {
        return handwrittenBorrowedTime;
    }

    public void setHandwrittenBorrowedTime(LocalDate handwrittenBorrowedTime) {
        this.handwrittenBorrowedTime = handwrittenBorrowedTime;
    }

    public LocalDate getHandwrittenReturnTime() {
        return handwrittenReturnTime;
    }

    public void setHandwrittenReturnTime(LocalDate handwrittenReturnTime) {
        this.handwrittenReturnTime = handwrittenReturnTime;
    }

    @Override
    public int getOwnerID() {
        return ownerID;
    }

    @Override
    public void setOwnerID(int ownerID) {
        this.ownerID = ownerID;
    }
}
