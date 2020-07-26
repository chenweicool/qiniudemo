package com.chen.example;

import com.chen.Utils.UserConstant;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.model.FileInfo;
import com.qiniu.storage.model.FileListing;
import com.qiniu.util.Auth;

/**
 * @Author Chen
 * @Date 2020/7/26 17:09
 * 功能：
 * 列举空间文件信息的实现
 * 测试通过，
 **/
public class ListDemo {
    public static void main(String args[]) {

        Auth auth = Auth.create(UserConstant.ACCESS_KEy, UserConstant.SECRET_KEY);
        Configuration c = new Configuration(Region.autoRegion());

        //实例化一个BucketManager对象
        BucketManager bucketManager = new BucketManager(auth, c);
        String bucket = "chenweitest";

        try {
            //调用listFiles方法列举指定空间的指定文件
            //参数一：bucket    空间名
            //参数二：prefix    文件名前缀
            //参数三：marker    上一次获取文件列表时返回的 marker
            //参数四：limit     每次迭代的长度限制，最大1000，推荐值 100
            //参数五：delimiter 指定目录分隔符，列出所有公共前缀（模拟列出目录效果）。缺省值为空字符串
            FileListing fileListing = bucketManager.listFiles(bucket, null, null, 10, null);
            FileInfo[] items = fileListing.items;
            for (FileInfo fileInfo : items) {
                System.out.println(fileInfo.key);
            }
        } catch (QiniuException e) {
            Response r = e.response;
            System.out.println(r.toString());
        }
    }
}
