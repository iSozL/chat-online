# public static boolean judgeLogin(int id,String pwd)
判断登录，登录成功返回true
# public static boolean createUser(String pwd, String name, int sex, String birthday, String address, String phone, String signature)
密码，用户名，性别为必填项，性别：0为保密，1为男，2为女，其他可填null
# public static boolean addFriend(int receiveId,int sendId)
不带问候消息的添加好友方式
# public static boolean addFriend(int receiveId,int sendId,String massage)
带有问候消息的添加好友方式
# public static boolean sendMassage(int receiveId,int sendId,String massage)
发送消息
# public static List getUnreadMassage(int receiveId,int sendId)
获取某一个人的未读消息
# public static List getAllUnreadMassage(int receiveId)
获取所有未读消息
# public static boolean addGroup(int id, String groupName)
添加分组
# public static void readMassage(int receiveId,int sendId)
消息阅读
