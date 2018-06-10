package sample.Model;

import javafx.scene.control.TextArea;

public class ConsoleArea {

    private TextArea consoleTextArea;

    public ConsoleArea() {
    }

    public ConsoleArea(TextArea consoleTextArea) {
        this.consoleTextArea = consoleTextArea;
    }

    public void setConsoleTextArea(TextArea consoleTextArea) {
        this.consoleTextArea = consoleTextArea;
    }

    public TextArea getConsoleTextArea() {
        return consoleTextArea;
    }

    public void printToConsole(String stringToPrint){
        this.consoleTextArea.appendText("\n" + stringToPrint);
    }
}
