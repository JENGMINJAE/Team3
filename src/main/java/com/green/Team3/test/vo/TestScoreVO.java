package com.green.Team3.test.vo;

import com.green.Team3.admin.vo.OperatorVO;
import com.green.Team3.member.vo.MemberVO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class TestScoreVO {
    private int scoreNum;
    private int score;
    private String memberId;
    private int testNum;
    private int subTestNum;

    private int ranking;
    private double testAvg;
    private String className;
    private String level;
    private MemberVO memberOneVO;
    private TestVO testOneVo;
    private TestSubjectVO testSubOneVO;

}
