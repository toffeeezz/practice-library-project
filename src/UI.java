import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class UI extends Utils{

    static JFrame frame;
    static Container con;
    static Font buttonFonts = new Font("Times New Roman", Font.PLAIN, 30);
    static Font instrucFonts = new Font("Times New Roman", Font.PLAIN, 25);
    static LineBorder lineBorder = new LineBorder(Color.BLACK, 3, true);

    static String fullName;
    static String surname;
    static String firstName;

    static String accessLevel;
    static String id;

    static InputStream f1 = UI.class.getResourceAsStream("/resources/fonts/CinzelDecorative-Bold.otf");
    static InputStream f2 = UI.class.getResourceAsStream("/resources/fonts/Cinzel-Regular.otf");
    static InputStream f3 = UI.class.getResourceAsStream("/resources/fonts/Bloody Hell.otf");
    static InputStream f4 = UI.class.getResourceAsStream("/resources/fonts/RomanAntique-Italic.ttf");
    static InputStream f5 = UI.class.getResourceAsStream("/resources/fonts/RomanAntique.ttf");


    static Font CinzelDecoBold;
    static Font CinzelReg;
    static Font BloodyHell;
    static Font RomanAntiqueItalic;
    static Font RomanAntique;

    static {
        assert f1 != null;
        assert f2 != null;
        assert f3 != null;
        assert f4 != null;
        assert f5 != null;
        try {
            CinzelReg = Font.createFont(Font.TRUETYPE_FONT, f2);
            CinzelDecoBold = Font.createFont(Font.TRUETYPE_FONT, f1);
            BloodyHell = Font.createFont(Font.TRUETYPE_FONT, f3);
            RomanAntiqueItalic = Font.createFont(Font.TRUETYPE_FONT, f4);
            RomanAntique = Font.createFont(Font.TRUETYPE_FONT, f5);
        } catch (FontFormatException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    static GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();

    static Account account;

    public static void Window(){

        ge.registerFont(RomanAntiqueItalic);
        ge.registerFont(RomanAntique);


        frame = new JFrame();

        frame.setTitle("Akashic Records");
        frame.setSize(new Dimension(500,500));
        frame.setResizable(false);
        frame.setBackground(Color.WHITE);
        frame.setLocationRelativeTo(null);
        frame.setFocusable(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.getContentPane().setBackground(Color.WHITE);

        con = newContainer(500,500,0,0, Color.WHITE, null);

        frame.add(con);

    }

    public static void HomePage(){
        con.removeAll();

        //Title GUI
        JPanel titleP = newPanel(500,100,-3,50, Color.WHITE, new BorderLayout());
        titleP.setBorder(lineBorder);

        JLabel titleLabel = newLabel("Akashic Records", new Font("Times New Roman", Font.PLAIN, 45), Color.BLACK);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        titleP.add(titleLabel);
        con.add(titleP);

        //Selection GUI
        JPanel buttonPanels = newPanel(200,225,150,175, Color.WHITE, new FlowLayout(FlowLayout.CENTER, 0, 20));

        JButton logIn = newButton("Log In",150,50, Color.BLACK, buttonFonts);
        JButton signUp = newButton("Sign Up", Color.BLACK, buttonFonts);
        JButton exit = newButton("Exit", Color.BLACK, buttonFonts);
        logIn.addActionListener(_ -> LogInPage());
        signUp.addActionListener(_ -> SignUpPage());
        exit.addActionListener(_ -> System.exit(0));
        JButton[] buttons = {logIn, signUp, exit};

        for(JButton button: buttons) {
            buttonPanels.add(button);
        }
        con.add(buttonPanels);

        con.revalidate();
        con.repaint();

    }
    static void LogInPage(){
        con.removeAll();

        //Back button GUI
        JPanel backP = newPanel(70,30,20,20, Color.GREEN, new BorderLayout());
        JButton backButton = newButton("Back", Color.BLACK, new Font("Times New Roman", Font.PLAIN, 15));
        backButton.addActionListener(_ -> HomePage());

        backP.add(backButton);
        con.add(backP);

        //Instruction GUI
        JPanel instructionP = newPanel(300, 90, 100, 90, Color.WHITE, new BorderLayout());

        JLabel label = newLabel("Enter your six (6) digit ID", instrucFonts , Color.BLACK);
        label.setHorizontalAlignment(SwingConstants.CENTER);

        instructionP.add(label);
        con.add(instructionP);

        //Input GUI
        JPanel inputP = newPanel(200, 50, 150,170, Color.GREEN, new BorderLayout());
        NumericalField inputField = newNumField(new Dimension(180, 40));
        inputField.setFont(new Font("Times New Roman", Font.BOLD, 20));
        inputField.setHorizontalAlignment(SwingConstants.CENTER);

        inputP.add(inputField);
        con.add(inputP);

        //Response Status GUI
        JPanel statusP = newPanel(200, 30, 150, 290, Color.WHITE, new BorderLayout());
        JLabel statusLabel = newLabel("", new Font("Times New Roman", Font.BOLD, 10), Color.RED);
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);

        statusP.add(statusLabel);
        con.add(statusP);

        //Submit GUI
        JPanel submitP = newPanel(100, 40, 200, 240, Color.RED, new BorderLayout());
        JButton submitButton = newButton("Enter", Color.BLACK, new Font("Times New Roman", Font.PLAIN, 25));
        submitButton.addActionListener(_ -> {
            statusLabel.setText("");
           if(Database.isValidAcc(inputField.getText())){
               PrintLine("Found Acc");
               LibraryPage(inputField.getText());
            }else if(inputField.getText().length() != 6){
               statusLabel.setText("ERROR: INCORRECT NUMBER OF DIGITS");
           }else{
               statusLabel.setText("ERROR: ACCOUNT DOES NOT EXIST");
           }

        });

        submitP.add(submitButton);
        con.add(submitP);

        con.revalidate();
        con.repaint();
    }
    static void SignUpPage(){
        con.removeAll();

        //Surname Input GUI
        JPanel surnameLabelP = newPanel(160, 30, 50, 80, Color.WHITE, new BorderLayout());
        JLabel surnameLabel = newLabel("Last Name:", new Font("Times New Roman", Font.PLAIN, 14), Color.BLACK);
        surnameLabel.setHorizontalAlignment(SwingConstants.LEFT);

        surnameLabelP.add(surnameLabel);
        con.add(surnameLabelP);

        JPanel surnameInputP = newPanel(160, 23, 50, 110, Color.BLACK, new BorderLayout());
        NameField surnameInput = newNameField(new Dimension(180, 23), "^[A-Za-z]{0,10}$");
        surnameInput.setColumns(1);
        surnameInput.setFont(new Font("Times New Roman", Font.PLAIN, 14));

        surnameInputP.add(surnameInput);
        con.add(surnameInputP);

        //Name Input GUI
        JPanel nameLabelP = newPanel(160, 30, 230, 80, Color.WHITE, new BorderLayout());
        JLabel nameLabel = newLabel("First Name:", new Font("Times New Roman", Font.PLAIN, 14), Color.BLACK);

        nameLabelP.add(nameLabel);
        con.add(nameLabelP);

        JPanel nameInputP = newPanel(220, 23, 230, 110, Color.BLACK, new BorderLayout());
        NameField nameInput = newNameField(new Dimension(220, 23), "^[A-Za-z ]{0,25}$");
        nameInput.setColumns(1);
        nameInput.setFont(new Font("Times New Roman", Font.PLAIN, 14));

        nameInputP.add(nameInput);
        con.add(nameInputP);

        //Library Access Level GUI
        JPanel accessLabelP = newPanel(160, 30, 50, 150, Color.WHITE, new BorderLayout());
        JLabel accessLabel = newLabel("Access Level", new Font("Times New Roman", Font.PLAIN, 14), Color.BLACK);

        accessLabelP.add(accessLabel);
        con.add(accessLabelP);

        JPanel accessDropP = newPanel(160, 30, 50, 180, Color.RED, new BorderLayout());
        JComboBox<AccessLevel> accessDrop = newComboBox(new DefaultComboBoxModel<>(Database.getAccessLevels()), new Font("Times New Roman", Font.PLAIN, 14));
        accessDrop.setAlignmentY(Component.CENTER_ALIGNMENT);
        accessDrop.setFocusable(false);

        accessDropP.add(accessDrop);
        con.add(accessDropP);

        //Error GUi
        JPanel errorP = newPanel(250, 20, 125, 280, Color.WHITE, new BorderLayout());
        JLabel errorLabel = newLabel("", new Font("Times New Roman", Font.BOLD, 9), Color.RED);
        errorLabel.setHorizontalAlignment(SwingConstants.CENTER);

        errorP.add(errorLabel);
        con.add(errorP);

        //Save values for user back tracking
        surnameInput.setText(surname);
        nameInput.setText(firstName);
        if(accessLevel == null){
            accessDrop.setSelectedIndex(0);
        }else{
            accessDrop.setSelectedItem(accessLevel);
        }


        //Submit GUi
        JPanel submitP = newPanel(100, 30, 200,250, Color.RED, new BorderLayout());
        JButton submitButton = newButton("Register", Color.BLACK, new Font("Times New Roman", Font.PLAIN, 17));
        submitButton.setHorizontalAlignment(SwingConstants.CENTER);
        submitButton.addActionListener(_ -> {
            surname = surnameInput.getText();
            firstName = nameInput.getText();
            accessLevel = Objects.requireNonNull(accessDrop.getSelectedItem()).toString();
            fullName = FormatName(surname, firstName);
            errorLabel.setForeground(Color.RED);

            if(!surname.equalsIgnoreCase("") && !firstName.equalsIgnoreCase("") && !Database.checkExistingName(fullName)){

                errorLabel.setForeground(Color.GREEN);
                switch (accessLevel){
                    case "Novice", "Intermediate":
                        id = Database.createAccount(fullName, Database.stringToAccess(accessLevel));
                        errorLabel.setText("ACCOUNT SUCCESSFULLY CREATED: " + id);
                        break;
                    default:
                        adminRequestPage();
               }
            }else if(Database.checkExistingName(fullName)){
                errorLabel.setText("ERROR: ACCOUNT ALREADY EXISTS");
            }else{
                errorLabel.setText("ERROR: PLEASE FILL UP ALL REQUIRED FIELDS");
            }
        });
        submitP.add(submitButton);
        con.add(submitP);

        //Back button GUI
        JPanel backP = newPanel(70,30,20,20, Color.GREEN, new BorderLayout());
        JButton backButton = newButton("Back", Color.BLACK, new Font("Times New Roman", Font.PLAIN, 15));
        backButton.addActionListener(_ -> {
        HomePage();
        surnameInput.setText("");
        nameInput.setText("");
        accessDrop.setSelectedIndex(0);
        });

        backP.add(backButton);
        con.add(backP);

        con.revalidate();
        con.repaint();
    }

    static void adminRequestPage(){
        con.removeAll();

        //Back button GUI
        JPanel backP = newPanel(70,30,20,20, Color.GREEN, new BorderLayout());
        JButton backButton = newButton("Back", Color.BLACK, new Font("Times New Roman", Font.PLAIN, 15));
        backButton.addActionListener(_ -> SignUpPage());

        backP.add(backButton);
        con.add(backP);

        //Instruction GUI
        JPanel instrucP = newPanel(390, 20, 55, 130, Color.WHITE, new BorderLayout());
        JLabel instrucLabel = newLabel("Admin PIN is required for the selected access level", new Font("Times new Roman", Font.PLAIN, 18), Color.BLACK);
        instrucLabel.setHorizontalAlignment(SwingConstants.CENTER);

        instrucP.add(instrucLabel);
        con.add(instrucP);

        //Input Admin Code GUI
        JPanel inputP = newPanel(200, 40, 150,170, Color.GREEN, new BorderLayout());
        NumericalField inputField = newNumField(new Dimension(180, 40));
        inputField.setFont(new Font("Times New Roman", Font.BOLD, 20));
        inputField.setHorizontalAlignment(SwingConstants.CENTER);

        inputP.add(inputField);
        con.add(inputP);

        //Response Status GUI
        JPanel statusP = newPanel(250, 30, 125, 270, Color.WHITE, new BorderLayout());
        JLabel statusLabel = newLabel("", new Font("Times New Roman", Font.BOLD, 10), Color.RED);
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);

        statusP.add(statusLabel);
        con.add(statusP);

        //Submit GUI
        JPanel submitP = newPanel(100, 30, 200, 230, Color.RED, new BorderLayout());
        JButton submitButton = newButton("Enter", Color.BLACK, new Font("Times New Roman", Font.PLAIN, 17));
        submitButton.addActionListener(_ -> {
            statusLabel.setForeground(Color.RED);
            statusLabel.setText("");
            if(Database.checkAdminCode(inputField.getText()) && !Database.checkExistingName(fullName)){
                statusLabel.setForeground(Color.GREEN);
                id = Database.createAccount(fullName, Database.stringToAccess(accessLevel));
                statusLabel.setText("ACCOUNT SUCCESSFULLY REGISTERED: " + id);
            }else if(Database.checkExistingName(fullName)){
                statusLabel.setText("ERROR: ACCOUNT ALREADY EXISTS");
            }else if(inputField.getText().length() != 6){
                statusLabel.setText("ERROR: INCORRECT NUMBER OF DIGITS");
            }else{
                statusLabel.setText("ERROR: WRONG CODE");
            }

        });

        submitP.add(submitButton);
        con.add(submitP);



        con.revalidate();
        con.repaint();
    }

    static void LibraryPage(String id){
        con.removeAll();

        account = Database.getAccount(id);
        assert account != null;
        fullName = account.name();
        Utils.PrintLine(fullName);

        //Back button GUI
        JPanel backP = newPanel(70,30,20,20, Color.GREEN, new BorderLayout());
        JButton backButton = newButton("Exit", Color.BLACK, new Font("Times New Roman", Font.PLAIN, 15));
        backButton.addActionListener(_ -> HomePage());

        backP.add(backButton);
        con.add(backP);

        //Header GUI
        JPanel headerP = newPanel(250, 40, 125, 30, Color.WHITE, new BorderLayout());
        JLabel headerLabel = newLabel("Lost Archives", CinzelDecoBold.deriveFont(30f), Color.BLACK);
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);

        headerP.add(headerLabel);
        con.add(headerP);

        //Filter GUI
        JPanel filterMainP = newPanel(450, 70, 25, 120, Color.WHITE, new FlowLayout(FlowLayout.LEADING));
        ArrayList<JPanel> holderP = new ArrayList<>();

            //GENRES
        JPanel genrePanel = newPanel(new Dimension(300, 22), Color.WHITE, null);
        genrePanel.setLayout(new BoxLayout(genrePanel, BoxLayout.X_AXIS));
        holderP.add(genrePanel);
        JLabel genreLabel = newLabel("Genre:", CinzelDecoBold.deriveFont(13f), Color.BLACK);
        genrePanel.add(genreLabel);
        genrePanel.add(Box.createHorizontalGlue());
        genrePanel.setBorder(new LineBorder(Color.WHITE, 2));
        JComboBox<Genre> genreSelection = newComboBox(new DefaultComboBoxModel<>(Genre.values()), CinzelDecoBold.deriveFont(11f));

        genreSelection.setFocusable(false);
        genrePanel.add(genreSelection);

        JPanel authorP = newPanel(new Dimension(300, 22), Color.WHITE, null);
        authorP.setLayout(new BoxLayout(authorP, BoxLayout.X_AXIS));
        holderP.add(authorP);
        JLabel authorLabel = newLabel("Author:", CinzelDecoBold.deriveFont(13f), Color.BLACK);
        authorP.add(authorLabel);
        authorP.add(Box.createHorizontalGlue());
        authorP.setBorder(new LineBorder(Color.WHITE, 2));

        JComboBox<Author> authorSelection = newComboBox(new DefaultComboBoxModel<>(Author.values()), CinzelDecoBold.deriveFont(11f));

        authorSelection.setFocusable(false);
        authorP.add(authorSelection);


        for(JPanel panel: holderP){
            filterMainP.add(panel);
        }
        con.add(filterMainP);


        //Booklist GUI
        JPanel mainPanel = newPanel(450, 250, 25, 200, Color.YELLOW, new BorderLayout());

        ArrayList<Book> books = Database.getBookList();
        DefaultListModel<Book> bookModel = new DefaultListModel<>();

        bookModel.clear();
        bookModel.clear();
        for(Book b: books){
            if(bookModel.contains(b)){
                continue;
            }
            if(Objects.equals(genreSelection.getSelectedItem(), Genre.ALL) && Objects.equals(authorSelection.getSelectedItem(), Author.ALL)){
                bookModel.addElement(b);
            }else if(Objects.equals(genreSelection.getSelectedItem(), Genre.ALL) && b.author().equals(authorSelection.getSelectedItem())){
                bookModel.addElement(b);
            }else if(b.genre().equals(genreSelection.getSelectedItem()) && Objects.equals(authorSelection.getSelectedItem(), Author.ALL)){
                bookModel.addElement(b);
            }else if(b.genre().equals(genreSelection.getSelectedItem()) && b.author().equals(authorSelection.getSelectedItem())){
                bookModel.addElement(b);
            }
        }
        genreSelection.addActionListener(new filterAction(books, bookModel, genreSelection, authorSelection));
        authorSelection.addActionListener(new filterAction(books, bookModel, genreSelection, authorSelection));
        JList<Book> bookList = newList(bookModel);
        bookList.setFont(CinzelReg.deriveFont(14f));
        bookList.setFixedCellHeight(30);
        JScrollPane scollP = new JScrollPane(bookList);

        bookList.setCellRenderer((list, book, _, isSelected, _) -> {

            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
            panel.setOpaque(true);
            panel.setBackground(isSelected ? list.getSelectionBackground() : list.getBackground());

            JLabel titleL = new JLabel(book.title());
            titleL.setFont(CinzelDecoBold.deriveFont(14f));
            titleL.setHorizontalAlignment(SwingConstants.LEFT);

            JLabel authorL = new JLabel(" by " + book.author());
            authorL.setFont(CinzelReg.deriveFont(12f));
            authorL.setHorizontalAlignment(SwingConstants.RIGHT);

            panel.add(titleL);
            panel.add(Box.createHorizontalGlue());
            panel.setBorder(new LineBorder(Color.WHITE,5));
            panel.add(authorL);

            return panel;
        });

        bookList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int i =  bookList.locationToIndex(e.getPoint());
                bookList.setSelectedIndex(i);
                Book selectedBook = bookList.getSelectedValue();
                if(selectedBook.accessLevel().getAccessLevel() <= account.accessLevel().getAccessLevel()){
                    previewBook(selectedBook);
                }else {
                    restrictedPopUp(selectedBook.accessLevel());
                }
            }
        });

        mainPanel.add(scollP);
        con.add(mainPanel);

        con.revalidate();
        con.repaint();
    }
    static void restrictedPopUp(AccessLevel level){
        JDialog restrictedPopUp = new JDialog(frame, "LOW ACCESS DETECTED", true);
        restrictedPopUp.setLayout(new BorderLayout());
        restrictedPopUp.setResizable(false);
        restrictedPopUp.setUndecorated(true);
        restrictedPopUp.setBackground(Color.WHITE);

        JLabel warningLabel = newLabel("REQUIRED ACCESS: " + level.toString().toUpperCase(),CinzelReg.deriveFont(14f), Color.RED);
        warningLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel labelP = newPanel(new Dimension(300, 200), Color.WHITE, new FlowLayout(FlowLayout.CENTER));
        labelP.setOpaque(false);
        labelP.setPreferredSize(new Dimension(warningLabel.getPreferredSize().width + 40,warningLabel.getPreferredSize().height + 10));

        JButton confirmButton = newButton("OK", Color.BLACK, CinzelDecoBold.deriveFont(11f));
        confirmButton.setFocusPainted(false);
        confirmButton.setHorizontalAlignment(SwingConstants.CENTER);
        confirmButton.addActionListener(_-> restrictedPopUp.dispose());

        JPanel buttonP = newPanel(new Dimension(300,200), Color.white, new FlowLayout(FlowLayout.CENTER));
        buttonP.setOpaque(false);
        buttonP.setPreferredSize(new Dimension(confirmButton.getPreferredSize().width + 10,confirmButton.getPreferredSize().height + 10));


        buttonP.add(confirmButton);
        labelP.add(warningLabel);
        restrictedPopUp.add(labelP,BorderLayout.NORTH);
        restrictedPopUp.add(buttonP, BorderLayout.SOUTH);
        restrictedPopUp.pack();
        restrictedPopUp.setLocationRelativeTo(frame);
        restrictedPopUp.setVisible(true);
    }
    static void previewBook(Book book) {

        JDialog popUp = new JDialog(frame, book.title(), true);
        popUp.setSize(450, 450);
        popUp.setLocationRelativeTo(frame);
        popUp.setResizable(false);
        popUp.setLayout(new BorderLayout()); // use BorderLayout instead of null


        //MAIN PANEL
        ImagePanel mainP = new ImagePanel("/resources/textures/bookDisplays/bookBG.jpeg");
        //JPanel mainP = newPanel(450, 450, 0, 0, Color.RED, null);
        mainP.setLayout(new BoxLayout(mainP, BoxLayout.Y_AXIS));

        //IMAGE DISPLAY
        ImageIcon bookDisplay = new ImageIcon(Objects.requireNonNull(UI.class.getResource(book.imageFile())));
        Image formatted = bookDisplay.getImage().getScaledInstance(180, 270, Image.SCALE_AREA_AVERAGING);
        bookDisplay.setImage(formatted);

        JLabel pic = new JLabel(bookDisplay);

        JPanel imageP = newPanel(0, 0, 0, 0, Color.ORANGE, new BorderLayout());
        imageP.setBorder(new LineBorder(Color.BLACK, 3, true));
        imageP.setPreferredSize(new Dimension(pic.getPreferredSize().width + 5, pic.getPreferredSize().height + 5));
        imageP.add(pic);

        JPanel mainImageP = newPanel(new Dimension(0, 0), Color.RED, null);
        mainImageP.setAlignmentX(Component.CENTER_ALIGNMENT);
        int imageHeight = imageP.getPreferredSize().height + 10;
        mainImageP.setPreferredSize(new Dimension(450, imageHeight));
        mainImageP.setMaximumSize(new Dimension(450, imageHeight));
        mainImageP.add(imageP);


        //TITLE
        JPanel maineTitleP = newPanel(new Dimension(330, 70), Color.BLUE, null);
        maineTitleP.setMaximumSize(new Dimension(450, 70));
        maineTitleP.setAlignmentX(Component.CENTER_ALIGNMENT);
        JPanel titleP = newPanel(330, 60, 0,0, Color.GREEN, new BorderLayout());
        JLabel title = newLabel(book.title(), BloodyHell.deriveFont(40f), Color.BLACK);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        titleP.add(title);
        maineTitleP.add(titleP);

        //DESCRIPTION
        JPanel mainDescP = newPanel(new Dimension(450, 0), Color.MAGENTA, null);
        JPanel descP = newPanel(350, 350, 0, 0, Color.CYAN, new BorderLayout());
        descP.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextPane desc = new JTextPane();
        desc.setOpaque(false);
        desc.setForeground(Color.BLACK);
        desc.setFocusable(false);
        desc.setEditable(false);
        desc.setBackground(Color.WHITE);

        StyledDocument doc = desc.getStyledDocument();
        SimpleAttributeSet alignCenter = new SimpleAttributeSet();
        StyleConstants.setFontFamily(alignCenter, RomanAntiqueItalic.getFamily());
        StyleConstants.setAlignment(alignCenter, StyleConstants.ALIGN_CENTER);
        StyleConstants.setFontSize(alignCenter, 25);

        try {
            doc.insertString(doc.getLength(), book.summary(), alignCenter);
            doc.setParagraphAttributes(0, doc.getLength(), alignCenter, false);
        } catch (BadLocationException e) {
            throw new RuntimeException(e);
        }

        PrintLine(RomanAntique.getFamily());

        desc.setSize(descP.getWidth(), Short.MAX_VALUE);
        int textHeight = desc.getPreferredSize().height;
        desc.setPreferredSize(new Dimension(descP.getWidth(), textHeight));

        mainDescP.setPreferredSize(new Dimension(450, desc.getPreferredSize().height + 70));
        mainDescP.setMaximumSize(new Dimension(450, desc.getPreferredSize().height + 70));

        desc.revalidate();
        desc.repaint();
        desc.revalidate();

        descP.add(desc, BorderLayout.CENTER);
        mainDescP.add(descP);

        //Make bg transparent
        maineTitleP.setOpaque(false);
        mainImageP.setOpaque(false);
        mainDescP.setOpaque(false);
        imageP.setOpaque(false);
        titleP.setOpaque(false);
        descP.setOpaque(false);

        //ADD PANES IN ORDER
        mainP.add(maineTitleP);
        mainP.add(mainImageP);
        mainP.add(mainDescP);


        JScrollPane scrollPane = getJScrollPane(mainP);

        scrollPane.addMouseWheelListener(new MouseAdapter() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                int scroll = e.getUnitsToScroll() * 10;
                scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getValue() + scroll);
            }
        });

        popUp.add(scrollPane, BorderLayout.CENTER);

        popUp.setVisible(true);
        popUp.revalidate();
        popUp.repaint();

        PrintLine(book.title());
    }

    private static @NotNull JScrollPane getJScrollPane(JPanel mainP) {
        JScrollPane scrollPane = new JScrollPane(mainP);
        JScrollBar customScrollBar = new JScrollBar();
        customScrollBar.setBackground(Color.WHITE);

        SwingUtilities.invokeLater(()-> customScrollBar.setValue(0));

        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBar(customScrollBar);
        return scrollPane;
    }


}

record filterAction(ArrayList<Book> books, DefaultListModel<Book> bookModel, JComboBox<Genre> genreSelection,
                    JComboBox<Author> authorSelection) implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        bookModel.clear();
        for (Book b : books) {
            if (bookModel.contains(b)) {
                continue;
            }
            if (Objects.equals(genreSelection.getSelectedItem(), Genre.ALL) && Objects.equals(authorSelection.getSelectedItem(), Author.ALL)) {
                bookModel.addElement(b);
            } else if (Objects.equals(genreSelection.getSelectedItem(), Genre.ALL) && b.author().equals(authorSelection.getSelectedItem())) {
                bookModel.addElement(b);
            } else if (b.genre().equals(genreSelection.getSelectedItem()) && Objects.equals(authorSelection.getSelectedItem(), Author.ALL)) {
                bookModel.addElement(b);
            } else if (b.genre().equals(genreSelection.getSelectedItem()) && b.author().equals(authorSelection.getSelectedItem())) {
                bookModel.addElement(b);
            }

        }
    }
}
