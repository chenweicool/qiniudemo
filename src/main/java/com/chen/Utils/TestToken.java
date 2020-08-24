package com.chen.Utils;

import com.qiniu.util.Auth;

/**
 * 测试管理凭证
 */
public class TestToken {

    public static void main(String[] args) {

        Auth auth = Auth.create(UserConstant.ACCESS_KEy6,UserConstant.SECRET_KEY6);
      //  auth.qiniuAuthorization("")
    }
}
