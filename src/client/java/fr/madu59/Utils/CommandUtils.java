package fr.madu59.Utils;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.*;

import java.util.List;

import com.mojang.brigadier.arguments.StringArgumentType;

import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.minecraft.client.Minecraft;
import net.minecraft.commands.SharedSuggestionProvider;
import net.minecraft.network.chat.ClickEvent;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.HoverEvent;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;

public class CommandUtils {
    
    public static void RegisterSimple(String commandName, Runnable action){
        String[] commandNameSplit = commandName.split(" ");
        if (commandNameSplit.length == 2) {
            RegisterSimple(commandNameSplit[0], commandNameSplit[1], action);
            return;
        }

        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
            dispatcher.register(
                literal(commandName)
                .executes(context -> {
                    action.run();
                    return 1;
                })
            );
        });
    }

    public static void RegisterSimple(String commandPart1, String commandPart2, Runnable action){
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
            dispatcher.register(
                literal(commandPart1)
                .then(literal(commandPart2)
                    .executes(context -> {
                        action.run();
                        return 1;
                    })
                )
            );
        });
    }

    public static void RegisterOneArg(String commandName, List<String> options, java.util.function.Consumer<String> action){
        String[] commandNameSplit = commandName.split(" ");
        if (commandNameSplit.length == 2) {
            RegisterOneArg(commandNameSplit[0], commandNameSplit[1], options, action);
            return;
        }
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
            dispatcher.register(
                literal(commandName)
                .then(argument("option", StringArgumentType.string()).suggests((context, builder) -> { return SharedSuggestionProvider.suggest(options, builder);})
                    .executes(context -> {
                        action.accept(StringArgumentType.getString(context, "option"));
                        return 1;
                    })
                )
            );
        });
    }

    public static void RegisterOneArg(String commandPart1, String commandPart2, List<String> options, java.util.function.Consumer<String> action){
        String[] commandNameSplit = commandPart1.split(" ");
        if (commandNameSplit.length == 2) {
            RegisterOneArg(commandNameSplit[0], commandNameSplit[1], options, action);
            return;
        }
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
            dispatcher.register(
                literal(commandPart1)
                .then(literal(commandPart2)
                    .then(argument("option", StringArgumentType.string()).suggests((context, builder) -> { return SharedSuggestionProvider.suggest(options, builder);})
                        .executes(context -> {
                            action.accept(StringArgumentType.getString(context, "option"));
                            return 1;
                        })
                    )
                )
            );
        });
    }

    public static void RegisterOneArg(String commandName, java.util.function.Consumer<String> action){
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
            dispatcher.register(
                literal(commandName)
                .then(argument("expression", StringArgumentType.greedyString())
                    .executes(context -> {
                        action.accept(StringArgumentType.getString(context, "expression"));
                        return 1;
                    })
                )
            );
        });
    }

    public static void FeedbackMessage(Component message){
        Minecraft.getInstance().player.displayClientMessage(message, false);
    }

    public static void FeedbackMessage(Component message, String copy){
        Minecraft.getInstance().player.displayClientMessage(((MutableComponent)message).withStyle(
                Style.EMPTY
                    .withHoverEvent(
                        new HoverEvent.ShowText(
                            Component.translatable("copy")
                        )
                    )
                    .withClickEvent(
                        new ClickEvent.CopyToClipboard(
                            copy
                        )
                    )
            ), false);
    }
}
