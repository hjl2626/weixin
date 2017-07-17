package com.hjl.core.message.bean.weixin.sendMsg;

/**
 * Created by hjl on 2017/7/12.
 */
public class SendMsgText extends SendMsgBase {
    
    
    
    private Text text;
    
    
    public static class Text {
        String content;
    
        public String getContent() {
            return content;
        }
    
        public Text setContent(String content) {
            this.content = content;
            return this;
        }
    }
    
    public Text getText() {
        return text;
    }
    
    public SendMsgText setText(Text text) {
        this.text = text;
        return this;
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SendMsgText{");
        sb.append("text=").append(text);
        sb.append('}');
        return sb.toString();
    }
}
