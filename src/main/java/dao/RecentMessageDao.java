package dao;

public interface RecentMessageDao {
    boolean storingMessage(String sendId, String receiveId, String sendtime, String messagetxt);

    boolean isExists(String sendId, String receiveId);

    boolean updateMessage(String sendId, String receiveId, String sendtime, String messagetxt);
}
