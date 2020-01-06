
package com.imuons.shopntrips.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("ticket_status")
    @Expose
    private String ticketStatus;
    @SerializedName("ticket_id")
    @Expose
    private Integer ticketId;
    @SerializedName("chat_msg")
    @Expose
    private List<ChatMsg> chatMsg = null;

    public String getTicketStatus() {
        return ticketStatus;
    }

    public void setTicketStatus(String ticketStatus) {
        this.ticketStatus = ticketStatus;
    }

    public Integer getTicketId() {
        return ticketId;
    }

    public void setTicketId(Integer ticketId) {
        this.ticketId = ticketId;
    }

    public List<ChatMsg> getChatMsg() {
        return chatMsg;
    }

    public void setChatMsg(List<ChatMsg> chatMsg) {
        this.chatMsg = chatMsg;
    }

}
