import java.util.List;
import java.util.UUID;

public class StackOverflow {

    public enum PostType {
        ANSWER, QUESTION, COMMENT
    }

    public abstract class Post {
        String userId;
        String body;
        PostType postType;

        public Post(String userId, String body, PostType postType) {
            this.userId = userId;
            this.body = body;
            this.postType = postType;
        }
    }

    public class Question extends Post {
        int upVotes;
        List<String> tags;

        public Question(String userId, String body, List<String> tags) {
            super(userId, body, PostType.QUESTION);
            this.tags = tags;
            this.upVotes = 0;
        }

        public List<String> getTags() {
            return tags;
        }

        public void setTags(List<String> tags) {
            this.tags = tags;
        }

        public int getUpVotes() {
            return upVotes;
        }

        public void setUpVotes(int upVotes) {
            this.upVotes = upVotes;
        }
        public synchronized void incUpvote() {
            upVotes++;
        }
    }

    public class Answer extends Post {
        int upVotes;
        List<String> tags;
        String questionId;

        public Answer(String userId, String body) {
            super(userId, body, PostType.ANSWER);
        }
    }

    public class User {
        String id;
        int reputationScore;

    }


}