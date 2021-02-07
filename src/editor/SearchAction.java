package editor;

import javax.swing.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SearchAction {
    private static JTextArea textArea;
    private static int index = -1;
    private static int last = 0;
    private static boolean isRegex = false;
    private static Matcher matcher;
    private static Pattern pattern;

    public static void search(TextEditor txT) {
        textArea = txT.getTextAreaObject();
        String foundText = txT.getSearchString();
        last = 0;

        pattern = Pattern.compile(foundText);
        matcher = pattern.matcher(textArea.getText());

        //index = textArea.getText().indexOf(foundText);
        index = findRegex();

        setCaretPositionFunction();
    }

    public static void next(TextEditor txT) {
        if (index >= 0) {
            matcher = pattern.matcher(textArea.getText());
            new NextFinder(matcher, last).execute();
        } else {
            search(txT);
        }
    }

    public static void previous(TextEditor txT) {
        if (index >= 0) {
            matcher = pattern.matcher(textArea.getText());
            new PreviousFinder(matcher, index).execute();

        } else {
            search(txT);
        }
    }

    public static void setCaretPositionFunction() {
        if (index >= 0) {
            //textArea.setCaretPosition(index + foundText.length());
            //textArea.select(index, index + foundText.length());
            textArea.setCaretPosition(last);
            textArea.select(index, last);
            textArea.grabFocus();
        }
    }

    public static void setCaretPositionFunctionParallel(Matcher matcher) {
        if (matcher != null && matcher.start() >= 0) {
            index = matcher.start();
            last = matcher.end();
            textArea.setCaretPosition(last);
            textArea.select(index, last);
            textArea.grabFocus();
        }
    }

    public static void setIsRegex(boolean isRegexChecked) {
        isRegex = isRegexChecked;
        if (textArea != null) {
            textArea.setCaretPosition(textArea.getTabSize());
        }
    }

    public static int findRegex() {
        int findInd = -1;

        if (matcher.find(last)) {
            findInd = matcher.start();
            last = matcher.end();
            //foundText = matcher.group();
        }
        return findInd;
    }

}


