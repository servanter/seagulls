package com.crop.seagulls.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crop.seagulls.bean.Response;
import com.crop.seagulls.bean.ReturnCode;
import com.crop.seagulls.common.Constant;
import com.crop.seagulls.service.SystemSerivce;
import com.crop.seagulls.util.SessionUtils;

/**
 * 用户控制层
 * 
 * @author zhanghongyan@outlook.com
 * 
 */
@Controller
@RequestMapping("/system")
public class SystemController {

    @Autowired
    private SystemSerivce systemService;

    @ResponseBody
    @RequestMapping(value = "/sendCode", method = RequestMethod.GET)
    public Response sendVerificationCode(@RequestParam("phone") String phone, HttpSession session) {
        Response response = systemService.send(phone);
        System.out.println(response.getResult() + "+++++++++++++++++++==");
        if (ReturnCode.isSuccess(response.getReturnCode())) {
            SessionUtils.setValue(session, Constant.SMSCODE, response.getResult());
        }
        return response;
    }

    @RequestMapping("/generateCode")
    public void imageCheckCode(HttpSession session, HttpServletResponse response) throws Exception {
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);

        int width = 130, height = 37;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        Graphics g = image.getGraphics();
        g.setColor(getRandColor(200, 250));
        g.fillRect(0, 0, width, height);
        g.setFont(new Font("Times New Roman", Font.BOLD, 30));
        g.setColor(getRandColor(160, 200));

        Random random = new Random();
        for (int i = 0; i < 50; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            g.drawLine(x, y, x + xl, y + yl);
        }

        String sRand = "";
        for (int i = 0; i < 4; i++) {
            String rand = String.valueOf(random.nextInt(10));
            sRand += rand;
            g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
            g.drawString(rand, 20 * i + 30, 24);
        }
        g.dispose();
        SessionUtils.setValue(session, Constant.VERIFICATIONCODE, sRand);
        ImageIO.write(image, "JPEG", response.getOutputStream());
    }

    @ResponseBody
    @RequestMapping("/imageError")
    public Response checkImageCodeError() {
        Response response = new Response();
        response.setReturnCode(ReturnCode.IMAGE_CODE_ERROR);
        return response;
    }
    
    @ResponseBody
    @RequestMapping("/smsError")
    public Response checkSMSCodeError() {
        Response response = new Response();
        response.setReturnCode(ReturnCode.SMS_CODE_ERROR);
        return response;
    }

    private Color getRandColor(int fc, int bc) {
        Random random = new Random();
        if (fc > 255)
            fc = 255;
        if (bc > 255)
            bc = 255;
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }
}
