package editor;

import javax.swing.*;
import java.util.regex.Matcher;

public class NextFinder extends SwingWorker<Matcher, Object> {
    private final Matcher matcher;
    private final int last;
    NextFinder(Matcher matcher, int last) {
        this.matcher = matcher;
        this.last = last;
    }
    @Override
    protected Matcher doInBackground() {
        try {
            if (matcher.find(last)) {
                return matcher;
            } else {
                matcher.reset();
                matcher.find();
                return matcher;
            }
        } catch (Exception ignored) {
            return null;
        }
    }
    @Override
    protected void done() {
        try {
            Matcher matcher = get();
            SearchAction.setCaretPositionFunctionParallel(matcher);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
