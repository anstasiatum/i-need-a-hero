import com.google.common.collect.Lists;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.request.KeyboardButton;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;
import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.request.SendDocument;
import com.pengrad.telegrambot.request.SendMessage;
import player.userinputhandler.BotAnswer;
import player.userinputhandler.StateHolder;
import player.userinputhandler.UserInputHandler;
import player.userinputhandler.commands.createnewhero.CreateNewHero;
import player.userinputhandler.commands.db.CharacterDao;
import player.userinputhandler.commands.db.CharacterDaoImpl;
import player.userinputhandler.commands.deletehero.DeleteHero;
import player.userinputhandler.commands.printhero.PDFCreator;
import player.userinputhandler.commands.printhero.PrintHero;

public class Bot {

    public static void telegramBotListener(String botToken) {
        final StateHolder stateHolder = new StateHolder();
        final CharacterDao characterDao = new CharacterDaoImpl();
        final DeleteHero deleteHero = new DeleteHero(characterDao);
        final CreateNewHero createHero = new CreateNewHero(characterDao);
        final PDFCreator createPDF = new PDFCreator(characterDao);
        final PrintHero printHero = new PrintHero(characterDao, createPDF);

        UserInputHandler handleUserInput = new UserInputHandler(stateHolder, deleteHero, createHero, printHero);

        TelegramBot bot = new TelegramBot(botToken);
        bot.setUpdatesListener(updates -> {
            updates.forEach(update -> {
                System.out.println("telegramBotListener is working");
                System.out.println(update);
                long chatId = update.message().chat().id();
                BotAnswer botAnswer = handleUserInput.handleUserInput(chatId, update.message().text());
                bot.execute(buildRequest(botAnswer, chatId));
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

    public static BaseRequest<?, ?> buildRequest(BotAnswer botAnswer, Long chatId) {
        if (botAnswer.getFile() != null) {
            return new SendDocument(chatId, botAnswer.getFile());
        } else if (botAnswer.isHasOptions()) {
            ReplyKeyboardMarkup keyboard = new ReplyKeyboardMarkup(
                    Lists.partition(botAnswer.getOptionTexts(), 4).stream() // making a List of Lists with Strings
                            .map(optionList -> optionList.stream() // transforming a List of Strings into Arrays of KeyboardButtons
                                    .map(KeyboardButton::new)
                                    .toArray(KeyboardButton[]::new))
                            .toArray(KeyboardButton[][]::new) // putting the results into an array
            )
                    .resizeKeyboard(true)
                    .oneTimeKeyboard(true);
            SendMessage request = new SendMessage(chatId, botAnswer.getAnswer());
            request.replyMarkup(keyboard);
            return request;
        } else {
            return new SendMessage(chatId, botAnswer.getAnswer());
        }
    }

    public static void main(String[] args) {
        telegramBotListener(args[0]);
    }
}
