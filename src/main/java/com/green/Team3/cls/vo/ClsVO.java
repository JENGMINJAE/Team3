package com.green.Team3.cls.vo;

import com.green.Team3.member.vo.MemberVO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@ToString
@Setter
@Getter
public class ClsVO {
    private int classNum;
    private String className;
    private int classPayment;
    private int classPersonnel;
    private String classSdate;
    private String classEdate;
    private String classEnter;
    private int classStatus;
    private int teacherNum;
    private List<MemberVO> memberVOList;
}
