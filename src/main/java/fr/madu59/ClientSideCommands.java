package fr.madu59;

import com.mojang.logging.LogUtils;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(ClientSideCommands.MOD_ID)
public class ClientSideCommands{
	public static final String MOD_ID = "clientsidecommands";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LogUtils.getLogger();

	public ClientSideCommands(FMLJavaModLoadingContext context) {
        // Get the mod event bus
        IEventBus modEventBus = context.getModEventBus();

        // Register client setup only for client distribution
        modEventBus.addListener(this::onClientSetup);
    }

    private void onClientSetup(final FMLClientSetupEvent event) {
        LOGGER.info("ClientSideCommands loaded on CLIENT side.");
    }
}