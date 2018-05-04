package com.op.marvel.dc.zhg38.common.source.threadlocal;

import com.op.marvel.dc.zhg38.common.pojo.TbUser;

/**
 * @version 3.0
 * @Author :History.GreatMan.Mao
 * @Description:
 * @Date Created in 11:10 on 2018/5/4.
 */
public class UserThreadLoacal {
    private static ThreadLocal<TbUser> local=new ThreadLocal<>();

    private UserThreadLoacal(){ }

    public static void set(TbUser user){
        local.set(user);
    }

    public  static TbUser get(){
        return  local.get();
    }
}
