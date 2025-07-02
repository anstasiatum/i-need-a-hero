package player.userinputhandler.commands.createnewhero;

import com.pengrad.telegrambot.model.request.KeyboardButton;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;

public class Buttons {
    public void sendReplyKeyboard(Long chatID) {

        // Create keyboard buttons
        KeyboardButton button1 = new KeyboardButton("Option 1");
        KeyboardButton button2 = new KeyboardButton("Option 2");

        // Create the reply keyboard markup
        ReplyKeyboardMarkup keyboard = new ReplyKeyboardMarkup(
                new KeyboardButton[]{button1, button2}
        )
                .resizeKeyboard(true)  // Make keyboard smaller
                .oneTimeKeyboard(true); // Hide after use
    }
}

