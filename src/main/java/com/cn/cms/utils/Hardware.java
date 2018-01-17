package com.cn.cms.utils;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * 硬件操作
 * Created by Administrator on 2018/1/17 0017.
 */
public class Hardware {
    public static String getLocalHost() throws UnknownHostException{
        InetAddress ia = InetAddress.getLocalHost();
        return String.valueOf(ia);
    }
    public static String getLocalMac() throws SocketException,UnknownHostException{
        InetAddress ia = InetAddress.getLocalHost();
        //获取网卡，获取地址
        byte[] mac = NetworkInterface.getByInetAddress(ia).getHardwareAddress();
        StringBuffer sb = new StringBuffer("");
        for(int i=0; i<mac.length; i++) {
            if(i!=0) {
                sb.append("-");
            }
            //字节转换为整数
            int temp = mac[i]&0xff;
            String str = Integer.toHexString(temp);
            if(str.length()==1) {
                sb.append("0"+str);
            }else {
                sb.append(str);
            }
        }
        return sb.toString().toUpperCase();
    }
}
