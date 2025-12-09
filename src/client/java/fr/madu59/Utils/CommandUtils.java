package fr.madu59.Utils;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.*;

import java.util.List;

import com.mojang.brigadier.arguments.FloatArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;

import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.commands.Commands;
import net.minecraft.commands.SharedSuggestionProvider;
import net.minecraft.network.chat.ClickEvent;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.HoverEvent;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.server.permissions.Permission;
import net.minecraft.server.permissions.PermissionCheck;
import net.minecraft.server.permissions.PermissionLevel;

public class CommandUtils {

    public static final Permission CREATIVE_MODE = new Permission.HasCommandLevel(PermissionLevel.MODERATORS);
    
    public static void registerSimple(String commandName, Runnable action){
        String[] commandNameSplit = commandName.split(" ");
        if (commandNameSplit.length == 2) {
            registerSimple(commandNameSplit[0], commandNameSplit[1], action);
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

    public static void registerSimple(String commandPart1, String commandPart2, Runnable action){
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

    public static void registerSimpleLvl2(String commandName, Runnable action){
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
            dispatcher.register(
                literal(commandName)
                .requires(Commands.hasPermission(new PermissionCheck.Require(CREATIVE_MODE)))
                .executes(context -> {
                    action.run();
                    return 1;
                })
            );
        });
    }

    public static void registerOneArg(String commandName, List<String> options, java.util.function.Consumer<String> action){
        String[] commandNameSplit = commandName.split(" ");
        if (commandNameSplit.length == 2) {
            registerOneArg(commandNameSplit[0], commandNameSplit[1], options, action);
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

    public static void registerOneArg(String commandPart1, String commandPart2, List<String> options, java.util.function.Consumer<String> action){
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

    public static void registerOneArg(String commandName, java.util.function.Consumer<String> action){
        String[] commandNameSplit = commandName.split(" ");
        if (commandNameSplit.length == 2) {
            registerOneArg(commandNameSplit[0], commandNameSplit[1], action);
            return;
        }
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

    public static void registerOneArg(String commandPart1, String commandPart2, java.util.function.Consumer<String> action){
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
            dispatcher.register(
                literal(commandPart1)
                .then(literal(commandPart2)
                    .then(argument("expression", StringArgumentType.greedyString())
                        .executes(context -> {
                            action.accept(StringArgumentType.getString(context, "expression"));
                            return 1;
                        })
                    )
                )
            );
        });
    }

    public static void registerOneArgInt(String commandName, java.util.function.Consumer<Integer> action){
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
            dispatcher.register(
                literal(commandName)
                .then(argument("arg1", IntegerArgumentType.integer())
                    .executes(context -> {
                        try{
                            int arg1 = IntegerArgumentType.getInteger(context, "arg1");
                            action.accept(arg1);
                            return 1;
                        }
                        catch(Exception e){
                            feedbackMessage(Component.translatable("invalid-argument"));
                            return 0;
                        }
                    })
                )
            );
        });
    }

    public static void registerTwoArgs(String commandName, java.util.function.BiConsumer<String, String> action){
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
            dispatcher.register(
                literal(commandName)
                .then(argument("arg1", StringArgumentType.string())
                    .then(argument("arg2", StringArgumentType.string())
                        .executes(context -> {
                            try{
                                String arg1 = StringArgumentType.getString(context, "arg1");
                                String arg2 = StringArgumentType.getString(context, "arg2");
                                action.accept(arg1, arg2);
                                return 1;
                            }
                            catch(Exception e){
                                feedbackMessage(Component.translatable("invalid-arguments"));
                                return 0;
                            }
                        })
                    )
                )
            );
        });
    }

    public static void registerTwoArgsFloat(String commandName, java.util.function.BiConsumer<Float, Float> action){
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
            dispatcher.register(
                literal(commandName)
                .then(argument("arg1", FloatArgumentType.floatArg())
                    .then(argument("arg2", FloatArgumentType.floatArg())
                        .executes(context -> {
                            try{
                                float arg1 = FloatArgumentType.getFloat(context, "arg1");
                                float arg2 = FloatArgumentType.getFloat(context, "arg2");
                                action.accept(arg1, arg2);
                                return 1;
                            }
                            catch(Exception e){
                                feedbackMessage(Component.translatable("invalid-arguments"));
                                return 0;
                            }
                        })
                    )
                )
            );
        });
    }

    public static void registerTwoArgsInt(String commandName, java.util.function.BiConsumer<Integer, Integer> action){
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
            dispatcher.register(
                literal(commandName)
                .then(argument("arg1", IntegerArgumentType.integer())
                    .then(argument("arg2", IntegerArgumentType.integer())
                        .executes(context -> {
                            try{
                                int arg1 = IntegerArgumentType.getInteger(context, "arg1");
                                int arg2 = IntegerArgumentType.getInteger(context, "arg2");
                                action.accept(arg1, arg2);
                                return 1;
                            }
                            catch(Exception e){
                                feedbackMessage(Component.translatable("invalid-arguments"));
                                return 0;
                            }
                        })
                    )
                )
            );
        });
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
