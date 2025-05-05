package uz.pdp.checkerbot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.util.UUID;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Homework {
    private UUID uuid;
    private Long chatId;
    private String description;
    private String zipFileId;
    private Integer ball;
    private String feedback;
    private Time sendTime;
    private Time checkTime;


}
