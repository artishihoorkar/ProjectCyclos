package com.training.bean;

public class MemberDetailsBean {
private String memberlogin;
private String permissiongroup;
private String comments;
/* Adding new Bean for getter setter methods for test CYTC_067*/
public MemberDetailsBean() {
}

public MemberDetailsBean(String memberlogin, String permissiongroup, String comments) {
	super();
	this.memberlogin = memberlogin;
	this.permissiongroup = permissiongroup;
	this.comments = comments;
	
}
public void setMemberLogin(String memberlogin) {
	this.memberlogin = memberlogin;
	}

public String getMemberLogin() {
		return memberlogin;
	}

public String getPermissionGroup() {
		return permissiongroup;		
	}
	
public void setPermissionGroup(String permissiongroup){
	this.permissiongroup = permissiongroup;
}

public String getComments(){
	return comments;
}
public void setComments(String comments){
	this.comments = comments;
}

}
