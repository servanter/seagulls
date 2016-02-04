package com.crop.seagulls.bean.wx;

import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.Element;

import com.crop.seagulls.util.Logger;
import com.crop.seagulls.util.weixin.AesException;
import com.crop.seagulls.util.weixin.WXBizMsgCrypt;

public class WxRequest {

    private static final Logger LOGGER = Logger.getLogger(WxRequest.class);

    private String msgSignature;

    private String timestamp;

    private String nonce;

    private String encryptType;

    private Document document;

    private WXBizMsgCrypt msgCrypt;

    private String requestRawText;

    private String fromUserName;

    private String toUserName;

    private Event event;

    private MsgType msgType;

    private String content;

    public WxRequest(String appId, String encodingAesKey, String token, Document document, String msgSignature, String timestamp, String nonce, String encryptType) {
        this.document = document;
        this.msgSignature = msgSignature;
        this.timestamp = timestamp;
        this.nonce = nonce;
        this.encryptType = encryptType;

        try {
            msgCrypt = new WXBizMsgCrypt(token, encodingAesKey, appId);
            requestRawText = msgCrypt.decryptMsg(msgSignature, timestamp, nonce, document.asXML());
            LOGGER.info("[Wx] request:[{0}]", requestRawText);

            Element root = document.getRootElement();
            Iterator<Element> it = root.elementIterator();
            while (it.hasNext()) {
                Element e = it.next();
                if (e.getName().equals("ToUserName")) {
                    this.toUserName = e.getText();
                } else if (e.getName().equals("FromUserName")) {
                    this.fromUserName = e.getText();
                } else if (e.getName().equals("MsgType")) {
                    msgType = MsgType.valueOf(e.getText().toUpperCase());
                } else if (e.getName().equals("Event")) {
                    event = Event.valueOf(e.getText().toUpperCase());
                } else if (e.getName().equals("Content")) {
                    content = e.getText();
                }
            }

        } catch (AesException e) {
            LOGGER.error("Can't create wx request", e);
        }
    }

    public String getMsgSignature() {
        return msgSignature;
    }

    public void setMsgSignature(String msgSignature) {
        this.msgSignature = msgSignature;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getNonce() {
        return nonce;
    }

    public void setNonce(String nonce) {
        this.nonce = nonce;
    }

    public String getEncryptType() {
        return encryptType;
    }

    public void setEncryptType(String encryptType) {
        this.encryptType = encryptType;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public WXBizMsgCrypt getMsgCrypt() {
        return msgCrypt;
    }

    public void setMsgCrypt(WXBizMsgCrypt msgCrypt) {
        this.msgCrypt = msgCrypt;
    }

    public String getRequestRawText() {
        return requestRawText;
    }

    public void setRequestRawText(String requestRawText) {
        this.requestRawText = requestRawText;
    }

    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    public String getToUserName() {
        return toUserName;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public MsgType getMsgType() {
        return msgType;
    }

    public void setMsgType(MsgType msgType) {
        this.msgType = msgType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
