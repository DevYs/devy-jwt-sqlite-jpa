package devy.jwt.sqlite.jpa.valid;

import devy.jwt.sqlite.jpa.domain.Member;
import devy.jwt.sqlite.jpa.request.MemberSignupRequest;
import devy.jwt.sqlite.jpa.service.MemberService;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * 회원정보 유효성 검사
 */
public class MemberSignupValidator implements Validator {

    private MemberService memberService;

    public MemberSignupValidator(MemberService memberService) {
        this.memberService = memberService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return MemberSignupRequest.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        // 회원정보
        MemberSignupRequest memberSignupRequest = (MemberSignupRequest) target;

        // 등록된 회원인지 검사
        boolean isSignupMember = memberService.isSignupMember(memberSignupRequest.toMember());
        if(isSignupMember) {
            errors.rejectValue("memberEmail", "already.signup", "이미 등록된 이메일입니다");
        }

    }

}
