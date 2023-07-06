import java.time.LocalDate;
import java.util.ArrayList;

public class PrintedBooks extends Books {
    private LocalDate printedBorrowedTime;  // This variable stores the date on which a printed book was borrowed.
    private LocalDate printedReturnTime;    // This variable stores the date on which a printed book is due to be returned.
    private String bookType = "P";          // This variable stores the type of book. In this case, it is initialized as "P" for printed book.
    private int printedBookID;              // This variable stores the unique ID number associated with a printed book.
    private boolean isBorrowable = true;    // This variable indicates whether a printed book is currently available to be borrowed.
    private int timeOfExtend = 1;           // This variable stores the maximum number of times a user can extend the due date for a printed book.
    private int ownerID = 0;                // This variable stores the unique ID number associated with the user who currently has the printed book checked out. If the book is not currently checked out, this variable will be set to 0.

    // Constructors
    public PrintedBooks(int printedBookID, boolean isBorrowable) {
        super(printedBookID,isBorrowable);
        this.printedBookID = printedBookID;
        this.isBorrowable = isBorrowable;
    }
    // Getters and Setters
    public int getPrintedBookID() {
        return printedBookID;
    }
    public void setPrintedBookID(int printedBookID) {
        this.printedBookID = printedBookID;
    }

    @Override
    public boolean isBorrowable() {
        return isBorrowable;
    }

    @Override
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

    public LocalDate getPrintedBorrowedTime() {
        return printedBorrowedTime;
    }

    public void setPrintedBorrowedTime(LocalDate printedBorrowedTime) {
        this.printedBorrowedTime = printedBorrowedTime;
    }

    public LocalDate getPrintedReturnTime() {
        return printedReturnTime;
    }

    public void setPrintedReturnTime(LocalDate printedReturnTime) {
        this.printedReturnTime = printedReturnTime;
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
