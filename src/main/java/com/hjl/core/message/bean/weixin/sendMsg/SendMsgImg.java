package com.hjl.core.message.bean.weixin.sendMsg;

/**
 * Created by hjl on 2017/7/12.
 */
public class SendMsgImg extends SendMsgBase{
    
    /**
     "image":{
        "media_id":"123dsdajkasd231jhksad"
    },
     */

    private Image image;
    
    public Image getImage() {
        return image;
    }
    
    public SendMsgImg setImage(Image image) {
        this.image = image;
        return this;
    }
    
    public static class Image {
       private String media_id;
    
        public String getMedia_id() {
            return media_id;
        }
    
        public Image setMedia_id(String media_id) {
            this.media_id = media_id;
            return this;
        }
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SendMsgImg{");
        sb.append("image=").append(image);
        sb.append('}');
        return sb.toString();
    }
}
