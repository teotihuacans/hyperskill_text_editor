package editor;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

public class ButtonAction {
    public static void loadAction(TextEditor txT) {
        JTextArea textArea = txT.getTextAreaObject();
        String fName = "";
        JFileChooser jfc = txT.getFileChooser();
        jfc.setVisible(true);
        int returnValue = jfc.showOpenDialog(null);
        // int returnValue = jfc.showSaveDialog(null);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            fName = jfc.getSelectedFile().getAbsolutePath();
           //ApplicationRunner.tx.setFileName(fName);
            txT.setFileName(fName);
        }

        if (fName != null && fName.trim().length() > 0) {

            //String text = FileWorker.readFile(fName).reduce("", (sum, transaction) -> sum + transaction + "\n");
            //text = text.substring(0, text.length() - 1);

                /*Long m = Arrays.stream(FileWorker.readFile(fName).reduce("", (a, b) -> a + b).split("\\s"))
                                .mapToInt(Integer::parseInt).filter(e -> e > 9999).count();*/

                /*Integer m = FileWorker.readFile(fName).mapToInt(Integer::parseInt).sum();
                textArea.setText(m.toString());*/

           /* Map<String, Long> map = new TreeMap<>();
            FileWorker.readFile(fName).map(e -> e.split("\\s")).filter(e -> e[0].matches("\\d{4}"))
                    .forEach(e -> map.put(e[0], Long.valueOf(e[1].replaceAll(",", ""))));

            List<Long> lst = map.entrySet().stream().map(e -> e.getValue()).collect(Collectors.toList());
            Long mv = 0L;
            Long t = 0L;
            Long result = 0L;

            for(Long k : lst) {
                if (k - t > mv && t > 0L) { mv = k - t; result = k; }
                //System.out.println("mv = " + mv + "; k = " + k + "; t = " + t);
                t = k;
            }
            final Long kt = result;
            textArea.setText(new String("val = " + mv + "; " + result + "; year = " +
                    map.entrySet().stream().filter(e -> e.getValue() == kt).findFirst()));*/

                byte[] tmp = FileWorker.readByteFile(fName);
                if (tmp == null) {
                    textArea.setText(null);
                } else {
                    textArea.setText(new String(tmp));
                }
        }
        jfc.setVisible(false);
    }

    public static void saveAction(TextEditor txT) {
        JTextArea textArea = txT.getTextAreaObject();
        //String fName = ApplicationRunner.tx.getFileName();
        JFileChooser jfc = txT.getFileChooser();
        jfc.setVisible(true);
        int returnValue = jfc.showSaveDialog(null);

        String fName = txT.getFileName();
        //jfc.setSelectedFile(new File(fName)); //Commented For testing purpose

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            fName = jfc.getSelectedFile().getAbsolutePath();
            txT.setFileName(fName);
        }

        if (fName != null && fName.trim().length() > 0) {
            FileWorker.saveFile(fName, textArea.getText());
        }

        jfc.setVisible(false);
    }
}
