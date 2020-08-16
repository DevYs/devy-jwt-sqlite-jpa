package devy.jwt.sqlite.jpa.domain;

import javax.persistence.*;

/**
 * 회원정보
 */
@Entity
public class Member {

    /** 회원 번호 */
    @Id
    private long memberNo;

    /** 회원 이메일 */
    private String memberEmail;

    /** 회원 이름 */
    private String memberName;

    /** 비밀번호 */
    private String password;

    public long getMemberNo() {
        return memberNo;
    }

    public void setMemberNo(long memberNo) {
        this.memberNo = memberNo;
    }

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

    @Override
    public String toString() {
        return "Member{" +
                "memberNo=" + memberNo +
                ", memberEmail='" + memberEmail + '\'' +
                ", memberName='" + memberName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
