package dao;

/**
 * 数据库在线用户列表
 */
public interface OnlineUserDao {
    /**
     * 在线列表添加用户
     *
     * @param userName
     * @return
     */
    boolean addUser(String userName);

    /**
     * 用户离线，在线列表删除该用户
     *
     * @param userName
     * @return
     */
    boolean deleteUser(String userName);

    /**
     * 判断某用户是否在线
     *
     * @param userName
     * @return
     */
    boolean isOnline(String userName);
}
