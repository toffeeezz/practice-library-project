import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Database extends Utils {

    static final String ACCOUNTS_JSON = "accounts.json";
    static final String LIBRARY_JSON = "lib.json";
    private static final Gson gson = new Gson();

    private static final AdminAccount admin = new AdminAccount("John Mangubat", "Admin", "094703", "123456");
    private static final ArrayList<Account> accountList = new ArrayList<>();
    private static final String[] accessLevels = {"Novice", "Intermediate", "Experienced", "Masteral", "Origin"};
    private static final List<Book> bookList = new ArrayList<>();

    public static void loadLibrary(){
        try (FileReader reader = new FileReader(LIBRARY_JSON)) {
            List<Book> books = gson.fromJson(reader, new TypeToken<List<Book>>() {}.getType());
            if(books != null){
                PrintLine("Library Loaded!");
                bookList.addAll(books);
            }
        } catch (IOException e) {
            System.out.println("No existing file found. Returning empty list.");
        }
    }
    public static void saveLibrary(List<Book> books){
        try (Writer writer = new FileWriter(LIBRARY_JSON)) {
            gson.toJson(books, writer);
            System.out.println("Library saved to " + LIBRARY_JSON + " (" + books.size() + " entries)");
        } catch (IOException e) {
            System.out.println("Error: No such file exists: " + LIBRARY_JSON);
        }
        System.out.println("Library saved to " + LIBRARY_JSON + gson);
    }

    public static void saveAccount(ArrayList<Account> accounts){
        try (Writer writer = new FileWriter(ACCOUNTS_JSON)) {
            gson.toJson(accounts, writer);
            System.out.println("Accounts saved to " + ACCOUNTS_JSON + " (" + accounts.size() + " entries)");
        } catch (IOException e) {
            System.out.println("Error: No such file exists: " + ACCOUNTS_JSON);
        }
        System.out.println("Account saved to " + ACCOUNTS_JSON + gson);
    }
    public static void loadAccounts() {
        try (FileReader reader = new FileReader(ACCOUNTS_JSON)) {
            ArrayList<Account> accounts = gson.fromJson(reader, new TypeToken<ArrayList<Account>>() {}.getType());
            if(accounts != null){
                PrintLine("Loaded!");
                accountList.addAll(accounts);
            }
        } catch (IOException e) {
            System.out.println("No existing file found. Returning empty list.");
        }
    }
    public static void testAcc() {
        accountList.add(new Account("Ryan Gosling", AccessLevel.INTERMEDIATE, "123456"));
    }
    public static boolean isValidAcc(String id) {
        for (Account account : accountList) {
            if (id.equalsIgnoreCase(account.id()))
                return true;
        }
        return false;
    }
    public static boolean checkExistingName(String name) {
        for (Account account : accountList) {
            if (name.equalsIgnoreCase(account.name()))
                return true;
        }
        return false;
    }
    public static boolean checkAdminCode(String code) {
        return code.equalsIgnoreCase(admin.pin());
    }
    public static String[] getAccessLevels() {
        return accessLevels;
    }
    static String createAccount(String name, AccessLevel accessLevel) {
        String id;
        Random rand = new Random();
        while (true) {
            boolean valid = true;
            id = String.valueOf(rand.nextInt(100000, 1000000));
            for (Account account : accountList) {
                if (id.equalsIgnoreCase(account.id())) {
                    valid = false;
                    break;
                }
            }
            if (valid) {
                addAccount(new Account(name, accessLevel, id));
                Utils.PrintLine(id);
                return id;
            }
        }
    }
    public static void addAccount(Account account) {
        accountList.add(account);
        saveAccount(accountList);
        Utils.PrintLine("Made an account");
    }
    public static Account getAccount(String id) {
        for (Account account : accountList)
            if (id.equalsIgnoreCase(account.id()))
                return account;
        return null;
    }
    public static ArrayList<Book> getBookList(){
        return (ArrayList<Book>) bookList;
    }
    static boolean checkAccessLevel(Book book, AccessLevel lvl){
        return lvl.getAccessLevel() >= book.accessLevel().getAccessLevel();
    }
    static AccessLevel stringToAccess(String txt){
        return switch (txt) {
            case "Novice" -> AccessLevel.NOVICE;
            case "Intermediate" -> AccessLevel.INTERMEDIATE;
            case "Expert" -> AccessLevel.EXPERT;
            case "Masteral" -> AccessLevel.MASTERAL;
            case "Origin" -> AccessLevel.ORIGIN;
            default -> null;
        };
    }
}

enum Genre{
    ALL("Default"),
    GRIMOIRE("Grimoire"),
    OCCULT("Occult"),
    PROPHECY("Prophecies/Apocalyptic")
    ;
    private final String name;

    Genre(String name) {
        this.name = name;
    }
    @Override
    public String toString() {return name;}
}
enum Author{
    ALL("Default"),
    UNKNOWN("Unknown"),
    ABDUL_ALHAZRED("Abdul Alhazred"),
    SOLOMON_THE_WISE("Solomon The Wise"),
    NOSTRADAMUS("Nostradamus"),
    ENOCH("Enoch"),
    BARUCH("Baruch"),
    MASLAMA_AL_MAJRITI("Maslama al-Majriti"),
    PETER_OF_ABANO("Peter of Abano");

    private final String name;

    Author(String name){this.name = name;}
    @Override
    public String toString() {return name;}
}
enum AccessLevel{
    NOVICE("Novice", 0),
    INTERMEDIATE("Intermediate", 1),
    EXPERT("Experienced", 2),
    MASTERAL("Masteral", 3),
    ORIGIN("Origin", 4);

    private final String name;
    private final int lvl;

    AccessLevel(String name, int lvl){
        this.name = name;
        this.lvl = lvl;
    }
    @Override
    public String toString() {return name;}
    public int getAccessLevel() {
        return lvl;
    }
    public AccessLevel getAccessFromString(String string){
        if(string.equalsIgnoreCase(this.name)){
            return this;
        }
        return null;
    }
}

