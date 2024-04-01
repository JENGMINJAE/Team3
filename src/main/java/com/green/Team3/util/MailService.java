package com.green.Team3.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service("mailService")
public class MailService {

    @Autowired
    private JavaMailSender javaMailSender;

    //단순 문자 메일 보내기
    public void sendSimpleEmail(MailVO mailVO){
        //단순 문자 메일을 보낼 수 있는 객체 생성
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject(mailVO.getTitle());  //메일 제목
        message.setTo(mailVO.getRecipient());   //받는사람
        message.setText(mailVO.getContent());   //내용
        javaMailSender.send(message);
    }
    //html 메일 보내기
    public void sendHTMLEmail(){

    }
    //6자리의 랜덤 비밀번호 생성
    public void createRandomPw(){

    }







}
