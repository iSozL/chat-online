# User类
### 包含元素（私有元素有get和set方法）
* private int id;
> 存储user的账号
* private String userName;
> 存储user的用户名
* private int userSex;
> 存储user的性别
* private String userBirthday;
> 存储user的生日
* private String userAddress;
> 存储user的地址
* private String userPhone;
> 存储user的手机号码
* private String userSignature;
> 存储user的个性签名
* private List<String> userGroup;
> 存储user的所有好友组
* private List<UserFriend> userFriend;
> 存储user的所有好友信息

### 构造函数
* public User(int id, String userName, int userSex, String userBirthday, String userAddress, String userPhone, String userSignature, List<String> userGroup, List<UserFriend> userFriend)

---
# UserFriend类
### 包含元素（私有元素有get和set方法）
* private int userId;
> 存储好友id
* private String userGroup;
> 存储好友组别
* private int isAccept;
> 是否已是好友，0表示已向其发送好友申请，1表示已是好友

### 构造函数
* public UserFriend(int userId,String userGroup,int isAccept)


---
# Evaluate类
### 包含元素（私有元素有get和set方法）
* private int userId1;
> 存储被评价人id
* private int userId2;
> 评价人id
* private String evaluate;
> 评价内容
### 构造函数
* public Evaluate(int userId1, int userId2, String evaluate)

---
# DBFunction介绍
* public static User judgeLogin(int id, String pwd)
> 判断登录，登录成功返回登录用户的User类，否则为空
* public static boolean createUser(String pwd, String name, int sex, String birthday, String address, String phone, String signature)
> 密码，用户名，性别为必填项，性别：0为保密，1为男，2为女，其他如果没有输入请传入null
* public static User getUserinfo(int id)
> 获取用户信息，可用于查询好友信息、加好友时显示搜索信息，返回值为User类，没有用户时查询的返回值为null
* public static User updateUserInfo(int id,String userName,int userSex,String userBirthday,String userAddress,String userPhone,String userSignature)
> 用于修改个人信息，id、userName、userSex（0,1,2）为必填项，其他可填null
* public static boolean addFriend(int sendId,int receiveId,String massage,String group)
> 用于向用户发送好友申请
* public static List getGroup(int userId)
> 获取用户所有分组，用户修改、增加、删除分组时可调用此函数用于更新
* public static boolean acceptFriend(int userId1,int userId2,String group)
> 用于接收好友申请userId1为当前用户，userId2为发来申请用户，group为放入的分组
* public static boolean sendMassage(int sendId,int receiveId,String massage)
> sendId为发送的用户，意味着就是当前用户，receiveId为接收用户，massage为发送的信息。
* public static List getUnreadMassage(int receiveId,int sendId)
> 获取某一个账户发来的未阅读的消息，用于点开某一个用户聊天界面，并显示聊天信息，receiveId为当前账户，sendId为发来消息的账户
* public static List getUnreadMassage(int receiveId)
> 获取所有未读消息，可用于登录时显示未读消息，receiveId为当前账户
* public static void readMassage(int receiveId,int sendId)
> 用户查看某个账户发来的未读消息，将此用户发来的所有消息都改为已读，receiveId为当前账户，sendId为发来消息的账户
* public static boolean deleteReadMassage(int receiveId,int sendId,String massage,String sendTime,int isFriendRequest)
> 传入为消息所有信息，包括接受者、发送者、消息内容、发送时间、是否为好友请求，使之尽量防止多删、错删
* public static boolean addGroup(int id, String groupName)
> 用于用户添加好友分组，id为用户账号，groupName为新组名
* public static boolean deleteGroup(int id, String groupName)
> 删除组别，该组别所有好友移至默认分组，传入参数为用户id及需要删除的组名
* public static boolean deleteFriend(int userId1,int userId2)
> 删除好友，userId1为当前用户，userId2为删除用户，用户删除为双向性
* public static boolean moveFriend(int userId1,int userId2,String group)
> 移动好友分组，userId1为当前用户，userId2为需要移动的好友，group为移动到的分组
* public static boolean addEvaluate(int userId1, int userId2, String evaluate)
> 增加好友评价，userId1为当前账户，userId2为被评价用户，evaluate为评价内容
* public static List showEvaluate(int userId)
> 查看某一用户所有的评价，返回Evaluate类的队列，userId为所要查看用户
* public static boolean deleteEvaluate(int userId1,int userId2,String evaluate)
> 删除某条评价，userId1为被评价人，userId2为评价人，evaluate为评价信息

__注：由于除去DBFunction类以外，其他类所有信息都是必填，所以暂时没有给空构造方法，及少变量的构造方法。__
\
__注：JDBC路径在/com/connmysql/lib/。__