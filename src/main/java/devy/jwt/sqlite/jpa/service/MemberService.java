package devy.jwt.sqlite.jpa.service;

import devy.jwt.sqlite.jpa.domain.Member;
import devy.jwt.sqlite.jpa.repository.MemberRepository;
import devy.jwt.sqlite.jpa.util.JwtCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 회원 서비스
 */
@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    /**
     * 회원정보 등록
     * @param newMember 신규회원정보
     * @return 등록한 회원정보 반환
     */
    public Member signup(Member newMember) {
        // 회원번호는 ZonedDateTime 현재 시간을 long으로 변환한 값이다
        ZonedDateTime zonedDateTime = LocalDateTime.now().atZone(ZoneOffset.UTC);
        // 비밀번호 암호화
        String encodePassword = PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(newMember.getPassword());

        // 회원번호 및 암호화한 비밀번호 세팅
        newMember.setMemberNo(zonedDateTime.toInstant().toEpochMilli());
        newMember.setPassword(encodePassword);

        // 회원등록 후 비밀번호를 null로 세팅하여 반환한다
        Member savedMember = memberRepository.save(newMember);
        savedMember.setPassword(null);

        return savedMember;
    }

    /**
     * 로그인
     * @param member 로그인 정보
     * @return 생성한 jwt 반환
     */
    public String signin(Member member) {
        Member signedMember = memberRepository.findByMemberEmail(member.getMemberEmail());
        String jwt = JwtCreator.create(signedMember, PasswordEncoderFactories.createDelegatingPasswordEncoder());

        // TODO 생성한 토큰을 DB에 저장

        return jwt;
    }

    /**
     * 등록된 회원인지 조회
     * @param member 조회하려는 회원정보
     * @return 등록 여부
     */
    public boolean isSignupMember(Member member) {
        // 회원정보 조회
        Member signupMember = memberRepository.findByMemberEmail(member.getMemberEmail());

        // 등록된 회원정보가 없을 경우 실패
        if(signupMember == null) {
            return false;
        }

        return true;
    }

    /**
     * 비밀번호 일치 여부
     * @param member 회원정보
     * @return 비밀번호 일치 여부
     */
    public boolean isCorrectPassword(Member member) {
        // 회원정보 조회
        Member signupMember = memberRepository.findByMemberEmail(member.getMemberEmail());

        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

        // 비밀번호가 틀릴 경우 실패
        if(!passwordEncoder.matches(member.getPassword(), signupMember.getPassword())) {
            return false;
        }

        return true;
    }

    /**
     * 모든 회원 조회
     * @return 모든 회원 목록
     */
    public List<Member> getMemberListAll() {
        List<Member> allMember = memberRepository.findAll();
        return allMember.stream()
                .map(member -> {
                    member.setPassword(null);
                    return member;
                })
                .collect(Collectors.toList());
    }

}
