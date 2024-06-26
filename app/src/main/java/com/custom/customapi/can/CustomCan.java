package com.custom.customapi.can;

import android.content.Context;

import com.custom.apidemo.MainApp;

public class CustomCan {

    private int canFd = -1;
    private String canx;
    private String baudrate;
    private Context mContext;
    private SocketCan socketCan;

    public CustomCan(Context context) {
        mContext = context;
    }
    public String getCanx() {
        return canx;
    }

    public String getBaudrate() {
        return baudrate;
    }

    public int openCan(String canx, String baudrate)
    {
        canx = canx;
        baudrate = baudrate;
        socketCan = new SocketCan();
        MainApp.getCustomApi().enableCan(canx, baudrate);
        canFd = socketCan.openCan(canx);
        return canFd;
    }
    public int closeCan()
    {
        if(socketCan == null || canFd <= 0) {
            return -1;
        }
        int ret = socketCan.closeCan(canFd);
        MainApp.getCustomApi().disableCan(canx);
        return ret;
    }

    public int writeCan(long canid, long eff, long rtr, int len, int[] data)
    {
        if(socketCan == null || canFd <= 0) {
            return -1;
        }
        return socketCan.writeCan(canFd, canid, eff, rtr, len, data);
    }
    public long[] readCan()
    {
        if(socketCan == null || canFd <= 0) {
            return null;
        }
        return socketCan.readCan(canFd);
    }
}
