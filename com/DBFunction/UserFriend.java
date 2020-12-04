package com.DBFunction;

public class UserFriend{
   private int userId;
   private String userGroup;

   public UserFriend(){
       this(0,null);
   }

   public UserFriend(int userId){
       this(userId,null);
   }

   public UserFriend(int userId,String userGroup){
       this.userId=userId;
       this.userGroup=userGroup;
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
}
