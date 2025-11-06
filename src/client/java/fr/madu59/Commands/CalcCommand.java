package fr.madu59.Commands;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import fr.madu59.Utils.CommandUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraft.network.chat.ClickEvent;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.HoverEvent;
import net.minecraft.network.chat.Style;

public class CalcCommand {
    public static void Register(){
        CommandUtils.RegisterOneArg("calculate", CalcCommand::WriteCalcResult);
        CommandUtils.RegisterOneArg("calc", CalcCommand::WriteCalcResult);
    }

    public static void WriteCalcResult(String expression){
        Player player = Minecraft.getInstance().player;

        String copy = ParseAndCalc(expression);
        player.displayClientMessage(Component.literal(copy).withStyle(
                Style.EMPTY
                    .withHoverEvent(
                        new HoverEvent.ShowText(
                            Component.literal("Click to copy!")
                        )
                    )
                    .withClickEvent(
                        new ClickEvent.CopyToClipboard(
                            copy
                        )
                    )
            ), false);
    }

    public static String Calc(String input) {
        ScriptEngineManager mgr = new ScriptEngineManager();
        ScriptEngine engine = mgr.getEngineByName("JavaScript");
        try {
            Object output = engine.eval(input);
            return output.toString();
        } catch (Exception e) {
            return "Invalid expression";
        }
    }

    public static String ParseAndCalc(String input){
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
            return "Invalid expression";
        }
    }
}
