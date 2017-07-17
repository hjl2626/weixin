package com.hjl.core.message.bean.weixin.sendMsg;

/**
 * Created by hjl on 2017/7/12.
 */
public class SendMsgNews extends SendMsgBase{
    
    /**
     "mpnews":{
        "media_id":"123dsdajkasd231jhksad"
    },
     */
    
    private News mpnews;
    
    public News getMpnews() {
        return mpnews;
    }
    
    public SendMsgNews setMpnews(News mpnews) {
        this.mpnews = mpnews;
        return this;
    }
    
    public static class News {
        private String media_id;
    
        public String getMedia_id() {
            return media_id;
        }
    
        public News setMedia_id(String media_id) {
            this.media_id = media_id;
            return this;
        }
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SendMsgNews{");
        sb.append("mpnews=").append(mpnews);
        sb.append('}');
        return sb.toString();
    }
}
