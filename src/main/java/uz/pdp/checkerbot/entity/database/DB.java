package uz.pdp.checkerbot.entity.database;

import uz.pdp.checkerbot.entity.Homework;
import uz.pdp.checkerbot.entity.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface DB {
    List<User> users = new ArrayList<>();
    Map<String, Homework> homeworks = new HashMap<>();
}
