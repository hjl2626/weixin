package com.hjl.core.message.bean.weixin.sendMsg;

import java.util.List;

/**
 * Created by hjl on 2017/7/12.
 */
public class SendMsgBase {
    
    /**
         "filter":{
            "is_to_all":false,
                    "tag_id":2
        },
        "touser":[
        "OPENID1",
        "OPENID2"
        ],
        "mpnews":{
            "media_id":"123dsdajkasd231jhksad"
        },
        "msgtype":"mpnews",
        "send_ignore_reprint":0
    
    */
    private Filter filter;
    
    private List<String> touser;
    
    private String msgtype;
    
    private Integer send_ignore_reprint;
    
    public static class Filter {
        
        
        private Boolean is_to_all;
        
        private Integer tag_id;
    
        public Boolean getIs_to_all() {
            return is_to_all;
        }
    
        public Filter setIs_to_all(Boolean is_to_all) {
            this.is_to_all = is_to_all;
            return this;
        }
    
        public Integer getTag_id() {
            return tag_id;
        }
    
        public Filter setTag_id(Integer tag_id) {
            this.tag_id = tag_id;
            return this;
        }
    }
    
    public Filter getFilter() {
        return filter;
    }
    
    public SendMsgBase setFilter(Filter filter) {
        this.filter = filter;
        return this;
    }
    
    public List<String> getTouser() {
        return touser;
    }
    
    public SendMsgBase setTouser(List<String> touser) {
        this.touser = touser;
        return this;
    }
    
    public String getMsgtype() {
        return msgtype;
    }
    
    public SendMsgBase setMsgtype(String msgtype) {
        this.msgtype = msgtype;
        return this;
    }
    
    public Integer getSend_ignore_reprint() {
        return send_ignore_reprint;
    }
    
    public SendMsgBase setSend_ignore_reprint(Integer send_ignore_reprint) {
        this.send_ignore_reprint = send_ignore_reprint;
        return this;
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SendMsgBase{");
        sb.append("filter=").append(filter);
        sb.append(", touser=").append(touser);
        sb.append(", msgtype='").append(msgtype).append('\'');
        sb.append(", send_ignore_reprint=").append(send_ignore_reprint);
        sb.append('}');
        return sb.toString();
    }
}
