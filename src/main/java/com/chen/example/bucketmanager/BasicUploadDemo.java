package com.chen.example.bucketmanager;

import com.chen.Utils.UserConstant;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;

import java.io.IOException;

/**
 * @Author Chen
 * @Date 2020/7/26 18:32
 * 基本的上传策略
 **/
public class BasicUploadDemo {
    //设置好账号的ACCESS_KEY和SECRET_KEY
    String ACCESS_KEY = UserConstant.ACCESS_KEy;
    String SECRET_KEY = UserConstant.SECRET_KEY;

    //要上传的空间
    String bucketname = "chenweitest";
    //上传到七牛后保存的文件名
    String key = "scenery";
    //上传文件的路径
    String FilePath = "E:\\Pictures\\有趣的照片\\timg.jpg";

    //密钥配置
    Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);


    //第二种方式: 自动识别要上传的空间(bucket)的存储区域是华东、华北、华南。
    Configuration c = new Configuration(Region.autoRegion());


    //创建上传对象
    UploadManager uploadManager = new UploadManager(c);

    public static void main(String args[]) throws IOException {
        new BasicUploadDemo().upload();
    }

    //简单上传，使用默认策略，只需要设置上传的空间名就可以了
    public String getUpToken() {
        return auth.uploadToken(bucketname);
    }

    public void upload() throws IOException {
        try {
            //调用put方法上传
            Response res = uploadManager.put(FilePath, key, getUpToken());
            //打印返回的信息
            System.out.println(res.bodyString());
        } catch (QiniuException e) {
            Response r = e.response;
            // 请求失败时打印的异常的信息
            System.out.println(r.toString());
            try {
                //响应的文本信息
                System.out.println(r.bodyString());
            } catch (QiniuException e1) {
                //ignore
            }
        }
    }
}

