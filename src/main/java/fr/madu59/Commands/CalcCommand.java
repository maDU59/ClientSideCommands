package fr.madu59.Commands;

import fr.madu59.Utils.CommandUtils;
import net.minecraft.network.chat.Component;
import net.minecraftforge.client.event.RegisterClientCommandsEvent;

public class CalcCommand {
    public static void register(RegisterClientCommandsEvent event){
        CommandUtils.registerOneArg(event, "calculate", CalcCommand::writeCalcResult);
        CommandUtils.registerOneArg(event, "calc", CalcCommand::writeCalcResult);
    }

    public static void writeCalcResult(String expression){

        String copy = parseAndCalc(expression);
        CommandUtils.feedbackMessage(Component.literal(copy), copy);
    }

    public static String parseAndCalc(String input){
        try {
            double result = new Object() {
                int pos = -1, ch;
                void nextChar() { 
                    ch = (++pos < input.length()) ? input.charAt(pos) : -1; 
                }

                boolean eat(int charToEat) {
                    while (ch == ' ') nextChar();
                    if (ch == charToEat) { nextChar(); return true; }
                    return false;
                }

                double parse() {
                    nextChar();
                    double x = parseExpression();
                    if (pos < input.length()) throw new RuntimeException("Unexpected: " + (char)ch);
                    return x;
                }

                double parseExpression() {
                    double x = parseTerm();
                    for (;;) {
                        if      (eat('+')) x += parseTerm(); // addition
                        else if (eat('-')) x -= parseTerm(); // subtraction
                        else return x;
                    }
                }

                double parseTerm() {
                    double x = parseFactor();
                    for (;;) {
                        if      (eat('*')) x *= parseFactor(); // multiplication
                        else if (eat('/')) x /= parseFactor(); // division
                        else return x;
                    }
                }

                double parseFactor() {
                    double x = parseUnary();
                    for (;;) {
                        if (eat('^')) x = Math.pow(x, parseUnary());
                        else return x;
                    }
                }

                double parseUnary() {
                    if (eat('+')) return parseUnary(); // unary plus
                    if (eat('-')) return -parseUnary(); // unary minus

                    double x;
                    int startPos = this.pos;
                    if (eat('(')) { // parentheses
                        x = parseExpression();
                        eat(')');
                    } else if ((ch >= '0' && ch <= '9') || ch == '.') {
                        while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
                        x = Double.parseDouble(input.substring(startPos, this.pos));
                    } else {
                        throw new RuntimeException("Unexpected: " + (char)ch);
                    }

                    return x;
                }
            }.parse();

            return String.valueOf(result);
        } catch (Exception e) {
            return "invalid-expression";
        }
    }
}
