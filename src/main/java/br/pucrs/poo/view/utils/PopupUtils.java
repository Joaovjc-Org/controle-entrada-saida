package br.pucrs.poo.view.utils;
import javax.swing.*;
public class PopupUtils {
    public static JScrollPane envolverEmCampoDeTexto(String s) {
        JTextArea ta = new JTextArea(10, 10);
        ta.setText(s);
        ta.setWrapStyleWord(true);
        ta.setLineWrap(true);
        ta.setCaretPosition(0);
        ta.setEditable(false);
        return new JScrollPane(ta);
    }
}
