package mysite.vo;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public Integer getHit() {
        return hit;
    }

    public void setHit(Integer hit) {
        this.hit = hit;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    public Integer getG_no() {
        return g_no;
    }

    public void setG_no(Integer g_no) {
        this.g_no = g_no;
    }

    public Integer getO_no() {
        return o_no;
    }

    public void setO_no(Integer o_no) {
        this.o_no = o_no;
    }

    public Integer getDepth() {
        return depth;
    }

    public void setDepth(Integer depth) {
        this.depth = depth;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "BoardVo{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", contents='" + contents + '\'' +
                ", hit=" + hit +
                ", regDate='" + regDate + '\'' +
                ", g_no=" + g_no +
                ", o_no=" + o_no +
                ", depth=" + depth +
                ", userId=" + userId +
                ", userName='" + userName + '\'' +
                '}';
    }
}
