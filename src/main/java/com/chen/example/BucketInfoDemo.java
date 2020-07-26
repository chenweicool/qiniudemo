package com.chen.example;

import com.chen.Utils.UserConstant;
import com.qiniu.common.Zone;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.model.BucketInfo;
import com.qiniu.util.Auth;

import javax.xml.transform.Source;

/**
 * @Author Chen
 * @Date 2020/7/26 16:42
 * 得到存储空间的信息
 * 测试通过
 **/
public class BucketInfoDemo {

    Auth auth = Auth.create(UserConstant.ACCESS_KEy, UserConstant.SECRET_KEY);


    Configuration c = new Configuration(Region.autoRegion());
    //实例化一个BucketManager对象
    BucketManager bucketManager = new BucketManager(auth, c);

    // 空间名
    String bucket = "chenweitest";

    public static void main(String[] args) {
        new BucketInfoDemo().getBucketInfo();
    }

    /**
     * 得到bucket的详情信息
     * 更多的信息请参考BucketInfo这个类的信息
     */
    public void getBucketInfo() {
        try {
            BucketInfo bucketInfo = bucketManager.getBucketInfo(bucket);
            // 输出空间私有性
            System.out.println(bucketInfo.getPrivate());
            // 输出空间所述区域
            System.out.println(bucketInfo.getZone());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
