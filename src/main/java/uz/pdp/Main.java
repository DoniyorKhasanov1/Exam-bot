package uz.pdp;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import uz.pdp.checkerbot.entity.service.MyBot;

import java.util.ResourceBundle;

public class Main {
    public static final ResourceBundle bundle = ResourceBundle.getBundle("settings");
    public static void main(String[] args) {
        try {
            TelegramBotsApi api = new TelegramBotsApi(DefaultBotSession.class);
            api.registerBot(new MyBot(bundle.getString("BOT_TOKEN"), "checker_homework_bot"));
            System.err.println("The bot has been started...");
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
