package editor;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class MenuCreator {
    public static JMenuBar createMenu(TextEditor txT) {
        String searchText = txT.getSearchString();
        //Boolean isregExpChk = txT.isRegExprChk();

        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        fileMenu.setName("MenuFile");
        fileMenu.setMnemonic(KeyEvent.VK_F);

        JMenuItem openMenu = new JMenuItem("Open");
        openMenu.setName("MenuOpen");

        JMenuItem saveMenu = new JMenuItem("Save");
        saveMenu.setName("MenuSave");

        JMenuItem exitMenu = new JMenuItem("Exit");
        exitMenu.setName("MenuExit");

        JMenu searchMenu = new JMenu("Search");
        searchMenu.setName("MenuSearch");

        JMenuItem startSearchMenu = new JMenuItem("Start search");
        startSearchMenu.setName("MenuStartSearch");

        JMenuItem previousSearchMenu = new JMenuItem("Previous search");
        previousSearchMenu.setName("MenuPreviousMatch");

        JMenuItem nextMatchMenu = new JMenuItem("Next match");
        nextMatchMenu.setName("MenuNextMatch");

        JMenuItem useRegExMenu = new JMenuItem("Use regular expressions");
        useRegExMenu.setName("MenuUseRegExp");

        openMenu.addActionListener(e -> ButtonAction.loadAction(txT));
        saveMenu.addActionListener(e -> ButtonAction.saveAction(txT));
        exitMenu.addActionListener(e -> System.exit(0));

        startSearchMenu.addActionListener(e -> SearchAction.search(txT));
        previousSearchMenu.addActionListener(e -> SearchAction.previous(txT));
        nextMatchMenu.addActionListener(e -> SearchAction.next(txT));
        useRegExMenu.addActionListener(e -> { txT.getRegchk().setSelected(!txT.getRegchk().isSelected());
            SearchAction.setIsRegex(txT.getRegchk().isSelected()); }); //Make checkbox selected

        fileMenu.add(openMenu);
        fileMenu.add(saveMenu);

        fileMenu.addSeparator();
        fileMenu.add(exitMenu);

        searchMenu.add(startSearchMenu);
        searchMenu.add(previousSearchMenu);
        searchMenu.add(nextMatchMenu);
        searchMenu.add(useRegExMenu);

        menuBar.add(fileMenu);
        menuBar.add(searchMenu);

        return menuBar;

    }
}
