package com.chen.example;

import com.chen.Utils.UserConstant;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.sms.SmsManager;
import com.qiniu.util.Auth;

import javax.jws.soap.SOAPBinding;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author Chen
 * @Date 2020/7/26 17:37
 * 云短信的测试
 * 没有账号，故没有测试
 **/
public class SmsSendMessageDemo {
    public static void main(String args[]) {
        // 设置需要操作的账号的AK和SK
        String ACCESS_KEY = UserConstant.ACCESS_KEy;
        String SECRET_KEY = UserConstant.SECRET_KEY;
        Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);

        // 实例化一个SmsManager对象
        SmsManager smsManager = new SmsManager(auth);
        try {
            Map<String, String> map = new HashMap<String, String>();
            Response resp = smsManager.sendMessage("templateId", new String[] { "10086" }, map);
//          Response resp = smsManager.describeSignature("passed", 0, 0);
//          Response resp = smsManager.createSignature("signature", "app",
//                  new String[] { "data:image/gif;base64,xxxxxxxxxx" });
//          Response resp = smsManager.describeTemplate("passed", 0, 0);
//          Response resp = smsManager.createTemplate("name", "template", "notification", "test", "signatureId");
//          Response resp = smsManager.modifyTemplate("templateId", "name", "template", "test", "signatureId");
//          Response resp = smsManager.modifySignature("SignatureId", "signature");
//          Response resp = smsManager.deleteSignature("signatureId");
//          Response resp = smsManager.deleteTemplate("templateId");
            System.out.println(resp.bodyString());

//          SignatureInfo sinfo = smsManager.describeSignatureItems("", 0, 0);
//          System.out.println(sinfo.getItems().get(0).getAuditStatus());
//          TemplateInfo tinfo = smsManager.describeTemplateItems("", 0, 0);
//          System.out.println(tinfo.getItems().get(0).getAuditStatus());


        } catch (QiniuException e) {
            System.out.println(e);
        }

    }
}
