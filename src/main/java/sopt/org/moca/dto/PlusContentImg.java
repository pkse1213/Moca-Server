package sopt.org.moca.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PlusContentImg {
    private int plus_img_id;
    private int plus_contents_id;
    private String plus_default_img_url;
    private String plus_tagging_img_url;
}