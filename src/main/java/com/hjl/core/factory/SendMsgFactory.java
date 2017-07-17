package com.hjl.core.factory;



import com.hjl.base.constants.WeiXinMsgType;
import com.hjl.core.message.bean.weixin.sendMsg.SendMsgBase;
import com.hjl.core.message.bean.weixin.sendMsg.SendMsgImg;
import com.hjl.core.message.bean.weixin.sendMsg.SendMsgNews;
import com.hjl.core.message.bean.weixin.sendMsg.SendMsgText;

import java.util.Arrays;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: hjl
 * Date: 2017-07-13
 * Time: 10:16
 */
public final class SendMsgFactory {
    
    private SendMsgFactory(){}
    
    
    /**
     * 获取群发文本消息
     * @param users openId 列表
     * @param content 文本内容
     * @return SendMsgBase
     */
    public static SendMsgBase getTextMsg(List<String> users , String content){
    
        SendMsgText text = new SendMsgText();
        text.setText(new SendMsgText.Text().setContent(content));
        text.setMsgtype(WeiXinMsgType.MSG_TYPE_TEXT)
                .setTouser(users);
        
        return text;
    }
    
    /**
     * 获取群发文本消息
     * @param isToAll 是否发送全部
     * @param tagsId 分组ID
     * @param content 文本内容
     * @return SendMsgBase
     */
    public static SendMsgBase getTextMsg(boolean isToAll , Integer tagsId , String content){
        
        SendMsgText text = new SendMsgText();
        text.setText(new SendMsgText.Text().setContent(content));
        text.setMsgtype(WeiXinMsgType.MSG_TYPE_TEXT)
                .setFilter(new SendMsgBase.Filter().setIs_to_all(isToAll).setTag_id(tagsId));
        return text;
    }
    
    /**
     * 获取群发图片消息
     * @param users openId 列表
     * @param imgId  微信media_id
     * @return
     */
    public static SendMsgBase getImgMsg(List<String> users , String imgId){
        
        SendMsgImg img = new SendMsgImg();
       
        img.setImage(new SendMsgImg.Image().setMedia_id(imgId)).setTouser(users).setMsgtype(WeiXinMsgType.MSG_TYPE_IMAGE);
        
        return img;
    }
    
    /**
     *  获取群发图片消息
     * @param is2All 是否全部发送
     * @param tagsId 分组ID
     * @param imgId 图片ID
     * @return SendMsgBase
     */
    public static SendMsgBase getImgMsg(boolean is2All , Integer tagsId ,String imgId){
        
        SendMsgImg img = new SendMsgImg();
        
        img.setImage(new SendMsgImg.Image().setMedia_id(imgId))
                .setFilter(new SendMsgBase.Filter()
                        .setTag_id(tagsId)
                        .setIs_to_all(is2All))
                .setMsgtype(WeiXinMsgType.MSG_TYPE_IMAGE);
        
        return img;
    }
    
    
    /**
     * 获取群发图文消息
     * @param is2All 是否全部发送
     * @param tagsId 分组ID
     * @param newsId 图文消息ID
     * @return SendMsgBase
     */
    public static SendMsgBase getNewsMsg(boolean is2All , Integer tagsId ,String newsId){
        
        SendMsgNews news = new SendMsgNews();
        
        news.setMpnews(new SendMsgNews.News().setMedia_id(newsId))
                .setFilter(new SendMsgBase.Filter()
                        .setTag_id(tagsId)
                        .setIs_to_all(is2All))
                .setMsgtype(WeiXinMsgType.MSG_TYPE_MP_NEWS)
                .setSend_ignore_reprint(1);
        
        return news;
    }
    
    /**
     * 获取群发图文消息
     * @param toUsers openId 列表
     * @param newsId 图文消息ID
     * @return SendMsgBase
     */
    public static SendMsgBase getNewsMsg(List<String> toUsers , String newsId){
    
        SendMsgNews news = new SendMsgNews();
    
        news.setMpnews(new SendMsgNews.News().setMedia_id(newsId))
                .setTouser(toUsers)
                .setMsgtype(WeiXinMsgType.MSG_TYPE_MP_NEWS)
                .setSend_ignore_reprint(1);
    
        return news;
    }
    
    public static void main(String[] args) throws Exception {
    	SendMsgBase msg = getTextMsg(Arrays.asList("o5pL8s1YgF5xbnIJeT5sC5AGo5sM" ,"oARAQw9EKiXezkdbUBqY8b6v4a9g") , "大家好");
    
//        System.out.println(
//                AuthorizationWechatApi.sendMsgByOpenIds(msg ,
//                        "nOfXo0kxc6TYvLklfPWGDcz24Q4egQ9IzRK1LdDK-Qx1XfUTl0dtO4hIz4UL" +
//                                "Und8Na36UMVi643LDaS_Os9zXaPXAuI7wygXHJpDRfQ0l4UXEWeAEAOVN")
//        );
    }
    
}
