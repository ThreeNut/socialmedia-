package com.tensquare.sms.listener;

import com.tensquare.sms.utils.SmsUtil;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 监听 接收mq消息 消息中包含手机号 和随机6位验证码 封装到map里面
 * @author BinPeng
 * @date 2020/5/19 16:34
 */
@Component
@RabbitListener(queues = "sms")
public class SmsListener {
    @Autowired
    private SmsUtil smsUtil;

    @Value("${aliyun.sms.template_code}")
    private String template_code;

    @Value("${aliyun.sms.sing_name}")
    private String sing_name;

    @RabbitHandler
    public void executeSms(Map<String,String> map){

        System.out.println("手机号"+map.get("mobile")+">>>>验证码:"+map.get("code"));

        String code = map.get("code");
        smsUtil.sendSms(map.get("mobile"),template_code,sing_name,"{\"code\":\""+ code +"\"}");

    }
}
