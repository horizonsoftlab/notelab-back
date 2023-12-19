package sn.hsl.notelabback.service;

import jakarta.mail.MessagingException;
import sn.hsl.notelabback.web.dto.response.MailRspDto;

public interface EmailService {

    void sendSimpleMail(String subject, String message, String[] emails) throws MessagingException;

    void sendWithHtml(String subject, MailRspDto mailRspDto, String email);

    void sendWithHtml(String subject, MailRspDto mailRspDto, String[] emails);
}
