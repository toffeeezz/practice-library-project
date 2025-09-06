public class Main {

    public static void main(String[] args) {
        Database.saveLibrary(new Library());
        Database.loadLibrary();
        Database.loadAccounts();
        UI.Window();
        UI.HomePage();
    }

}