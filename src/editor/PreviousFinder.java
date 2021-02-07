package editor;

import javax.swing.*;
import java.util.regex.Matcher;

public class PreviousFinder extends SwingWorker<Matcher, Object> {
    private final Matcher matcher;
    private final int index;
    PreviousFinder(Matcher matcher, int index) {
        this.matcher = matcher;
        this.index = index;
    }
    @Override
    protected Matcher doInBackground() {
        int ind = -1;
        try {
                Matcher matcher2 = matcher.region(0, index);
                while (matcher2.find()) {
                    ind = matcher2.start();
                }

                if (ind == -1) {
                    matcher.reset();
                    while (matcher.find()) {
                        ind = matcher.start();
                    }
                }

                matcher.reset();
                if (matcher.region(ind, matcher.regionEnd()).find()) {
                    ind = matcher.start();
                }
                return matcher;
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
