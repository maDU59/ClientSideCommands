package fr.madu59;

import fr.madu59.Commands.CalcCommand;
import fr.madu59.Commands.CopyPositionCommand;
import fr.madu59.Commands.CopyRotationCommand;
import fr.madu59.Commands.DebugCommand;
import fr.madu59.Commands.DebugRendererCommand;
import fr.madu59.Commands.EmojiCommand;
import fr.madu59.Commands.FOVCommand;
import fr.madu59.Commands.GammaCommand;
import fr.madu59.Commands.PositionCommand;
import fr.madu59.Commands.RotationCommand;
import net.fabricmc.api.ClientModInitializer;

public class ClientSideCommandsClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.
		RegisterCommands();
	}

	public void RegisterCommands(){
		PositionCommand.Register();
		RotationCommand.Register();
		CopyPositionCommand.Register();
		CopyRotationCommand.Register();
		EmojiCommand.Register();
		CalcCommand.Register();
		DebugRendererCommand.Register();
		DebugCommand.Register();
		FOVCommand.Register();
		GammaCommand.Register();
	}
}