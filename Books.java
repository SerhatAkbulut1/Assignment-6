import java.time.LocalDate;
import java.util.ArrayList;

public class Books {
    private LocalDate BorrowedTime;         // This variable stores the date on which a book was borrowed.
    private LocalDate ReturnTime;            // This variable stores the date on which a book is due to be returned.
    private String bookType;                // This variable stores the type of book.
    private int booksID = 0;                // This variable stores the unique ID number associated with a book.
    private boolean isBorrowable = true;    // This variable indicates whether a book is currently available to be borrowed.
    private int timeOfExtend = 1;           // This variable stores the maximum number of times a user can extend the due date for a book.
    private int ownerID = 0;                // This variable stores the unique ID number associated with the user who currently has the book checked out. If the book is not currently checked out, this variable will be set to 0.

    // Constructors
    public Books(int booksID, boolean isBorrowable) {
        this.booksID = booksID;
        this.isBorrowable = isBorrowable;
    }

    // Getters and Setters
    public int getBooksID() {
        return booksID;
    }

    public void setBooksID(int booksID) {
        this.booksID = booksID;
    }

    public boolean isBorrowable() {
        return isBorrowable;
    }

    public void setBorrowable(boolean borrowable) {
        isBorrowable = borrowable;
    }

    public String getBookType() {
        return bookType;
    }

    public void setBookType(String bookType) {
        this.bookType = bookType;
    }

    public int getTimeOfExtend() {
        return timeOfExtend;
    }

    public void setTimeOfExtend(int timeOfExtend) {
        this.timeOfExtend = timeOfExtend;
    }

    public LocalDate getBorrowedTime() {
        return BorrowedTime;
    }

    public void setBorrowedTime(LocalDate borrowedTime) {
        BorrowedTime = borrowedTime;
    }

    public LocalDate getReturnTime() {
        return ReturnTime;
    }

    public void setReturnTime(LocalDate returnTime) {
        ReturnTime = returnTime;
    }

    public int getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(int ownerID) {
        this.ownerID = ownerID;
    }
}
