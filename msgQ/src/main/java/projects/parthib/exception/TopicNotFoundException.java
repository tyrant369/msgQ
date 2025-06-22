package projects.parthib.exception;

public class TopicNotFoundException extends AppException {
    public TopicNotFoundException(String topicName) {
        super(ErrorCode.TOPIC_NOT_FOUND,  "Topic Not Found : " + topicName);
    }
}
