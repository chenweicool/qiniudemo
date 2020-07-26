package com.chen.example.bucketmanager;

import com.chen.Utils.UserConstant;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.util.Auth;

/**
 * @Author Chen
 * @Date 2020/7/26 17:57
 * 文件的复制
 **/
public class BucketManagerCopy {
    public static void main(String args[]) {
        //设置需要操作的账号的AK和SK
        String ACCESS_KEY = UserConstant.ACCESS_KEy;
        String SECRET_KEY = UserConstant.SECRET_KEY;
        Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);

        Configuration c = new Configuration(Region.autoRegion());

        //实例化一个BucketManager对象
        BucketManager bucketManager = new BucketManager(auth, c);
        //要测试的空间和key，并且这个key在你空间中存在
        String bucket = "chenweitest";
        String key = "view.jpg";
        //将文件从文件key 复制到文件key2。 可以在不同bucket复制
        String key2 = "view3";
        try {
            //调用copy方法移动文件
            bucketManager.copy(bucket, key, bucket, key2);
            System.out.println("上传文件成功");
        } catch (QiniuException e) {
            //捕获异常信息
            Response r = e.response;
            System.out.println(r.toString());
        }
    }
}
