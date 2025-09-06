import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.awt.*;
import java.util.ArrayList;

public class Utils {

    //GUI Tools
    public static JPanel newPanel(int width, int height, int x, int y, Color color, LayoutManager layout){

        JPanel panel = new JPanel();
        panel.setBounds(x, y, width, height);
        panel.setBackground(color);
        if(layout != null)
            panel.setLayout(layout);
        return panel;
    }
    @SuppressWarnings("unused")
    public static JPanel newPanel(Dimension dimension, Color color, LayoutManager layout){

        JPanel panel = new JPanel();
        panel.setPreferredSize(dimension);
        panel.setBackground(color);
        if(layout != null) panel.setLayout(layout);
        return panel;
    }
    public static Container newContainer(int width, int height, int x, int y, Color color, LayoutManager layout){

        Container con = new Container();
        con.setBounds(x, y, width, height);
        con.setBackground(color);
        if(layout != null)
            con.setLayout(layout);
        return  con;
    }
    @SuppressWarnings("unused")
    public static Container newContainer(Dimension dimension, Color color, LayoutManager layout){

        Container con = new Container();
        con.setSize(dimension);
        con.setBackground(color);
        if(layout != null)
            con.setLayout(layout);
        return  con;
    }
    public static JLabel newLabel(String txt, Font font, Color color){

        JLabel label = new JLabel();
        label.setText(txt);
        label.setFont(font);
        label.setForeground(color);
        return label;
    }
    public static JButton newButton(String txt, Color color, Font font){

        JButton button = new JButton();

        button.setText(txt);
        button.setFont(font);
        button.setForeground(color);
        button.setBackground(Color.WHITE);
        button.setFocusable(false);
        return button;
    }
    public static JButton newButton(String txt, int width, int height, Color color, Font font){

        JButton button = new JButton();

        button.setText(txt);
        button.setFont(font);
        button.setPreferredSize(new Dimension(width, height));
        button.setForeground(color);
        button.setBackground(Color.WHITE);
        button.setFocusable(false);
        return button;
    }
    public static JTextField newTextField(Dimension dim, Color color, Font font){

        JTextField textField = new JTextField();
        textField.setPreferredSize(dim);
        textField.setBackground(Color.WHITE);
        textField.setForeground(color);
        textField.setFont(font);
        return  textField;
    }
    public static <e> JComboBox<e> newComboBox(ComboBoxModel<e> dataModel, Font font){

        JComboBox<e> comboBox = new JComboBox<>(dataModel);
        comboBox.setBackground(Color.WHITE);
        comboBox.setForeground(Color.BLACK);
        comboBox.setFont(font);
        return comboBox;

    }
    public static <e> JList<e> newList(ListModel<e> listModel){

        return new JList<>(listModel);
    }
    public static String FormatName(String surname, String firstName){
        surname = surname.toLowerCase().trim();
        firstName = firstName.toLowerCase().trim();
        String[] firstNameSplits = firstName.split("\\s+");
        ArrayList<String> newFirstNameSplits = new ArrayList<>();
        for(String split: firstNameSplits){
            char newFirstLetter = Character.toUpperCase(split.charAt(0));
            newFirstNameSplits.add(split.replaceFirst("^.", Character.toString(newFirstLetter)));
        }
        StringBuilder firstNameBuild = new StringBuilder();
        for(String split: newFirstNameSplits){
            firstNameBuild.append(split).append(" ");
        }
        String finalFirstName = firstNameBuild.toString().trim();
        String finalSurname = surname.replaceFirst("^.", Character.toString(Character.toUpperCase(surname.charAt(0))));

        return finalFirstName + " " + finalSurname;
    }
    static NumericalField newNumField(Dimension dim){

        NumericalField numField = new NumericalField(1);

        numField.setPreferredSize(dim);
        numField.setBackground(Color.WHITE);
        numField.setForeground(Color.BLACK);
        return numField;
    }
    static NameField newNameField(Dimension dim, String regex){

        NameField nameField = new NameField(1, regex);
        nameField.setPreferredSize(dim);
        nameField.setBackground(Color.WHITE);
        nameField.setForeground(Color.BLACK);
        return nameField;
    }


    //Printing Debug Tools
    @SuppressWarnings("unused")
    public static void LineBreak(){
        System.out.println();
    }
    @SuppressWarnings("unused")
    public static void PrintLine(String txt){
        System.out.println((txt));
    }
    @SuppressWarnings("unused")
    public static void PrintFormat(String txt, Object... args){
        System.out.printf(txt, args);
    }
}


class NumericalField extends JTextField{
    private static final String DIGIT_5_REGEX = "^\\d{0,6}$";
    public NumericalField(int columns){
        super(columns);
        ((AbstractDocument) this.getDocument()).setDocumentFilter(new DocumentFilter(){
            @Override
            public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
                    throws BadLocationException {

                String newText = new StringBuilder(fb.getDocument().getText(0, fb.getDocument().getLength()))
                        .insert(offset, string)
                        .toString();

                if (newText.matches(DIGIT_5_REGEX)) { // allow only digits
                    super.insertString(fb, offset, string, attr);
                }
            }
            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
                    throws BadLocationException {
                String newText = new StringBuilder(fb.getDocument().getText(0, fb.getDocument().getLength()))
                        .replace(offset, offset + length, text == null ? "" : text)
                        .toString();
                if (newText.matches(DIGIT_5_REGEX)) { // allow only digits
                    super.replace(fb, offset, length, text, attrs);
                }
            }
        });
    }

}

class NameField extends JTextField{
    public NameField(int columns, String regex){
        super(columns);
        ((AbstractDocument) this.getDocument()).setDocumentFilter(new DocumentFilter(){
            @Override
            public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
                    throws BadLocationException {

                String newText = new StringBuilder(fb.getDocument().getText(0, fb.getDocument().getLength()))
                        .insert(offset, string)
                        .toString();

                if (newText.matches(regex)) {
                    super.insertString(fb, offset, string, attr);
                }
            }
            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
                    throws BadLocationException {
                String newText = new StringBuilder(fb.getDocument().getText(0, fb.getDocument().getLength()))
                        .replace(offset, offset + length, text == null ? "" : text)
                        .toString();
                if (newText.matches(regex)) {
                    assert text != null;
                    super.replace(fb, offset, length, text.toUpperCase(), attrs);
                }
            }
        });
    }
}
class ImagePanel extends JPanel {
    private final ImageIcon image;

    // Load image from resources (inside src/)
    public ImagePanel(String path) {
        java.net.URL url = getClass().getResource(path);
        System.out.println("Resource URL = " + url);
        assert url != null;
        image = new ImageIcon(url); // keep ImageIcon, not just Image
        System.out.println("Icon size: " + image.getIconWidth() + " x " + image.getIconHeight());
        setOpaque(true);
        setBackground(Color.YELLOW);
        setOpaque(true);
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw image stretched to panel size
        g.drawImage(image.getImage(), 0, 0, getWidth(), getHeight(), this);

    }
}