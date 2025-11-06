package fr.madu59.Commands;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.madu59.Utils.CommandUtils;
import net.minecraft.client.Minecraft;

public class EmojiCommand {
    static Map<String, String> emojiMap = new HashMap<>() {{
        put("(⊙ _ ⊙ )","(⊙ _ ⊙ )");
        put("¯\\_(ツ)_/¯","¯\\_(ツ)_/¯");
        put("(╯°□°）╯︵ ┻━┻","(╯°□°）╯︵ ┻━┻");
        put("┬─┬ ノ( ゜-゜ノ)","┬─┬ ノ( ゜-゜ノ)");
        put("ʕ•ᴥ•ʔ","ʕ•ᴥ•ʔ");
        put("ಠ_ಠ","ಠ_ಠ");
        put("♥‿♥","♥‿♥");
        put("◕‿◕","◕‿◕");
        put("smile", "😀");
        put("laughing", "😂");
        put("sweat_smile", "😅");
        put("blush", "😊");
        put("heart_eyes", "😍");
        put("sunglasses", "😎");
        put("unamused", "😒");
        put("sad", "😔");
        put("cry", "😢");
        put("sob", "😭");
        put("angry", "😡");
        put("thumbsup", "👍");
        put("thumbsdown", "👎");
        put("pray", "🙏");
        put("muscle", "💪");
        put("fire", "🔥");
        put("star", "🌟");
        put("tada", "🎉");
        put("heart", "❤️");
        put("broken_heart", "💔");
    }};

    public static void Register(){
        List<String> options = emojiMap.keySet().stream().toList();
        CommandUtils.RegisterOneArg("emoji", options, EmojiCommand::WriteEmoji);
    }

    public static void WriteEmoji(String emoji){
        Minecraft.getInstance().getConnection().sendChat(emoji);
    }
}
