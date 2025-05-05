package uz.pdp.checkerbot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Long chatId;
    private String firstName;
    private String lastName;
    private String username;
}
