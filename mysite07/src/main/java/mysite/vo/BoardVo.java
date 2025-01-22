package mysite.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BoardVo {
    private Long id;
    private String title;
    private String contents;
    private Integer hit = 0;
    private String regDate;
    private Integer g_no;
    private Integer o_no = 1;
    private Integer depth = 0;
    private Long userId;
    private String userName;
}
