package devy.jwt.sqlite.jpa.request;

import devy.jwt.sqlite.jpa.domain.Member;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * 회원 로그인 정보
 */
public class MemberSigninRequest {

    /** 회원 이메일 */
    @Email
    @NotBlank
    private String memberEmail;

    /** 비밀번호 */
    @NotBlank
    @Size(min = 8, max = 16, message = "비밀번호는 8~16자이어야 합니다")
    private String password;

    public String getMemberEmail() {
        return memberEmail;
    }

    public void setMemberEmail(String memberEmail) {
        this.memberEmail = memberEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Member toMember() {
        Member member = new Member();
        member.setMemberEmail(memberEmail);
        member.setPassword(password);
        return member;
    }

    @Override
    public String toString() {
        return "MemberSigninRequest{" +
                "memberEmail='" + memberEmail + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
