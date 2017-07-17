package com.hjl.core.message.bean.weixin.sendMsg;

/**
 * Created by hjl on 2017/7/12.
 */
public class SendMsgVoice extends SendMsgBase{
    /**
     "voice":{
        "media_id":"123dsdajkasd231jhksad"
    },
     */
    
    private Voice voice;
    
    
    public Voice getVoice() {
        return voice;
    }
    
    public SendMsgVoice setVoice(Voice voice) {
        this.voice = voice;
        return this;
    }
    
    public static class Voice {
        private String media_id;
    
        public String getMedia_id() {
            return media_id;
        }
    
        public Voice setMedia_id(String media_id) {
            this.media_id = media_id;
            return this;
        }
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SendMsgVoice{");
        sb.append("voice=").append(voice);
        sb.append('}');
        return sb.toString();
    }
}
