package com.chen.example;

import com.chen.Utils.UserConstant;
import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;

/**
 * @Author Chen
 * @Date 2020/7/26 17:48
 * 自定义上传文件的区域和格式
 *  测试通过
 **/
public class DefineUploadDomain {
    private static final String ACCESS_KEY = UserConstant.ACCESS_KEy;
    private static final String SECRET_KEY = UserConstant.SECRET_KEY;
    private static final String BUCKET = "chenweitest";
    private static final Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);

    /**
     * 自定义上传的域名
     * @return
     */
    public static Region regionHD() {
        return (new Region.Builder()).
                srcUpHost("upload-z2.qiniup.com").
                accUpHost("upload-z2.qiniup.com").
                iovipHost("iovip-z2.qbox.me").
                rsHost("rs-z2.qbox.me").
                rsfHost("rsf-z2.qbox.me").
                apiHost("api-z2.qiniu.com").build();
    }

    public static void main(String args[]) throws Exception {
        upload();
    }

    public static void upload() throws QiniuException {
        Configuration cfg = new Configuration(regionHD());
        //是否指定https上传，默认true
        //cfg.useHttpsDomains=false;
        UploadManager uploadManager = new UploadManager(cfg);
        StringMap policy = new StringMap();
        String upToken = auth.uploadToken(BUCKET, null, 3600, policy);
        String localFilePath = "E:\\Pictures\\有趣的照片\\2.jpg";
        Response response = uploadManager.put(localFilePath, "view2.jpg", upToken);
        //解析上传成功的结果
        DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
        System.out.println(putRet.key);
    }

}

