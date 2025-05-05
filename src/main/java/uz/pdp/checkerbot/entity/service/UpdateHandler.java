package uz.pdp.checkerbot.entity.service;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import uz.pdp.checkerbot.entity.Homework;
import uz.pdp.checkerbot.entity.User;
import uz.pdp.checkerbot.entity.UserState;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static uz.pdp.checkerbot.entity.database.DB.users;

public class UpdateHandler {
    public Map<String, UserState> stateMap = new HashMap<>();
    private MyBot bot;

    public UpdateHandler(MyBot bot) {
        this.bot = bot;
    }

    public void adminHandler(String chatId, Message message) {
        if (message.getText().equals("/start")) {
            sendMessage(chatId, "Welcome ADMIN👋");
        }
    }

    public void userHandler(String chatId, Message message) throws TelegramApiException {
        String text = message.getText();
        if (text.equals("/start")) {
            setUserMenu(chatId, message);
        } else if (text.equals("Send Homework") && stateMap.get(chatId) == UserState.WAITING_MARKUP) {
            stateMap.put(chatId, UserState.SENDING_HOMEWORK_DESC);
            ReplyKeyboardMarkup markup = new ReplyKeyboardMarkup();
            markup.setResizeKeyboard(true);
            KeyboardRow row = new KeyboardRow();
            row.add("CANCEL");
            markup.setKeyboard(Arrays.asList(row));
            sendReplyMarkup(chatId, "Please send homework theme or description", markup);
        } else if (stateMap.get(chatId) == UserState.SENDING_HOMEWORK_DESC) {
             String text1 = message.getText();
            Homework homework = new Homework();
            homework.setDescription(text1);
        } else if (stateMap.get(chatId) == UserState.SENDING_HOMEWORK_DESC && text.equals("CANCEL")) {
            SendMessage removeKeyboardMessage = new SendMessage(chatId, "✅ Sending cancelled");
            ReplyKeyboardRemove removeKeyboard = new ReplyKeyboardRemove();
            removeKeyboard.setRemoveKeyboard(true);
            removeKeyboardMessage.setReplyMarkup(removeKeyboard);
            bot.execute(removeKeyboardMessage);
            setUserMenu(chatId, message);
        }else {
            sendMessage(chatId, "Could not understand this command☹️");
        }

    }

    public void sendMessage(String chatId, String text) {
        SendMessage message = new SendMessage(chatId, text);
        message.setParseMode("Markdown");
        try {
            bot.execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void sendReplyMarkup(String chatId, String text, ReplyKeyboardMarkup markup) {
        SendMessage message = new SendMessage(chatId, text);
        message.setReplyMarkup(markup);
        message.setParseMode("Markdown");
        try {
            bot.execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
    public void setUserMenu(String chatId, Message message){
        User user = new User();
        String firstName = message.getFrom().getFirstName();
        String lastName = message.getFrom().getLastName();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setChatId(Long.valueOf(chatId));
        users.add(user);
        ReplyKeyboardMarkup markup = new ReplyKeyboardMarkup();
        markup.setResizeKeyboard(true);
        KeyboardRow row = new KeyboardRow();
        row.add(new KeyboardButton("Send Homework"));
        row.add(new KeyboardButton("Show old Homework"));
        markup.setKeyboard(Arrays.asList(row));
        sendReplyMarkup(chatId, "Welcome " + firstName + "\uD83D\uDC4B\n You can use this bot to get checked your homework❗", markup);
        stateMap.put(chatId, UserState.WAITING_MARKUP);
    }
}
