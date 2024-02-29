package com.green.Team3.member.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MemberVO {
    private String memberId;
    private String memberPw;
    private String memberName;
    private String memberTel;
    private String memberEmail;
    private int memberAge;
    private String memberGender;
    private String memberAddr;
    private String addrDetail;
    private String postCode;
    private String regDate;
    private int memberRoll;
}
