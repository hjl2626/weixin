package com.hjl.core.message.bean.weixin.sendMsg;

/**
 * Created by hjl on 2017/7/12.
 */
public class SendMsgVideo {
    
    /**
      "mpvideo":{
        "media_id":"IhdaAQXuvJtGzwwc0abfXnzeezfO0NgPK6AQYShD8RQYMTtfzbLdBIQkQziv2XJc"
    },
     */
    
    private Video mpvideo;
    
    public Video getMpvideo() {
        return mpvideo;
    }
    
    public SendMsgVideo setMpvideo(Video mpvideo) {
        this.mpvideo = mpvideo;
        return this;
    }
    
    public static class Video {
        private String media_id;
    
        public String getMedia_id() {
            return media_id;
        }
    
        public Video setMedia_id(String media_id) {
            this.media_id = media_id;
            return this;
        }
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SendMsgVideo{");
        sb.append("mpvideo=").append(mpvideo);
        sb.append('}');
        return sb.toString();
    }
}
