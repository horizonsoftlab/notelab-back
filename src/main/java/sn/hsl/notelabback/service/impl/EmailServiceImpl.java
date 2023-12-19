package sn.hsl.notelabback.service.impl;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import sn.hsl.notelabback.exceptions.NotelabException;
import sn.hsl.notelabback.service.EmailService;
import sn.hsl.notelabback.web.dto.response.MailRspDto;
import sn.hsl.notelabback.web.tools.response.ApiMessage;

import static sn.hsl.notelabback.commons.constants.AppConstant.EMAIL_TEMPLATE;
import static sn.hsl.notelabback.commons.constants.SecurityConstant.ACTIVE_ACCOUNT_FROM_EMAIL;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;
    @Value("${web.url}")
    private String webURL;
    @Value("${notelab.mail.from}")
    private String mailFrom;

    @Async
    @Override
    public void sendSimpleMail(String subject, String message, String[] emails) {
        sendMail(subject, message, emails);
    }

    @Async
    @Override
    public void sendWithHtml(String subject, MailRspDto mailRspDto, String email) {
        sendWithHtml(subject, mailRspDto, new String[] {email});
    }

    @Async
    @Override
    public void sendWithHtml(String subject, MailRspDto mailRspDto, String[] emails) {
        Context context = new Context();
        context.setVariable("mail", mailRspDto);
        context.setVariable("link", webURL + "/" + ACTIVE_ACCOUNT_FROM_EMAIL);

        String htmlContent = templateEngine.process(EMAIL_TEMPLATE, context);

        sendMail(subject, htmlContent, emails);
    }

    private void sendMail(String subject, String message, String[] emails) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            initMessageHelper(mimeMessage, subject, message, emails);
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            log.info("Failed to build email : {}", e.getMessage());
            throw new NotelabException(ApiMessage.FAILED_TO_BUILD_EMAIL, e.getMessage());
        } catch (MailException e) {
            log.info("Email not sent : {}", e.getMessage());
            throw new NotelabException(ApiMessage.FAILED_TO_SEND_EMAIL, e.getMessage());
        }
    }

    private void initMessageHelper(MimeMessage mimeMessage, String subject, String message, String[] to) throws MessagingException {
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");
        helper.setFrom(mailFrom);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(message, true);
    }

}
