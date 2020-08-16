package devy.jwt.sqlite.jpa.valid;

import devy.jwt.sqlite.jpa.domain.Member;
import devy.jwt.sqlite.jpa.request.MemberSigninRequest;
import devy.jwt.sqlite.jpa.service.MemberService;
import org.slf4j.LoggerFactory;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * 회원정보 유효성 검사
 */
public class MemberSigninValidator implements Validator {

    private MemberService memberService;

    public MemberSigninValidator(MemberService memberService) {
        this.memberService = memberService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return MemberSigninRequest.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        // 회원정보
        MemberSigninRequest memberSigninRequest = (MemberSigninRequest) target;

        // 등록된 회원인지 검사
        boolean isSignupMember = memberService.isSignupMember(memberSigninRequest.toMember());
        if(!isSignupMember) {
            errors.rejectValue("memberEmail", "no.signup", "등록되지 않은 이메일입니다");
            return;
        }

        // 비밀번호가 맞는지 검사
        boolean isCorrectPassword = memberService.isCorrectPassword(memberSigninRequest.toMember());
        if(!isCorrectPassword) {
            errors.rejectValue("password", "no.correct", "비밀번호가 일치하지 않습니다");
        }
    }

}
