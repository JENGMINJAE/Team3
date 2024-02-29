package com.green.Team3.member.vo;

import com.green.Team3.cls.vo.ClsVO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class TeacherVO {
    private int teacherNum;
    private String teacherClass;
    private int workCode;
    private String memberId;
    private String workStatus;
    private MemberVO memberVO;
    private List<ClsVO> clsVO;
}
