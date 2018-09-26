package com.home.crm.utils;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * @Author: xu.dm
 * @Date: 2018/9/25 23:01
 * @Description:
 */
public class EncryptUtils {
    private String salt;
    private String algorithmName;
    private int hashIterations;

    public EncryptUtils(String salt, String algorithmName, int hashIterations) {
        this.salt = salt;
        this.algorithmName = algorithmName;
        this.hashIterations = hashIterations;
    }

    public String encrypt(String str)
    {
        return new SimpleHash(algorithmName,str, ByteSource.Util.bytes(salt),hashIterations).toString();
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getAlgorithmName() {
        return algorithmName;
    }

    public void setAlgorithmName(String algorithmName) {
        this.algorithmName = algorithmName;
    }

    public int getHashIterations() {
        return hashIterations;
    }

    public void setHashIterations(int hashIterations) {
        this.hashIterations = hashIterations;
    }
}
