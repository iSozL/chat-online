package com.DBFunction;

public class UserFriend{
   private int userId;
   private String userGroup;
   private int isAccept;

   public UserFriend(){
       this(0,null,0);
   }

   public UserFriend(int userId){
       this(userId,null,0);
   }

   public UserFriend(int userId,String userGroup,int isAccept){
       this.userId=userId;
       this.userGroup=userGroup;
       this.isAccept = isAccept;
   }

   public int getUserId() {
       return userId;
   }

   public void setUserId(int userId) {
       this.userId = userId;
   }

   public String getUserGroup() {
       return userGroup;
   }

   public void setUserGroup(String userGroup) {
       this.userGroup = userGroup;
   }

    public int getIsAccept() {
        return isAccept;
    }

    public void setIsAccept(int isAccept) {
        this.isAccept = isAccept;
    }

    @Override
    public String toString() {
        return "UserFriend{" +
                "userId=" + userId +
                ", userGroup='" + userGroup + '\'' +
                ", isAccept=" + isAccept +
                '}';
    }
}
