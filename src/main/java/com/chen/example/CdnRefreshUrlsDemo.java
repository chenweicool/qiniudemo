package com.chen.example;

import com.chen.Utils.UserConstant;
import com.qiniu.cdn.CdnManager;
import com.qiniu.cdn.CdnResult;
import com.qiniu.common.QiniuException;
import com.qiniu.util.Auth;

/**
 * @Author Chen
 * @Date 2020/7/26 16:05
 * 刷新单个文件的demo
 * 测试通过
 **/
public class CdnRefreshUrlsDemo {
    public static void main(String args[]) {

        Auth auth = Auth.create(UserConstant.ACCESS_KEy, UserConstant.SECRET_KEY);
        CdnManager c = new CdnManager(auth);
        //待刷新的目录列表，目录必须以 / 结尾
        String[] urls = new String[]{
                "http://cww.huangbowei.com/chenweitest/chenweiwei.jpg",
        };
        try {
            //单次方法调用刷新的目录不可以超过10个，另外刷新目录权限需要联系技术支持开通
            CdnResult.RefreshResult result = c.refreshUrls(urls);
            System.out.println(result.code);
            //获取其他的回复内容
        } catch (QiniuException e) {
            System.err.println(e.response.toString());
        }
    }
}
