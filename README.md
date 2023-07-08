# Library Management System

This project implements a simple library management system that handles daily operations in a library. The system allows members, consisting of students and academics, to borrow and return books, extend deadlines, and read books within the library. The program analyzes incoming requests provided in the form of an input list and performs the necessary actions accordingly.

## Actions

The following actions can be performed in the library:

1. addBook: Adds a new book to the library collection. The book type can be printed (P) or handwritten (H).

2. addMember: Adds a new member to the library. The member type can be student (S) or academic (A).

3. borrowBook: Allows a member to borrow a book from the library by specifying the book ID, member ID, and date.

4. returnBook: Enables a member to return a borrowed book to the library by specifying the book ID, member ID, and date.

5. extendBook: Allows a member to extend the deadline for a borrowed book by specifying the book ID, member ID, and current date. Each book can be extended only once.

6. readInLibrary: Allows a member to read a book within the library without borrowing it. The member ID, book ID, and current date are specified.

7. getTheHistory: Retrieves the library's history, including the number of registered academic and student members, the number of borrowed books, and the number of books read in the library.

## Constraints and Rules

- Each library member has a book limit, depending on their type (student or academic).
- Members must register in the system before borrowing books.
- Books have unique IDs assigned by the library.
- Certain books, such as handwritten books, can only be read within the library by academics.
- Students cannot read handwritten books.
- Books must be returned within the specified time limit, and late returns incur a fee.
- Academics can borrow up to 4 books simultaneously, with a two-week time limit for returns.
- Students can borrow up to 2 books simultaneously, with a one-week time limit for returns.
- Handwritten books cannot be borrowed; they can only be read within the library.
- Members can extend the deadline for a book if it hasn't been extended before and the due date hasn't passed.
- The time limit for extensions is double the original due date.
- The system handles proper error messages and exceptions for various scenarios.

## Usage

The program reads input commands from an input file and performs the necessary actions accordingly. The output is written to an output file.

Command Format:
Each command is written on a new line in the input file, with the format as follows:
<command_name><TAB><arguments>

  Example Commands:
  
  addBook<TAB>P
  
  addMember<TAB>S
  
  borrowBook<TAB>1<TAB>1<TAB>2023-04-07
  
  returnBook<TAB>2<TAB>1<TAB>2023-04-07
  
  extendBook<TAB>1<TAB>1<TAB>2023-04-07
  
  readInLibrary<TAB>2<TAB>1<TAB>2023-04-07
  
  getTheHistory

## Implementation

The project is implemented using Java and follows the principles of object-oriented programming. The system utilizes classes to model the entities, such as books and members, and encapsulates their behavior and attributes.

The design and implementation of the system adhere to best practices and follow common naming conventions in Java. Proper accessibility and visibility of methods and attributes are ensured by using appropriate access modifiers and keywords.

## Running the Program

To run the program, execute the following command:

java LibraryManagementSystem <input_file> <output_file>

- `<input_file>`: The path to the input file containing the list of commands.
- `<output_file>`: The path to the output file where the program's output will be written.

Make sure to provide the correct file paths for both the input and output files.

## Note

This README file provides an overview of the library management system project. Refer to the code documentation and comments within the code for detailed information about the implementation of each command and functionality.



[104_Assignment3_2023.pdf](https://github.com/SerhatAkbulut1/Assignment-6/files/11971724/104_Assignment3_2023.pdf)
