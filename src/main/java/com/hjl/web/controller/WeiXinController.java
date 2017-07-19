package com.hjl.web.controller;

import com.hjl.base.utils.GsonUtil;
import com.hjl.core.exception.BizException;
import com.hjl.core.factory.ReplyMsgFactory;
import com.hjl.core.message.bean.weixin.WeiXinReceiveMsg;
import com.hjl.core.message.bean.weixin.menu.Menu;
import com.hjl.core.message.bean.weixin.replyMsg.WeiXinReplyImgTextMsg;
import com.hjl.core.message.bean.weixin.replyMsg.WeiXinReplyMsg;
import com.hjl.core.service.WeiXinService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * index
 *
 * @author hjl
 * @date 2017/1/9
 */

@Controller
@RequestMapping("/weixin")
public class WeiXinController {
    
    
    private static Set<String> sIds = new HashSet<>();
    @Autowired
    private WeiXinService weiXinService;

    private static Logger logger = LoggerFactory.getLogger(WeiXinController.class);

    @ResponseBody
    @RequestMapping(value = "authorize" ,method = RequestMethod.GET)
    public String AuthorizeGet(String signature, String timestamp, String nonce, String echostr) throws IOException {
        return weiXinService.Authorize(signature, timestamp, nonce, echostr);
    }

    @ResponseBody
    @RequestMapping(value = "authorize" ,method = RequestMethod.POST ,produces = MediaType.APPLICATION_XML_VALUE)
    public WeiXinReplyMsg AuthorizePost(@RequestBody WeiXinReceiveMsg message) throws IOException {
        logger.debug("from weixin msg = {} " , GsonUtil.toJson(message));
    
       

//        List<WeiXinReplyImgTextMsg.Item> items = new ArrayList<>();
//        items.add(new WeiXinReplyImgTextMsg.Item()
//                .setDescription("我瞬间 是是撒 大大打发第三方水电费沙发斯蒂芬是的发送到分水电费水电费水电费水电费水电费水电费双方都爽肤水发顺丰收水电费水电费第三方水电费水电费第三方地方")
//                .setPicUrl("http://img4.imgtn.bdimg.com/it/u=1264996776,4079453714&fm=26&gp=0.jpg")
//                .setTitle("\\ue159七月福利第一波 快来抢啊!")
//                .setUrl("http://img4.imgtn.bdimg.com/it/u=1264996776,4079453714&fm=26&gp=0.jpg"));
//        items.add(new WeiXinReplyImgTextMsg.Item()
//                .setDescription("我瞬间 是是撒 大大打发第三方水电费沙发斯蒂芬是的发送到分水电费水电费水电费水电费水电费水电费双方都爽肤水发顺丰收水电费水电费第三方水我瞬间 是是撒 大大打发第三方水电费沙发斯蒂芬是的发送到分水电费水电费水电费水电费水电费水电费双方都爽肤水发顺丰收水电费水电费第三方水")
//                .setPicUrl("http://pic.58pic.com/58pic/13/61/00/49S58PICvJi_1024.jpg")
//                .setTitle("\ue159科普 | 看完我就傻了 这几条汽车冷知识颠覆了我的三观")
//                .setUrl("http://pic.58pic.com/58pic/13/61/00/49S58PICvJi_1024.jpg"));
//
//        return ReplyMsgFactory.getImgTextMsg(message ,items);
//        return ReplyMsgFactory.getTextMsg(message).setContent("Here is a boy: &#128102;!");
//
//        List<WeiXinReplyImgTextMsg.Item> items = new ArrayList<>();
//        items.add(new WeiXinReplyImgTextMsg.Item()
//                .setDescription("我瞬间 是是撒 大大打发第三方水电费沙发斯蒂芬是的发送到分水电费水电费水电费水电费水电费水电费双方都爽肤水发顺丰收水电费水电费第三方水电费水电费第三方地方")
//                .setPicUrl("http://img4.imgtn.bdimg.com/it/u=1264996776,4079453714&fm=26&gp=0.jpg")
//                .setTitle("\\ue159七月福利第一波 快来抢啊!")
//                .setUrl("http://img4.imgtn.bdimg.com/it/u=1264996776,4079453714&fm=26&gp=0.jpg"));
//        items.add(new WeiXinReplyImgTextMsg.Item()
//                .setDescription("我瞬间 是是撒 大大打发第三方水电费沙发斯蒂芬是的发送到分水电费水电费水电费水电费水电费水电费双方都爽肤水发顺丰收水电费水电费第三方水我瞬间 是是撒 大大打发第三方水电费沙发斯蒂芬是的发送到分水电费水电费水电费水电费水电费水电费双方都爽肤水发顺丰收水电费水电费第三方水")
//                .setPicUrl("http://pic.58pic.com/58pic/13/61/00/49S58PICvJi_1024.jpg")
//                .setTitle("\ue159科普 | 看完我就傻了 这几条汽车冷知识颠覆了我的三观")
//                .setUrl("http://pic.58pic.com/58pic/13/61/00/49S58PICvJi_1024.jpg"));
//
//        return ReplyMsgFactory.getImgTextMsg(message ,items);
        
            return ReplyMsgFactory.getTextMsg(message).setContent("Here is a boy: &#128102;!");
//        return ReplyMsgFactory.getImgMsg(message).setData(new WeiXinReplyImgMsg().setMediaId("OqNd2gHFb1_gzFc7HsalI_QnU1sSm4YDgSG00zfles_GFfvBoqHkaKBnEXJr1M4q"));
//        return weiXinService.chat(message);
    }

    @ResponseBody
    @RequestMapping(value = "getToken")
    public String getToken() throws BizException {
        return weiXinService.getToken();
    }
    
    @ResponseBody
    @RequestMapping(value = "createMenu")
    public String createMenu(@RequestBody Menu menu) throws BizException, IOException {
        return weiXinService.createMenu(menu);
    }
    
    @ResponseBody
    @RequestMapping(value = "test")
    public String test(HttpServletRequest request) throws BizException {
       String id =  request.getSession().getId();
        logger.info(id);
        sIds.add(id);
        return sIds.size()+"";
    }


    public static void print(Object o) {
        System.out.print(o);
    }

    public static void println(Object o) {
        System.out.println(o);
    }

    public static void main(String[] args) throws JAXBException {


    }
}
