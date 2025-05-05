package uz.pdp.checkerbot.entity.service;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import uz.pdp.checkerbot.entity.User;

import static uz.pdp.Main.bundle;
import static uz.pdp.checkerbot.entity.database.DB.users;

public class MyBot extends TelegramLongPollingBot {
    private String username;
    public UpdateHandler handler = new UpdateHandler(this);

    public MyBot(String botToken, String username) {
        super(botToken);
        this.username = username;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().getText() != null) {
            String chatId = update.getMessage().getChatId().toString();
            Message message = update.getMessage();
            if (chatId.equals(bundle.getString("ADMIN_ID"))) {
                User admin = new User();
                admin.setChatId(Long.valueOf(chatId));
                users.add(admin);
                handler.adminHandler(chatId, message);
            } else {
                try {
                    handler.userHandler(chatId, message);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public String getBotUsername() {
        return username;
    }
}
