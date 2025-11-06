package fr.madu59.Utils;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.*;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;

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
}
