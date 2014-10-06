package longestprefix;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * https://oj.leetcode.com/problems/longest-common-prefix/
 * 
 * Write a function to find the longest common prefix string amongst an array of strings.
 * 
 */
public class Solution {
    public String longestCommonPrefix(String[] strs) {
        String longest = "";
        
        SortedMap<String,List<String>> prefixes = new TreeMap<String,List<String>>(new Comparator<String>() {
        	public int compare(String s1, String s2) {
        		if (s1 == null && s2 == null) return 0;
        		if (s1 == null) return -1;
        		if (s2 == null) return 1;
        		
        		if (s1.length() == s2.length()) {
        			return s1.compareTo(s2);
        		}
        		else {
            		return (s2.length() - s1.length());
        		}
        	}
        });
        
        for (int i = 0; i < strs.length; i++) {
        	String current = strs[i];
        	for (int j = 1; j <= current.length(); j++) {
        		String prefix = current.substring(0, j);
        		List<String> currentPrefixes = prefixes.get(prefix);
        		if (currentPrefixes == null) {
        			currentPrefixes = new ArrayList<String>();
        			prefixes.put(prefix, currentPrefixes);
        		}
        		currentPrefixes.add(prefix);
        	}
        }
        
        Set<String> keys = prefixes.keySet();
        for (String key: keys) {
        	List<String> current = prefixes.get(key);
        	if (current != null && current.size() == strs.length) {
        		longest = key;
        		break;
        	}
        }
        
        return longest;
    }
}
