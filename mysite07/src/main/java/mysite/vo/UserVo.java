package mysite.vo;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserVo {

    private Long id;

    @Size(min = 2, max = 8)
    private String name;

    @Email
    @NotEmpty
    private String email;

    @Size(min = 4, max = 8)
    private String password;

    private String gender;

    private String joinDate;

    private String role;
}
