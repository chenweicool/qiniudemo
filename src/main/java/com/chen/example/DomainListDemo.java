package com.chen.example;

import com.chen.Utils.UserConstant;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.util.Auth;

/**
 * @Author Chen
 * @Date 2020/7/26 16:13
 * 域名的列举使用
 *
 **/
public class DomainListDemo {

    /**
     * 1.测试一个空间的绑定的域名
     *
     * 测试通过
     * @param args
     */
    public static void main(String args[]) {

        Auth auth = Auth.create(UserConstant.ACCESS_KEy, UserConstant.SECRET_KEY);

        Zone z = Zone.zone2();
        Configuration c = new Configuration(z);

        //实例化一个BucketManager对象
        BucketManager bucketManager = new BucketManager(auth, c);

        //要列举文件的空间名
        String bucket = "chenweitest";

        try {
            String[] domainLists = bucketManager.domainList(bucket);
            for(String domain : domainLists)
                System.out.print(domain);
        } catch (QiniuException e) {

        }
    }
}

