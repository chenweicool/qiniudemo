package com.chen.example.bucketmanager;

import com.chen.Utils.UserConstant;
import com.qiniu.util.Auth;

/**
 * @Author Chen
 * @Date 2020/7/26 18:07
 *
 * 构建一个私有的下载连接
 * 当您的空间设置为私有的时候，就需要鉴权才能访问
 **/
public class DownLoadDemo {
    //设置好账号的ACCESS_KEY和SECRET_KEY
    String ACCESS_KEY = UserConstant.ACCESS_KEy;
    String SECRET_KEY = UserConstant.SECRET_KEY;
    //密钥配置
    Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
    //构造私有空间的需要生成的访问的链接
    String URL = "http://cww.huangbowei.com/chenweiwei.jpg";

    public static void main(String args[]) {
        new DownLoadDemo().download();
    }

    public void download() {
        //调用privateDownloadUrl方法生成下载链接,第二个参数可以设置Token的过期时间
        String downloadRUL = auth.privateDownloadUrl(URL, 3600);
        System.out.println(downloadRUL);
    }
}
