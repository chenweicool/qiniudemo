package com.chen.example;

import com.chen.Utils.UserConstant;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;

/**
 * @Author Chen
 * @Date 2020/7/26 17:14
 * 云主机传文件到对象存储的实现
 * //TODO 暂时不进行测试
 **/
public class QvmUploadDemo {
    // 获取凭证
    public static void main(String[] args) {

        //设置账号的AK,SK
        String ACCESS_KEY = UserConstant.ACCESS_KEy;
        String SECRET_KEY = UserConstant.SECRET_KEY;

        //要上传的空间和文件名，以及上传的资源路径
        String bucketname = "chenweitest";
        String key = "my-java.png";
        //上传文件的路径
        String FilePath = "E:\\Pictures\\家人\\碎女子jpg\\1.jpg";

        // 构造一个带指定的zone对象的配置类,华东1区域的云主机可以选择Zone.qvmZone0()，华北2区域(北京)的云主机可以选择Zone.qvmZone1()，目前其他存储区域是不支持
        Configuration configuration = new Configuration(Region.qvmRegion0());
        //创建上传对象
        UploadManager uploadManager = new UploadManager(configuration);
        //秘钥鉴权
        Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
        //简单上传，使用默认策略，只需要设置上传的空间名就可以了
        String upToken = auth.uploadToken(bucketname);
        Response res = null;
        try {
            //调用上传方法
            res = uploadManager.put(FilePath, key, upToken);
        } catch (QiniuException e) {
            e.printStackTrace();
        }
        //打印返回的信息
        System.out.println(res.statusCode);
        System.out.println(res.toString());
    }
}
