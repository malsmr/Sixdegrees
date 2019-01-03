package sixdegrees;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class FriendFinderTest {

    @Test
    public void findRelationshipPathBonusQuestionTest() {

    	SNSImpl sns = new SNSImpl();
        sns.addFriend("Kevin", "UserB");
        sns.addFriend("Kevin", "UserS");
        sns.addFriend("UserB", "UserC");
        sns.addFriend("UserA", "UserD");
        sns.addFriend("UserX", "UserC");
        sns.addFriend("UserY", "UserX");
        sns.addFriend("Bacon", "UserY");

        FriendFinder ff = new FriendFinder(sns);

        Assert.assertEquals(null, 
        		ff.findShortestPath("UserA", "Bacon", 5));

        // Create a shorter path that will be accessed later in the return collection (list in this test case) of friends
        sns.addFriend("UserS", "UserX");
        sns.addFriend("UserX", "Bacon");
        
        Assert.assertEquals(Arrays.asList("Kevin", "UserS", "UserX",  "Bacon"),
        		ff.findShortestPath("Kevin", "Bacon", 6));
    }
}
