package fr.madu59.Commands;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.madu59.Utils.CommandUtils;
import net.minecraft.client.Minecraft;

public class EmojiCommand {
    static Map<String, String> emojiMap = new HashMap<>() {{
        put("surprised","(⊙ _ ⊙ )");
        put("shrug","¯\\_(ツ)_/¯");
        put("tablefilp","(╯°□°）╯︵ ┻━┻");
        put("unflip","┬─┬ ノ( ゜-゜ノ)");
        put("bear","ʕ•ᴥ•ʔ");
        put("disapproval","ಠ_ಠ");
        put("love","♥‿♥");
        put("happy","◕‿◕");
        put("smile", "😀");
        put("smile_filled", "☻");
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
        put("heart", "❤");
        put("broken_heart", "💔");
        put("skull", "☠");
        put("snowman", "☃");
        put("snow", "❄");
        put("check", "✔");
        put("peace", "✌");
        put("music", "♬");
        put("fishing", "🎣");
        put("fish", "🐟");
        put("coffee", "☕");
    }};

    public static void register(){
        List<String> options = emojiMap.keySet().stream().toList();
        CommandUtils.registerOneArg("emoji", options, EmojiCommand::writeEmoji);
    }

    public static void writeEmoji(String emoji){
        Minecraft.getInstance().getConnection().sendChat(emojiMap.get(emoji));
    }
}
