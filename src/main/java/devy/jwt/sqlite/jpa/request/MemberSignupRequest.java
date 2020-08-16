package devy.jwt.sqlite.jpa.request;

import devy.jwt.sqlite.jpa.domain.Member;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * 회원등록 정보
 */
public class MemberSignupRequest {

    /** 회원 이메일 */
    @Email(message = "이메일 형식으로 입력하세요")
    @NotBlank
    private String memberEmail;

    /** 회원 이름 */
    @NotBlank
    @Size(min = 2, max = 4, message = "회원이름은 2~4자 한글만 가능합니다")
    private String memberName;

    /** 비밀번호 */
    @Size(min = 8, max = 16, message = "비밀번호는 8~16자이어야 합니다")
    @NotBlank
    private String password;

    public String getMemberEmail() {
        return memberEmail;
    }

    public void setMemberEmail(String memberEmail) {
        this.memberEmail = memberEmail;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
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
        member.setMemberName(memberName);
        member.setPassword(password);
        return member;
    }

    @Override
    public String toString() {
        return "MemberSignupRequest{" +
                "memberEmail='" + memberEmail + '\'' +
                ", memberName='" + memberName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
