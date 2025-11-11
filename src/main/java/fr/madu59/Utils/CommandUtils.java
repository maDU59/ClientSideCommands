package fr.madu59.Utils;

import java.util.List;

import com.mojang.brigadier.arguments.StringArgumentType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.commands.Commands;
import net.minecraft.commands.SharedSuggestionProvider;
import net.minecraft.network.chat.ClickEvent;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.HoverEvent;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraftforge.client.event.RegisterClientCommandsEvent;

public class CommandUtils {
    
    public static void registerSimple(RegisterClientCommandsEvent event, String commandName, Runnable action){
        String[] commandNameSplit = commandName.split(" ");
        if (commandNameSplit.length == 2) {
            registerSimple(event, commandNameSplit[0], commandNameSplit[1], action);
            return;
        }
        event.getDispatcher().register(
                Commands.literal(commandName)
                .executes(context -> {
                    action.run();
                    return 1;
                })
            );
    }

    public static void registerSimple(RegisterClientCommandsEvent event, String commandPart1, String commandPart2, Runnable action){
        event.getDispatcher().register(
                Commands.literal(commandPart1)
                .then(Commands.literal(commandPart2)
                    .executes(context -> {
                        action.run();
                        return 1;
                    })
                )
            );
    }

    public static void registerOneArg(RegisterClientCommandsEvent event, String commandName, List<String> options, java.util.function.Consumer<String> action){
        String[] commandNameSplit = commandName.split(" ");
        if (commandNameSplit.length == 2) {
            registerOneArg(event, commandNameSplit[0], commandNameSplit[1], options, action);
            return;
        }
        event.getDispatcher().register(
                Commands.literal(commandName)
                .then(Commands.argument("option", StringArgumentType.string()).suggests((context, builder) -> { return SharedSuggestionProvider.suggest(options, builder);})
                    .executes(context -> {
                        action.accept(StringArgumentType.getString(context, "option"));
                        return 1;
                    })
                )
            );
    }

    public static void registerOneArg(RegisterClientCommandsEvent event, String commandPart1, String commandPart2, List<String> options, java.util.function.Consumer<String> action){
        event.getDispatcher().register(
                Commands.literal(commandPart1)
                .then(Commands.literal(commandPart2)
                    .then(Commands.argument("option", StringArgumentType.string()).suggests((context, builder) -> { return SharedSuggestionProvider.suggest(options, builder);})
                        .executes(context -> {
                            action.accept(StringArgumentType.getString(context, "option"));
                            return 1;
                        })
                    )
                )
            );
    }

    public static void registerOneArg(RegisterClientCommandsEvent event, String commandName, java.util.function.Consumer<String> action){
        String[] commandNameSplit = commandName.split(" ");
        if (commandNameSplit.length == 2) {
            registerOneArg(event, commandNameSplit[0], commandNameSplit[1], action);
            return;
        }
        event.getDispatcher().register(
                Commands.literal(commandName)
                .then(Commands.argument("expression", StringArgumentType.greedyString())
                    .executes(context -> {
                        action.accept(StringArgumentType.getString(context, "expression"));
                        return 1;
                    })
                )
            );
    }

    public static void registerOneArg(RegisterClientCommandsEvent event,String commandPart1, String commandPart2, java.util.function.Consumer<String> action){
        event.getDispatcher().register(
                Commands.literal(commandPart1)
                .then(Commands.literal(commandPart2)
                    .then(Commands.argument("expression", StringArgumentType.greedyString())
                        .executes(context -> {
                            action.accept(StringArgumentType.getString(context, "expression"));
                            return 1;
                        })
                    )
                )
            );
    }

    public static void registerTwoArgs(RegisterClientCommandsEvent event, String commandName, java.util.function.BiConsumer<Float, Float> action){
        event.getDispatcher().register(
            Commands.literal(commandName)
                .then(Commands.argument("arg1", StringArgumentType.string())
                    .then(Commands.argument("arg2", StringArgumentType.string())
                        .executes(context -> {
                            try{
                                float arg1 = Float.parseFloat(StringArgumentType.getString(context, "arg1"));
                                float arg2 = Float.parseFloat(StringArgumentType.getString(context, "arg2"));
                                action.accept(arg1, arg2);
                                return 1;
                            }
                            catch(Exception e){
                                feedbackMessage(Component.translatable("invalid-argument"));
                                return 0;
                            }
                        })
                    )
                )
            );
    }

    public static void feedbackMessage(Component message){
        Minecraft.getInstance().player.displayClientMessage(message, false);
    }

    public static void feedbackMessage(String message, boolean status){
        Minecraft.getInstance().player.displayClientMessage(Component.literal(message + " " + (status? I18n.get("enabled") : I18n.get("disabled"))), false);
    }

    public static void feedbackMessage(Component message, String copy){
        Minecraft.getInstance().player.displayClientMessage(((MutableComponent)message).withStyle(
                Style.EMPTY
                    .withHoverEvent(
                        new HoverEvent(
                            HoverEvent.Action.SHOW_TEXT,
                            Component.translatable("copy")
                        )
                    )
                    .withClickEvent(
                        new ClickEvent(
                            ClickEvent.Action.COPY_TO_CLIPBOARD,
                            copy
                        )
                    )
            ), false);
    }
}
