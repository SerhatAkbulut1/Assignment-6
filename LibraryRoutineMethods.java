import javax.sound.midi.Soundbank;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLOutput;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class LibraryRoutineMethods {
    // Write the provided text to the file followed by a newline character.
    public static void writeFile(String text,String outputName) throws IOException {
        try{
            FileWriter writer = new FileWriter(outputName,true);
            writer.write(text+"\n");
            writer.close();
        }catch (IOException e){
            throw new IOException();
        }
    }
    // This method takes a string representing a date in the format "yyyy-MM-dd",
    // converts it to a LocalDate object, and returns it.
    public static LocalDate timeFormatter(String Time){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dateTime = LocalDate.parse(Time, formatter);
        return dateTime;
    }
    public static void addBook(ArrayList<Books> bookList,String bookType,int bookNumber,String outputName) throws IOException {
        if(bookType.equals("P")){
            // Create a new PrintedBooks object with the given book number and add it to the book list.
            PrintedBooks printedbook = new PrintedBooks(bookNumber,true);
            bookList.add(printedbook);
            writeFile("Created new book: Printed [id: "+bookNumber+"]",outputName);
        } else if (bookType.equals("H")) {
            // Create a new HandWrittenBooks object with the given book number and add it to the book list.
            HandWrittenBooks handWrittenBook = new HandWrittenBooks(bookNumber,true);
            bookList.add(handWrittenBook);
            writeFile("Created new book: Handwritten [id: "+bookNumber+"]",outputName);
        }else{
            // If the book type is neither Printed nor Handwritten, display an error message.
            writeFile("The input book genre should be either 'P' or 'H'!!",outputName);
        }
    }
    public static void addMember(ArrayList<Person> memberList,String personType,int memberNumber,String outputName) throws IOException {
        if(personType.equals("S")){
            // Create a new Students object with the given person type and add it to the member list.
            Student student = new Student(memberNumber);
            memberList.add(student);
            writeFile("Created new member: Student [id: "+memberNumber+"]",outputName);
        } else if (personType.equals("A")) {
            // Create a new Academic object with the given person type and add it to the member list.
            Academic academic = new Academic(memberNumber);
            memberList.add(academic);
            writeFile("Created new member: Academic [id: "+memberNumber+"]",outputName);
        }else{
            // If the person type is neither Student nor Academic, display an error message.
            writeFile("The input membership type should be either 'S' or 'A'!!",outputName);
        }
    }
    public static void borrowBook(ArrayList<Books> bookList,ArrayList<Person> memberList, String bookId, String memberId, String date,String outputName) throws IOException {
        LocalDate borrowingTime = timeFormatter(date);  // Parse the borrowing time from a String date to a LocalDate object using a custom timeFormatter method
        int bookID = Integer.parseInt(bookId);          // Parse the book ID from a String to an int
        int memberID = Integer.parseInt(memberId);      // Parse the member ID from a String to an int
        boolean hasMember = false;
        boolean hasBook = false;

        for (Books book:bookList) {
            if(book.getBooksID() == bookID){
                for (Person member:memberList) {
                    if(member.getPersonID() == memberID){
                        if(book.getBookType().equals("H")){     // Check if the book type is Handwritten.
                            writeFile("You can not borrow Handwrittenbooks !!!",outputName);
                            break;
                        }else{
                            if(book.isBorrowable()){     // Check if the book is borrowable
                                if(member.getMemberType().equals("A")){   // Check if the member is an academic member
                                    if(((Academic)member).getBookLimit() > ((Academic)member).academicBookID.size()){  //Check if the academic member has reached the book limit
                                        if(((Academic)member).academicBookID.size() == 0){ // If this is the first book being borrowed by the academic member
                                            // Set the borrowed time, return time, and borrowable status of the printed book.
                                            ((PrintedBooks)book).setPrintedBorrowedTime(borrowingTime);
                                            ((PrintedBooks)book).setPrintedReturnTime(borrowingTime.plusWeeks(((Academic) member).getMaxExtendTime()));
                                            ((PrintedBooks)book).setBorrowable(false);
                                            ((Academic)member).academicBookID.add(book);
                                            writeFile("The book ["+((PrintedBooks)book).getPrintedBookID()+"] was borrowed by member ["+((Academic)member).getAcademicID()+"] at "+date,outputName);
                                            return;
                                        }else{
                                            for (Books Book:((Academic)member).academicBookID) {
                                                if(bookID == Book.getBooksID()){    // Check if the member has already borrowed the book.
                                                    writeFile("You cannot borrow this book!",outputName);
                                                    return;
                                                }else{
                                                    //Set the borrowed time, return time, and borrowable status for the book.
                                                    ((PrintedBooks)book).setPrintedBorrowedTime(borrowingTime);
                                                    ((PrintedBooks)book).setPrintedReturnTime(borrowingTime.plusWeeks(((Academic) member).getMaxExtendTime()));
                                                    ((PrintedBooks)book).setBorrowable(false);
                                                    ((Academic)member).academicBookID.add(book);
                                                    writeFile("The book ["+((PrintedBooks)book).getPrintedBookID()+"] was borrowed by member ["+((Academic)member).getAcademicID()+"] at "+date,outputName);
                                                    return;
                                                }
                                            }
                                        }
                                    }else{
                                        writeFile("The book ["+((PrintedBooks)book).getPrintedBookID()+"] was borrowed by member ["+((Academic)member).getAcademicID()+"] at "+date,outputName);
                                    }
                                }else{
                                    if(member.getMemberType().equals("S")) {    // Check if the member is a student
                                        if(((Student)member).getBookLimit() > ((Student) member).studentBookID.size()){ // Check if the student has not exceeded their book limit
                                            if(((Student) member).studentBookID.size() == 0){   // Check if the student has not borrowed any books yet
                                                //Set the borrowed time, return time, and borrowable status for the boo
                                                ((PrintedBooks) book).setPrintedBorrowedTime(borrowingTime);
                                                ((PrintedBooks) book).setPrintedReturnTime(borrowingTime.plusWeeks(((Student) member).getMaxExtendTime()));
                                                ((PrintedBooks) book).setBorrowable(false);
                                                ((Student) member).studentBookID.add(book);
                                                writeFile("The book [" + ((PrintedBooks) book).getPrintedBookID() + "] was borrowed by member [" + ((Student) member).getStudentID() + "] at " + date,outputName);
                                                return;
                                            }else{
                                                for (Books Book : ((Student) member).studentBookID) {
                                                    if (bookID == Book.getBooksID()) { // Check if the member has already borrowed the book.
                                                        writeFile("You cannot borrow this book!",outputName);
                                                        return;
                                                    } else {
                                                        //Set the borrowed time, return time, and borrowable status for the book.
                                                        ((PrintedBooks) book).setPrintedBorrowedTime(borrowingTime);
                                                        ((PrintedBooks) book).setPrintedReturnTime(borrowingTime.plusWeeks(((Student) member).getMaxExtendTime()));
                                                        ((PrintedBooks) book).setBorrowable(false);
                                                        ((Student) member).studentBookID.add(book);
                                                        writeFile("The book [" + ((PrintedBooks) book).getPrintedBookID() + "] was borrowed by member [" + ((Student) member).getStudentID() + "] at " + date,outputName);
                                                        return;
                                                    }
                                                }
                                            }
                                        }else{
                                            writeFile("You have exceeded the borrowing limit!",outputName);
                                        }
                                    }
                                }
                            }else{
                                writeFile("You can not read this book!",outputName);
                            }
                        }
                    }
                }
            }
        }
    }
    public static void  returnBook(ArrayList<Books> bookList,ArrayList<Person> memberList, String bookId, String memberId, String date,String outputName) throws IOException {
        LocalDate returnTime = timeFormatter(date); // Parse the borrowing time from a String date to a LocalDate object using a custom timeFormatter method.
        int bookID = Integer.parseInt(bookId);      // Parse the book ID from a String to an int.
        int memberID = Integer.parseInt(memberId);  // Parse the member ID from a String to an int.

        for (Books book:bookList) {
            if(book.getBooksID() == bookID){
                for (Person member:memberList) {
                    if(member.getPersonID() == memberID){
                        if(member.getMemberType().equals("A")){ // Check if the member is an academic member.
                            for (Books Book:((Academic)member).academicReadBookID) {
                                if(bookID == Book.getBooksID()){
                                    // Update book status, remove from member's read book list and print return message.
                                    writeFile("The book ["+Book.getBooksID()+"] was returned by member ["+((Academic)member).getAcademicID()+"] at "+date+" Fee: 0",outputName);
                                    Book.setBorrowable(true);
                                    ((Academic)member).academicReadBookID.remove(Book);
                                    return;
                                }
                            }
                            for (Books Book:((Academic)member).academicBookID) {
                                if(bookID == Book.getBooksID()){
                                    long feeTime = ChronoUnit.DAYS.between(((PrintedBooks)Book).getPrintedReturnTime(), returnTime); //It calculates the fee by finding the difference in days between the return time and the printed return time of the book
                                    if(feeTime > 0){
                                        writeFile("The book ["+Book.getBooksID()+"] was returned by member ["+((Academic)member).getAcademicID()+"] at "+date+" Fee:"+feeTime,outputName);
                                    }else{
                                        writeFile("The book ["+Book.getBooksID()+"] was returned by member ["+((Academic)member).getAcademicID()+"] at "+date+" Fee: 0",outputName);
                                    }
                                    // Update book status, remove from member's read book list and print return message.
                                    Book.setBorrowable(true);
                                    ((Academic)member).academicBookID.remove(Book);
                                    return;
                                }
                            }
                        }
                        if(member.getMemberType().equals("S")){ //Check if the member is an student member.
                            for (Books Book: ((Student)member).studentReadBookID) {
                                if(bookID == Book.getBooksID()){
                                    // Update book status, remove from member's read book list and print return message.
                                    writeFile("The book ["+Book.getBooksID()+"] was returned by member ["+((Student)member).getStudentID()+"] at "+date+" Fee: 0",outputName);
                                    Book.setBorrowable(true);
                                    ((Student)member).studentReadBookID.remove(Book);
                                    return;
                                }
                            }
                            for (Books Book:((Student)member).studentBookID) {
                                if(bookID == Book.getBooksID()){
                                    long feeTime = ChronoUnit.DAYS.between(((PrintedBooks)Book).getPrintedReturnTime(), returnTime); //It calculates the fee by finding the difference in days between the return time and the printed return time of the book
                                    if(feeTime > 0){
                                        writeFile("The book ["+Book.getBooksID()+"] was returned by member ["+((Student)member).getStudentID()+"] at "+date+" Fee:"+feeTime,outputName);
                                    }else{
                                        writeFile("The book ["+Book.getBooksID()+"] was returned by member ["+((Student)member).getStudentID()+"] at "+date+" Fee: 0",outputName);
                                    }
                                    // Update book status, remove from member's read book list and print return message.
                                    Book.setBorrowable(true);
                                    ((Student)member).studentBookID.remove(Book);
                                    return;
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    public static void extendBook(ArrayList<Books> bookList,ArrayList<Person> memberList, String bookId, String memberId, String date,String outputName) throws IOException {
        LocalDate returnTime = timeFormatter(date); // Parse the borrowing time from a String date to a LocalDate object using a custom timeFormatter method.
        int bookID = Integer.parseInt(bookId);      // Parse the book ID from a String to an int.
        int memberID = Integer.parseInt(memberId);  // Parse the member ID from a String to an int.

        for (Books book:bookList) {
            if(book.getBooksID() == bookID){
                for (Person member:memberList) {
                    if(member.getMemberType().equals("A")){
                        for (Books Book:((Academic)member).academicBookID) {
                            if(bookID == Book.getBooksID()){
                                if(Book.getTimeOfExtend() > 1){ //Checks the number of times the book extension time is set.
                                    writeFile("You cannot extend the deadline!",outputName);
                                    Book.setTimeOfExtend(1);
                                    return;
                                }else if(returnTime.isAfter(((PrintedBooks)book).getPrintedReturnTime())){ // If the return time is after the original deadline, the book cannot be extended.
                                    writeFile("You cannot extend the deadline!",outputName);
                                } else{
                                    ((PrintedBooks)book).setPrintedReturnTime(returnTime.plusWeeks(((Academic)member).getMaxExtendTime())); //Return time of the printed book to a date that is extended by the maximum extension time allowed for academic members.
                                    Book.setTimeOfExtend(2);    //Set the number of times the book extension time is set.
                                    writeFile("The deadline of book ["+Book.getBooksID()+"] was extended by member ["+memberId+"] at "+date,outputName);
                                    writeFile("New deadline of book ["+bookId+"] is "+returnTime.plusWeeks(((Academic)member).getMaxExtendTime()),outputName);
                                    return;
                                }
                            }
                        }
                    }else{
                        for (Books Book:((Student)member).studentBookID) {
                            if(bookID == Book.getBooksID()){
                                if(Book.getTimeOfExtend() >1 ){//Checks the number of times the book extension time is set.
                                    writeFile("You cannot extend the deadline!",outputName);
                                    Book.setTimeOfExtend(1);
                                    return;
                                }else if(returnTime.isAfter(((PrintedBooks)book).getPrintedReturnTime())){ // If the return time is after the original deadline, the book cannot be extended.
                                    writeFile("You cannot extend the deadline!",outputName);
                                }else{
                                    ((PrintedBooks)book).setPrintedReturnTime(returnTime.plusWeeks(((Student)member).getMaxExtendTime()));//Return time of the printed book to a date that is extended by the maximum extension time allowed for student members.
                                    Book.setTimeOfExtend(2); //Set the number of times the book extension time is set.
                                    writeFile("The deadline of book ["+Book.getBooksID()+"] was extended by member ["+memberId+"] at "+date,outputName);
                                    writeFile("New deadline of book ["+bookId+"] is "+returnTime.plusWeeks(((Student)member).getMaxExtendTime()),outputName);
                                    return;
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    public static void  readInLibrary(ArrayList<Books> bookList,ArrayList<Person> memberList, String bookId, String memberId, String date,String outputName) throws IOException {
        LocalDate borrowedTime = timeFormatter(date); // Parse the borrowing time from a String date to a LocalDate object using a custom timeFormatter method.
        int bookID = Integer.parseInt(bookId);        // Parse the book ID from a String to an int.
        int memberID = Integer.parseInt(memberId);    // Parse the member ID from a String to an int.

        for (Books book:bookList) {
            if(book.getBooksID() == bookID){
                for (Person member:memberList) {
                    if(member.getPersonID() == memberID){
                        if(book.isBorrowable()){
                            if(member.getMemberType().equals("A")){
                                if(((Academic)member).academicReadBookID.size() == 0){// If the member has not read any book before, the book is borrowed.
                                    writeFile("The book ["+bookId+"] was read in library by member ["+memberId+"] at "+date,outputName);
                                    book.setBorrowable(false);
                                    book.setBorrowedTime(borrowedTime);
                                    ((Academic)member).academicReadBookID.add(book);
                                    return;
                                }else{
                                    for (Books Book:((Academic)member).academicReadBookID) {
                                        if(bookID == Book.getBooksID()){// If the member has already read the book before, they cannot borrow it again.
                                            writeFile("You can not read this book!",outputName);
                                            return;
                                        }if(bookID != Book.getBooksID()){// If the member has not read the book before, they can borrow the book.
                                            writeFile("The book ["+bookId+"] was read in library by member ["+memberId+"] at "+date,outputName);
                                            book.setBorrowable(false);
                                            book.setBorrowedTime(borrowedTime);
                                            ((Academic)member).academicReadBookID.add(book);
                                            return;
                                        }
                                    }
                                }
                            }else{
                                if(book.getBookType().equals("H")){
                                    writeFile("Students can not read handwritten books! ",outputName);
                                    return;
                                }else if(((Student)member).studentReadBookID.size() == 0){// Check if student has not read any book before.
                                    writeFile("The book ["+bookId+"] was read in library by member ["+memberId+"] at "+date,outputName);
                                    book.setBorrowable(false);
                                    book.setBorrowedTime(borrowedTime);
                                    ((Student)member).studentReadBookID.add(book);
                                    return;
                                }else{
                                    // Check if student has read any book before.
                                    for (Books Book:((Student)member).studentReadBookID) {
                                        if(bookID == Book.getBooksID()){// Check if student has already read the book.
                                            writeFile("You can not read this book!",outputName);
                                            return;
                                        }if(bookID != Book.getBooksID()){ // If student has not read the book before, add to their list of read books.
                                            writeFile("The book ["+bookId+"] was read in library by member ["+memberId+"] at "+date,outputName);
                                            book.setBorrowable(false);
                                            book.setBorrowedTime(borrowedTime);
                                            ((Student)member).studentReadBookID.add(book);
                                            return;
                                        }
                                    }
                                }
                            }
                        }else{
                            writeFile("You can not read this book!",outputName);
                        }
                    }
                }
            }
        }
   }
    public static void getTheHistory(ArrayList<Books> booksList,ArrayList<Person> memberList,String outputName) throws IOException {
        writeFile("History of library:"+"\n",outputName);

        int numberOfStudent = 0;           // This variable will store the number of student members in the library system.
        int numberOfAcademic = 0;          // This variable will store the number of academic members in the library system.
        int numberOfHandWritten = 0;       // This variable will store the number of handwritten books in the library system.
        int numberOfPrinted = 0;           // This variable will store the number of printed books in the library system.
        ArrayList<Books> numberOfBorrowed = new ArrayList<Books>();     //This ArrayList will store the books that are currently borrowed by members.
        ArrayList<Books> numberOfReadinLibrary = new ArrayList<Books>();//This ArrayList will store the books that are currently available in the library for members to read.

        //This code snippet counts the number of member in each member type (student or academic) and keeps a list of borrowed and read-in-library books for each member.
        for (Person member: memberList) {
            if(member.getMemberType().equals("S")){
                numberOfStudent++;
                if(((Student)member).studentBookID.size() != 0){
                    for (Books book: ((Student) member).studentBookID) {
                        book.setOwnerID(((Student)member).getStudentID());
                        numberOfBorrowed.add(book);
                    }
                }
                if(((Student)member).studentReadBookID.size() != 0){
                    for (Books book:((Student) member).studentReadBookID) {
                        book.setOwnerID(((Student)member).getStudentID());
                        numberOfReadinLibrary.add(book);
                    }
                }
            }
            if(member.getMemberType().equals("A")){
                numberOfAcademic++;
                if(((Academic)member).academicBookID.size() != 0){
                    for (Books book:((Academic)member).academicBookID) {
                        book.setOwnerID(((Academic)member).getAcademicID());
                        numberOfBorrowed.add(book);
                    }
                }
                if(((Academic)member).academicReadBookID.size() != 0){
                    for(Books book:((Academic)member).academicReadBookID){
                        book.setOwnerID(((Academic)member).getAcademicID());
                        numberOfReadinLibrary.add(book);
                    }
                }
            }
        }
        //This code counts the number of books for each book type.
        for (Books book:booksList) {
            if(book.getBookType().equals("H")){
                numberOfHandWritten++;
            }
            if(book.getBookType().equals("P")){
                numberOfPrinted++;
            }
        }

        //This method prints the history of all events in the library in the order they were written to the file.
        writeFile("Number of students: "+numberOfStudent,outputName);
        for (Person member:memberList) {
            if(member.getMemberType().equals("S")){
                writeFile("Student [id: "+((Student)member).getStudentID()+"]",outputName);
            }
        }
        writeFile("\n"+"Number of academics: "+numberOfAcademic,outputName);
        for (Person member:memberList) {
            if(member.getMemberType().equals("A")){
                writeFile("Academic [id: "+((Academic)member).getAcademicID()+"]",outputName);
            }
        }
        writeFile("\n"+"Number of printed books: "+numberOfPrinted,outputName);
        for (Books book:booksList) {
            if(book.getBookType().equals("P")){
                writeFile("Printed [id: "+((PrintedBooks)book).getPrintedBookID()+"]",outputName);
            }
        }
        writeFile("\n"+"Number of handwritten books: "+numberOfHandWritten,outputName);
        for (Books book:booksList) {
            if(book.getBookType().equals("H")){
                writeFile("HandWritten [id: "+((HandWrittenBooks)book).getHandWrittenBookID()+"]",outputName);
            }
        }
        writeFile("\n"+"Number of borrowed books: "+numberOfBorrowed.size(),outputName);
        for(Books book: numberOfBorrowed){
            writeFile("The book ["+book.getBooksID()+"] was borrowed by member ["+book.getOwnerID()+"] at "+ ((PrintedBooks)book).getPrintedBorrowedTime(),outputName);
        }
        writeFile("\n"+"Number of books read in library: "+numberOfReadinLibrary.size(),outputName);
        for(Books book: numberOfReadinLibrary){
            if(book.getBookType().equals("P")){
                writeFile("The book ["+((PrintedBooks)book).getPrintedBookID()+"] was read in library by member ["+book.getOwnerID()+"] at "+book.getBorrowedTime(),outputName);
            }else{
                writeFile("The book ["+((HandWrittenBooks)book).getHandWrittenBookID()+"] was read in library by member ["+book.getOwnerID()+"] at "+book.getBorrowedTime(),outputName);
            }
        }
    }
}
