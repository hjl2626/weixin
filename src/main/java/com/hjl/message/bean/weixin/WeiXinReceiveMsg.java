package com.hjl.message.bean.weixin;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by hjl on 2017/1/12.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "xml")
public class WeiXinReceiveMsg {
	
	/**
	 * 开发者微信号
	 */
	@XmlElement(name = "ToUserName")
	@JsonProperty("ToUserName")
	private String toUserName;
	
	/**
	 * 发送方帐号（一个OpenID）
	 */
	@XmlElement(name = "FromUserName")
	@JsonProperty("FromUserName")
	private String fromUserName;
	
	/**
	 * 消息创建时间 （整型）
	 */
	@XmlElement(name = "CreateTime")
	@JsonProperty("CreateTime")
	private String createTime;
	
	/**
	 * text image 语音为voice 视频为video 小视频为shortvideo location link event
	 */
	@XmlElement(name = "MsgType")
	@JsonProperty("MsgType")
	private String msgType;
	
	/**
	 * 消息ID 消息id，64位整型
	 */
	@XmlElement(name = "MsgId")
	@JsonProperty("MsgId")
	private String msgId;
	
	/**
	 * 消息ID 消息id，64位整型
	 */
	@XmlElement(name = "Content")
	@JsonProperty("Content")
	private String content;
	
	/**
	 * 图片链接（由系统生成）
	 */
	@XmlElement(name = "PicUrl")
	@JsonProperty("PicUrl")
	private String picUrl;
	
	/**
	 * 图片消息媒体id，可以调用多媒体文件下载接口拉取数据。
	 * 语音消息媒体id，可以调用多媒体文件下载接口拉取数据。
	 * 视频消息媒体id，可以调用多媒体文件下载接口拉取数据。
	 */
	@XmlElement(name = "MediaId")
	@JsonProperty("MediaId")
	private String mediaId;
	
	/**
	 * 语音格式，如amr，speex等
	 */
	@XmlElement(name = "Format")
	@JsonProperty("Format")
	private String format;
	
	/**
	 * 语音识别结果，UTF8编码 需开启语音识别
	 */
	@XmlElement(name = "Recognition")
	@JsonProperty("Recognition")
	private String recognition;
	
	/**
	 * 视频消息缩略图的媒体id，可以调用多媒体文件下载接口拉取数据。
	 * shortvideo and video 都有
	 */
	@XmlElement(name = "ThumbMediaId")
	@JsonProperty("ThumbMediaId")
	private String thumbMediaId;
	
	/**
	 * 地理位置维度
	 */
	@XmlElement(name = "Location_X")
	@JsonProperty("Location_X")
	private String locationX;
	
	/**
	 * 地理位置经度
	 */
	@XmlElement(name = "Location_Y")
	@JsonProperty("Location_Y")
	private String locationY;
	
	/**
	 * 地图缩放大小
	 */
	@XmlElement(name = "Scale")
	@JsonProperty("Scale")
	private String scale;
	
	/**
	 * 地理位置信息
	 */
	@XmlElement(name = "Label")
	@JsonProperty("Label")
	private String label;
	
	/**
	 * 消息标题
	 */
	@XmlElement(name = "Title")
	@JsonProperty("Title")
	private String title;
	
	/**
	 * 消息描述
	 */
	@XmlElement(name = "Description")
	@JsonProperty("Description")
	private String description;
	
	/**
	 * 消息链接
	 */
	@XmlElement(name = "Url")
	@JsonProperty("Url")
	private String url;
	
	/**
	 * 事件类型，subscribe(订阅)、unsubscribe(取消订阅)
	 */
	@XmlElement(name = "Event")
	@JsonProperty("Event")
	private String event;
	
	/**
	 * 事件KEY值，qrscene_为前缀，后面为二维码的参数值
	 * 事件KEY值，与自定义菜单接口中KEY值对应
	 */
	@XmlElement(name = "EventKey")
	@JsonProperty("EventKey")
	private String eventKey;
	
	/**
	 * 二维码的ticket，可用来换取二维码图片
	 */
	@XmlElement(name = "Ticket")
	@JsonProperty("Ticket")
	private String ticket;
	
	/**
	 * 地理位置纬度
	 */
	@XmlElement(name = "Latitude")
	@JsonProperty("Latitude")
	private String latitude;
	
	/**
	 * 地理位置经度
	 */
	@XmlElement(name = "Longitude")
	@JsonProperty("Longitude")
	private String longitude;
	
	/**
	 * 地理位置精度
	 */
	@XmlElement(name = "Precision")
	@JsonProperty("Precision")
	private String precision;
	
	public String getToUserName() {
		return toUserName;
	}
	
	public WeiXinReceiveMsg setToUserName(String toUserName) {
		this.toUserName = toUserName;
		return this;
	}
	
	public String getFromUserName() {
		return fromUserName;
	}
	
	public WeiXinReceiveMsg setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
		return this;
	}
	
	public String getCreateTime() {
		return createTime;
	}
	
	public WeiXinReceiveMsg setCreateTime(String createTime) {
		this.createTime = createTime;
		return this;
	}
	
	public String getMsgType() {
		return msgType;
	}
	
	public WeiXinReceiveMsg setMsgType(String msgType) {
		this.msgType = msgType;
		return this;
	}
	
	public String getMsgId() {
		return msgId;
	}
	
	public WeiXinReceiveMsg setMsgId(String msgId) {
		this.msgId = msgId;
		return this;
	}
	
	public String getContent() {
		return content;
	}
	
	public WeiXinReceiveMsg setContent(String content) {
		this.content = content;
		return this;
	}
	
	public String getPicUrl() {
		return picUrl;
	}
	
	public WeiXinReceiveMsg setPicUrl(String picUrl) {
		this.picUrl = picUrl;
		return this;
	}
	
	public String getMediaId() {
		return mediaId;
	}
	
	public WeiXinReceiveMsg setMediaId(String mediaId) {
		this.mediaId = mediaId;
		return this;
	}
	
	public String getFormat() {
		return format;
	}
	
	public WeiXinReceiveMsg setFormat(String format) {
		this.format = format;
		return this;
	}
	
	public String getRecognition() {
		return recognition;
	}
	
	public WeiXinReceiveMsg setRecognition(String recognition) {
		this.recognition = recognition;
		return this;
	}
	
	public String getThumbMediaId() {
		return thumbMediaId;
	}
	
	public WeiXinReceiveMsg setThumbMediaId(String thumbMediaId) {
		this.thumbMediaId = thumbMediaId;
		return this;
	}
	
	public String getLocationX() {
		return locationX;
	}
	
	public WeiXinReceiveMsg setLocationX(String locationX) {
		this.locationX = locationX;
		return this;
	}
	
	public String getLocationY() {
		return locationY;
	}
	
	public WeiXinReceiveMsg setLocationY(String locationY) {
		this.locationY = locationY;
		return this;
	}
	
	public String getScale() {
		return scale;
	}
	
	public WeiXinReceiveMsg setScale(String scale) {
		this.scale = scale;
		return this;
	}
	
	public String getLabel() {
		return label;
	}
	
	public WeiXinReceiveMsg setLabel(String label) {
		this.label = label;
		return this;
	}
	
	public String getTitle() {
		return title;
	}
	
	public WeiXinReceiveMsg setTitle(String title) {
		this.title = title;
		return this;
	}
	
	public String getDescription() {
		return description;
	}
	
	public WeiXinReceiveMsg setDescription(String description) {
		this.description = description;
		return this;
	}
	
	public String getUrl() {
		return url;
	}
	
	public WeiXinReceiveMsg setUrl(String url) {
		this.url = url;
		return this;
	}
	
	public String getEvent() {
		return event;
	}
	
	public WeiXinReceiveMsg setEvent(String event) {
		this.event = event;
		return this;
	}
	
	public String getEventKey() {
		return eventKey;
	}
	
	public WeiXinReceiveMsg setEventKey(String eventKey) {
		this.eventKey = eventKey;
		return this;
	}
	
	public String getTicket() {
		return ticket;
	}
	
	public WeiXinReceiveMsg setTicket(String ticket) {
		this.ticket = ticket;
		return this;
	}
	
	public String getLatitude() {
		return latitude;
	}
	
	public WeiXinReceiveMsg setLatitude(String latitude) {
		this.latitude = latitude;
		return this;
	}
	
	public String getLongitude() {
		return longitude;
	}
	
	public WeiXinReceiveMsg setLongitude(String longitude) {
		this.longitude = longitude;
		return this;
	}
	
	public String getPrecision() {
		return precision;
	}
	
	public WeiXinReceiveMsg setPrecision(String precision) {
		this.precision = precision;
		return this;
	}
}
