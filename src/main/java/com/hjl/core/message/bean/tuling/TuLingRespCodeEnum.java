package com.hjl.core.message.bean.tuling;

/**
 * Created by hjl on 2017/1/11.
 */
public enum TuLingRespCodeEnum {

	/* 消息类型 */
	TEXT("100000","文本类"),
	LINK("200000","链接类"),
	NEWS("302000","新闻类"),
	COOKBOOK("308000","菜谱类"),
	SONG("313000","儿童版 - 诗歌类"),
	POEM("314000","儿童版 - 诗词类"),

	/* 异常 */
	KEYERROR("40001","参数key错误"),
	INFONULL("40002","请求内容info为空"),
	TIMEOVER("40004","当天请求次数已使用完"),
	FORMATERROR("40007","数据格式异常");

	/** 编码 */
	private String code;

	/** 描述 */
	private String description;

	TuLingRespCodeEnum(String code, String description){
		this.code = code;
		this.description = description;
	}

	public String code() {
		return code;
	}

	public String description() {
		return description;
	}
}
