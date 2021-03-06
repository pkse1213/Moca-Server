package sopt.org.moca.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Message {

    private String sender_id;
    private String receiver_id;
    private Date message_send_data = new Date();
    private String message_content;
    private String message_img;

}
