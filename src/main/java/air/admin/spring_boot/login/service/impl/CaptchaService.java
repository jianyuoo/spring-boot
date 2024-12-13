package air.admin.spring_boot.login.service.impl;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import net.sf.jsqlparser.statement.select.KSQLWindow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Properties;
import java.util.UUID;

@Service
public class CaptchaService {

    private DefaultKaptcha defaultKaptcha;

    @Autowired
    private StringRedisTemplate redisTemplate;

    public CaptchaService() {
        defaultKaptcha = new DefaultKaptcha();
        Properties properties = new Properties();
        // 设置验证码相关属性，如字体、大小、颜色等
        properties.setProperty("kaptcha.border", "yes");
        properties.setProperty("kaptcha.border.color", "105,179,90");
        // ... 其他属性设置
        Config config = new Config(properties);
        defaultKaptcha.setConfig(config);
    }

    public void createCaptcha(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String captchaText = defaultKaptcha.createText();
        String key = UUID.randomUUID().toString(); // 生成唯一的key
        redisTemplate.opsForValue().set(key, captchaText); // 将验证码保存到Redis，并设置过期时间
        request.getSession().setAttribute("captchaKey", key); // 将key保存到session中，用于前端验证

        BufferedImage image = defaultKaptcha.createImage(captchaText);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", byteArrayOutputStream);
        byte[] captchaImage = byteArrayOutputStream.toByteArray();

        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/jpeg");

        ServletOutputStream out = response.getOutputStream();
        out.write(captchaImage);
        out.flush();
        out.close();
    }
}
