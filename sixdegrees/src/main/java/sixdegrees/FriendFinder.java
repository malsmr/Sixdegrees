package sixdegrees;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Implement the function in this class that will
 */
public class FriendFinder {

    private final SocialNetworkService sns;

    FriendFinder(SocialNetworkService socialNetworkService) {

        sns = socialNetworkService;
    }

    /**
     * Returns an ordered list of connectivity if the given personAId has a connection of friend relationships to personZId
     * within the specified degreesOfSeparation, otherwise return null.
     */
    public List<String> findShortestPath(String personAId, String personZId, int degreesOfSeparation) {
    	 // TODO: (BONUS) Implement this function by calling the 'injected' SocialNetworkService to search friends of the Person Ids...
    	//ripple effect
    	List<String> finalPath = findShortestRipple(personAId , personZId, degreesOfSeparation , new ArrayList<String>());
    	if(finalPath != null ) return finalPath;
    	else return null;
    }
    
    public List<String> findShortestRipple(String personAId, String personZId, int degreesOfSeparation, List<String> pathSoFar) {
    	//continue searching till ripple length ends
    	if(degreesOfSeparation > 0) {
    		//find all friends
    		Collection<String> myFriends = sns.findFriends(personAId);
    		pathSoFar.add(personAId);
    		
    		//To store all the paths from my node - Person A 's node - also sorted by size
    		SortedMap<Integer, List<String>> sm =  new TreeMap<Integer, List<String>>(); 
    		
    		//if I(PersonA) have person Z as friend - add to list and return
    		if(myFriends.contains(personZId)) {
    			pathSoFar.add(personZId);
    			return pathSoFar;
    		} 
    		
    		else {
    			for(String frnd : myFriends) {
    				if(!pathSoFar.contains(frnd)) {
    					//System.out.println("Path --"+ pathSoFar);
    					
    					List<String> tempPath = new ArrayList<String>();
    					tempPath.addAll(pathSoFar); //pass a new path to all my friends
    					
						List<String> possiblePath = findShortestRipple(frnd , personZId, degreesOfSeparation -1, tempPath);
	    	    		if(possiblePath != null) {
	    	    			//System.out.println("Path from --"+ "for frnd " + frnd + " is " + possiblePath);
	    	    			sm.put(possiblePath.size(), possiblePath);//add all possible paths to my list
	    	    				
	    	    		}
    		        } 
    	    	}
    			//System.out.println("All paths ----"+sm);
    			
    			//Return shortest path at Index 1
    			if(!sm.isEmpty()) {
    			Set s = sm.entrySet(); 
		        Iterator i = s.iterator(); 
		        Map.Entry m = (Map.Entry)i.next(); 
		  
		        int key = (Integer)m.getKey(); 
		        List<String> value = (List<String>)m.getValue(); 
		  
		        return value;
    			}
    		}
    	}
    	
        return null;
    }

}
