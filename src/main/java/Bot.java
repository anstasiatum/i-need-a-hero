import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.request.SendDocument;
import com.pengrad.telegrambot.request.SendMessage;
import player.userinputhandler.BotAnswer;

import static player.userinputhandler.UserInputHandler.handleUserInput;
import static player.userinputhandler.commands.createnewhero.Buttons.sendReplyKeyboard;

public class Bot {

    public static void telegramBotListener(String botToken) {

        TelegramBot bot = new TelegramBot(botToken);
        bot.setUpdatesListener(updates -> {
            updates.forEach(update -> {
                System.out.println("telegramBotListener is working");
                System.out.println(update);
                long chatId = update.message().chat().id();
                BotAnswer botAnswer = handleUserInput(update);
                if (botAnswer.getFile() != null) {
                    bot.execute(new SendDocument(chatId, botAnswer.getFile()));
                } else {
                    SendMessage request = new SendMessage(chatId, botAnswer.getAnswer());
                    request.replyMarkup(sendReplyKeyboard());
                    bot.execute(request);
                }
            });
            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        }, e -> {
            if (e.response() != null) {
                e.response().errorCode();
                e.response().description();
            } else {
                e.printStackTrace();
            }
        });
    }

    public static void main(String[] args) {
        telegramBotListener(args[0]);
    }
}
