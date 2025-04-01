package player.userinputhandler.commands.createnewhero;

import com.pengrad.telegrambot.model.request.Keyboard;
import com.pengrad.telegrambot.model.request.KeyboardButton;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;

public class Buttons {
    public static Keyboard keyboard = new ReplyKeyboardMarkup(
            new KeyboardButton[]{
                    new KeyboardButton("text"),
                    new KeyboardButton("contact").requestContact(true),
                    new KeyboardButton("location").requestLocation(true)
            }
    );
}

