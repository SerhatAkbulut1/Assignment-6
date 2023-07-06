import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(args[1],true))){
            try (Scanner scanner = new Scanner(new BufferedReader(new FileReader(args[0])))) {
                ArrayList<Person> memberList = new  ArrayList<Person>();
                ArrayList<Books> booksList = new  ArrayList<Books>();
                String outputName = args[1];
                int memberCount = 1;
                int bookCount = 1;
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    String[] commands = line.split("\t");
                    switch (commands[0]){
                        case "addBook":
                            LibraryRoutineMethods.addBook(booksList,commands[1],bookCount,outputName);
                            bookCount++;
                            break;
                        case "addMember":
                            LibraryRoutineMethods.addMember(memberList,commands[1],memberCount,outputName);
                            memberCount++;
                            break;
                        case "borrowBook":
                            LibraryRoutineMethods.borrowBook(booksList,memberList,commands[1],commands[2],commands[3],outputName);
                            break;
                        case "returnBook":
                            LibraryRoutineMethods.returnBook(booksList,memberList,commands[1],commands[2],commands[3],outputName);
                            break;
                        case "extendBook":
                            LibraryRoutineMethods.extendBook(booksList,memberList,commands[1],commands[2],commands[3],outputName);
                            break;
                        case "readInLibrary":
                            LibraryRoutineMethods.readInLibrary(booksList,memberList,commands[1],commands[2],commands[3],outputName);
                            break;
                        case "getTheHistory":
                            LibraryRoutineMethods.getTheHistory(booksList,memberList,outputName);
                            break;
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}