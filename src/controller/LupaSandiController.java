package controller;

import model.UserInfoMapper;
import model.UserOtp;
import model.UserOtpMapper;
import org.apache.ibatis.session.SqlSession;
import util.MyBatisUtil;

import java.time.LocalDateTime;
import java.util.Properties;
import java.util.Random;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import model.UserInfo;

public class LupaSandiController {

    public String generateOtp(String email) {
        String otpCode = String.format("%06d", new Random().nextInt(999999));
        LocalDateTime expiresAt = LocalDateTime.now().plusMinutes(5);

        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            UserOtpMapper otpMapper = session.getMapper(UserOtpMapper.class);
            UserOtp otp = new UserOtp();
            otp.setEmail(email);
            otp.setOtpCode(otpCode);
            otp.setExpiresAt(expiresAt);
            otp.setIsUsed(false);
            otpMapper.insertOtp(otp);
            session.commit();

            // Kirim OTP via Email
            sendOtpEmail(email, otpCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return otpCode;
    }

    private void sendOtpEmail(String toEmail, String otpCode) {
        String fromEmail = "dikifaturrohman17@gmail.com"; // Ganti dengan email pengirim
        String emailPassword = "ppuluyifeqwicajt"; // Ganti dengan app password (bukan password biasa)

        // Konfigurasi SMTP
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        // Autentikasi
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, emailPassword);
            }
        });

        try {
            // Membuat email
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmail));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
            message.setSubject("Kode OTP Anda");
            message.setText("Kode OTP Anda adalah: " + otpCode + "\n\nKode ini berlaku selama 5 menit.");

            // Mengirim email
            Transport.send(message);
            System.out.println("OTP berhasil dikirim ke: " + toEmail);
        } catch (MessagingException e) {
            e.printStackTrace();
            System.err.println("Gagal mengirim email ke: " + toEmail);
        }
    }

    public boolean validateOtp(String email, String otpCode) {
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            UserOtpMapper otpMapper = session.getMapper(UserOtpMapper.class);
            UserOtp otp = otpMapper.validateOtp(email, otpCode);
            if (otp != null) {
                otpMapper.markOtpAsUsed(otp.getId());
                session.commit();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean resetPassword(String email, String newPassword) {
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            UserInfoMapper userMapper = session.getMapper(UserInfoMapper.class);
            UserInfo user = userMapper.selectUserByEmail(email);
            if (user != null) {
                user.setPassword(newPassword);
                userMapper.updateUserPassword(user);
                session.commit();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
