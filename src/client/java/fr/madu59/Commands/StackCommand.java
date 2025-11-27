package fr.madu59.Commands;

import fr.madu59.Utils.CommandUtils;
import net.minecraft.network.chat.Component;

public class StackCommand {
    public static void register(){
        CommandUtils.registerTwoArgsInt("stacks", StackCommand::calculateStacks);
        CommandUtils.registerOneArgInt("stacks", StackCommand::calculateStacks);
    }

    public static void calculateStacks(int itemCount){
        calculateStacks(itemCount, 64);
    }

    public static void calculateStacks(int itemCount, int itemPerStack){
        if (itemCount <= 0 || itemPerStack <= 0) {
            CommandUtils.feedbackMessage(Component.translatable("invalid-values"));
            return;
        }
        CommandUtils.feedbackMessage(Component.translatable("stacks-result", 
            itemCount,
            itemCount / itemPerStack,
            itemPerStack,
            itemCount % itemPerStack
        ));
    }
}
