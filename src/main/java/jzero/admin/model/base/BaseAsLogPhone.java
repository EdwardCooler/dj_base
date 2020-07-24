package jzero.admin.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseAsLogPhone<M extends BaseAsLogPhone<M>> extends Model<M> implements IBean {

	public M setId(java.lang.Integer id) {
		set("id", id);
		return (M)this;
	}
	
	public java.lang.Integer getId() {
		return getInt("id");
	}

	public M setTel(java.lang.String tel) {
		set("tel", tel);
		return (M)this;
	}
	
	public java.lang.String getTel() {
		return getStr("tel");
	}

	public M setApiType(java.lang.Integer apiType) {
		set("apiType", apiType);
		return (M)this;
	}
	
	public java.lang.Integer getApiType() {
		return getInt("apiType");
	}

	public M setStatus(java.lang.Boolean status) {
		set("status", status);
		return (M)this;
	}
	
	public java.lang.Boolean getStatus() {
		return get("status");
	}

	public M setNote(java.lang.String note) {
		set("note", note);
		return (M)this;
	}
	
	public java.lang.String getNote() {
		return getStr("note");
	}

	public M setDateline(java.util.Date dateline) {
		set("dateline", dateline);
		return (M)this;
	}
	
	public java.util.Date getDateline() {
		return get("dateline");
	}

	public M setUid(java.lang.Integer uid) {
		set("uid", uid);
		return (M)this;
	}
	
	public java.lang.Integer getUid() {
		return getInt("uid");
	}

	public M setIp(java.lang.String ip) {
		set("ip", ip);
		return (M)this;
	}
	
	public java.lang.String getIp() {
		return getStr("ip");
	}

}