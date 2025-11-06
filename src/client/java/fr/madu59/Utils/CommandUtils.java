package fr.madu59.Utils;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.*;

import java.util.List;

import com.mojang.brigadier.arguments.StringArgumentType;

import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.minecraft.commands.SharedSuggestionProvider;

public class CommandUtils {
    
    public static void RegisterSimple(String commandName, Runnable action){
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

    public static void RegisterOneArg(String commandName, List<String> options, java.util.function.Consumer<String> action){
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
}
