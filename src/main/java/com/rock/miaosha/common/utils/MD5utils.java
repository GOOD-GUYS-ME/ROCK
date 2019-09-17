package com.rock.miaosha.common.utils;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.util.ByteSource;

import java.security.MessageDigest;
/**
*@author rock
*@Date 2019/9/17 23:14
*@param 
*@return 
*
*/
public class MD5utils {
    private final String salt;

    public MD5utils(String salt) {
        this.salt = salt;
    }

    public Md5Hash getMd5() throws IllegalAccessException, InstantiationException {
        String s = new String("甲方@按#附件$a%l^d&j* (阿)来得&及^案*例%法$律#就@D！j@a#ngo￥假%两……件&啊*a（lj）lalajla假聊几句啊拉法基");
        char[] chars = s.toCharArray();
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i <chars.length ; i++) {
            if (i %7==5) {
                stringBuffer.append(chars[i]);
            }
        }
        System.out.println("stringBuffer:"+stringBuffer);
        ByteSource bytes = ByteSource.Util.bytes(salt+stringBuffer);
        Md5Hash md5Hash = new Md5Hash(bytes);
        System.out.println("md5Hash:"+md5Hash);
        return md5Hash;
    }
}
