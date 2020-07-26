package com.chen.example.bucketmanager;

import com.chen.Utils.UserConstant;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.processing.OperationManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import com.qiniu.util.UrlSafeBase64;

/**
 * @Author Chen
 * @Date 2020/7/26 18:11
 * 转码的操作
 * 测试不通过
 **/
public class fosDemo {
    public static void main(String[] args) throws QiniuException {
        //设置账号的AK,SK
        String ACCESS_KEY = UserConstant.ACCESS_KEy;
        String SECRET_KEY = UserConstant.SECRET_KEY;
        Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);

        Configuration c = new Configuration(Region.autoRegion());

        //新建一个OperationManager对象
        OperationManager operater = new OperationManager(auth, c);
        //设置要转码的空间和key，并且这个key在你空间中存在
        String bucket = "chenweitest";
        String key = "搞笑的视频.mp4";
        //设置转码操作参数
        String fops = "avthumb/mp4/ab/160k/ar/44100/acodec/libfaac/r/30/vb/2200k/vcodec/libx264/s/1280x720/autoscale/1/stripmeta/0";
        //设置转码的队列
        String pipeline = "transer";
        //可以对转码后的文件进行使用saveas参数自定义命名，当然也可以不指定文件会默认命名并保存在当前空间。
        String urlbase64 = UrlSafeBase64.encodeToString("transer-vedio");
        String pfops = fops + "|saveas/" + urlbase64;
        //设置pipeline参数
        StringMap params = new StringMap().putWhen("force", 1, true).putNotEmpty("pipeline", pipeline);
        try {
            String persistid = operater.pfop(bucket, key, pfops, params);
            //打印返回的persistid
            System.out.println(persistid);
        } catch (QiniuException e) {
            //捕获异常信息
            Response r = e.response;
            // 请求失败时简单状态信息
            System.out.println(r.toString());
            try {
                // 响应的文本信息
                System.out.println(r.bodyString());
            } catch (QiniuException e1) {
                //ignore
            }
        }
    }
}
