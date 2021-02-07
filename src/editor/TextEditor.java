package editor;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.nio.file.Paths;

public class TextEditor extends JFrame {
    private final int BORDER = 25;
    private JTextArea textArea = new JTextArea();
    private JTextField searchField;
    private JCheckBox regchk;
    private JFileChooser jfc;
    private String fileName = "";

    public TextEditor() {
        super("Text Editor v1");
        //setTitle("Text Editor v1");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setLayout(new BorderLayout());
        setSize(300, 300);
        setLocationRelativeTo(null);

        setPreferredSize(new Dimension(600, 300));
        pack();

        initComponents();
        setJMenuBar(MenuCreator.createMenu(this));

        setVisible(true);
    }

    public JTextArea getTextAreaObject() {
        return this.textArea;
    }

    public void setFileName(String fName) {
        this.fileName = fName;
    }

    public String getFileName() {
        return fileName;
    }

    public String getSearchString() {
        return searchField.getText();
    }

    public JCheckBox getRegchk() {
        return regchk;
    }

    public JFileChooser getFileChooser() {
        return jfc;
    }

    private void initComponents() {
        JPanel cjp = new JPanel();
        cjp.setLayout(new BorderLayout());

        JPanel njp = new JPanel();
        //njp.setLayout(new BorderLayout());

        JPanel sjp = new JPanel();
        JPanel ejp = new JPanel();
        JPanel wjp = new JPanel();

        textArea.setName("TextArea");
        //textArea.setLineWrap(true);
        //textArea.setWrapStyleWord(true);
        //textArea.setFont(new Font("Dialog", Font.PLAIN, 14));
        //textArea.setTabSize(10);
        cjp.add(textArea);

        JScrollPane scrollableTextArea = new JScrollPane(cjp);
        scrollableTextArea.setName("ScrollPane");
        scrollableTextArea.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollableTextArea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        JButton sbt = new JButton(//"Save",
                createIcon(Paths.get("Text Editor", "task", "images", "save_icon.png").toString()));
        sbt.setName("SaveButton");

        JButton obt  = new JButton(//"Open",
                createIcon(Paths.get("Text Editor", "task", "images", "directory_icon.png").toString()));
        obt.setName("OpenButton");

        File current = new File(System.getProperty("user.dir"));
        jfc = new JFileChooser(current);
        jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        jfc.setName("FileChooser");
        //jfc.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Text files", "txt");
        jfc.addChoosableFileFilter(filter);

        searchField = new JTextField(15);
        searchField.setName("SearchField");

        JButton ssbt = new JButton(//"Search",
                createIcon(Paths.get("Text Editor", "task", "images", "search_icon.png").toString()));
        ssbt.setName("StartSearchButton");

        JButton pbt = new JButton(//"Previous",
                createIcon(Paths.get("Text Editor", "task", "images", "previous_icon.png").toString()));
        pbt.setName("PreviousMatchButton");

        JButton nbt = new JButton(//"Next",
                createIcon(Paths.get("Text Editor", "task", "images", "next_icon.png").toString()));
        nbt.setName("NextMatchButton");

        regchk = new JCheckBox("Use RegEx");
        regchk.setName("UseRegExCheckbox");

        njp.add(obt);
        njp.add(sbt);

        jfc.setVisible(false);
        njp.add(jfc); // For passing test only

        njp.add(searchField);
        njp.add(ssbt);
        njp.add(pbt);
        njp.add(nbt);
        njp.add(regchk);

        this.getContentPane().add(njp,BorderLayout.NORTH);
        this.getContentPane().add(sjp,BorderLayout.SOUTH);
        this.getContentPane().add(ejp,BorderLayout.EAST);
        this.getContentPane().add(wjp,BorderLayout.WEST);
        this.getContentPane().add(scrollableTextArea, BorderLayout.CENTER);

        obt.addActionListener(actionEvent -> ButtonAction.loadAction(this));
        sbt.addActionListener(actionEvent -> ButtonAction.saveAction(this));
        ssbt.addActionListener(e -> SearchAction.search(this));
        pbt.addActionListener(e -> SearchAction.previous(this));
        nbt.addActionListener(e -> SearchAction.next(this));
        regchk.addActionListener(e -> SearchAction.setIsRegex(regchk.isSelected()));
    }

    public static void forceSize(JComponent component, int width, int height) {
        Dimension d = new Dimension(width, height);
        component.setMinimumSize(d);
        component.setMaximumSize(d);
        component.setPreferredSize(d);
    }

    public static void setMargin(JComponent aComponent, int aTop,
                                 int aRight, int aBottom, int aLeft) {

        Border border = aComponent.getBorder();

        Border marginBorder = new EmptyBorder(new Insets(aTop, aLeft,
                aBottom, aRight));
        aComponent.setBorder(border == null ? marginBorder
                : new CompoundBorder(marginBorder, border));
    }

    public static ImageIcon createIcon(String path) {
        return new ImageIcon(new ImageIcon(path).getImage()
                .getScaledInstance(16, 16, Image.SCALE_SMOOTH));
    }

}
