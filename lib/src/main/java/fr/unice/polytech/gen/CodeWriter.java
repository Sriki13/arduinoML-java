package fr.unice.polytech.gen;

public class CodeWriter {

    private static final String INDENT = "    ";

    private int indentLevel = 0;
    private StringBuilder builder = new StringBuilder();

    public void indent() {
        indentLevel++;
    }

    public void unindent() {
        indentLevel--;
    }

    public void write(String str) {
        builder.append(str);
    }

    public void writeLn(String str) {
        startLn(str);
        builder.append("\n");
    }

    public void writeLn() {
        builder.append("\n");
    }

    public void startLn(String str) {
        for (int i = 0; i < indentLevel; i++) {
            builder.append(INDENT);
        }
        builder.append(str);
    }

    public String getCode() {
        return builder.toString();
    }

}
