package interleave;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class SolutionTest {

	private String s1;
	private String s2;
	private String s3;
	private boolean isInterleaved;
	
	public SolutionTest(String s1, String s2, String s3, boolean isInterleaved) {
		this.s1 = s1;
		this.s2 = s2;
		this.s3 = s3;
		this.isInterleaved = isInterleaved;
	}
	
	@Parameters
	public static Collection<Object[]> data() {
		Object[][] data = new Object[][] {
			{"aabcc", "dbbca", "aadbbcbcac", true},
			{"aabcc", "dbbca", "aadbbbaccc", false},
			{"", "", "", true},
			{"cabbacccabacbcaabaccacacc", "bccacbacabbabaaacbbbbcbbcacc", "cbccacabbacabbbaacbcacaaacbbacbcaabbbbacbcbcacccacacc", true},
			{"bcbbacbaabaabbbacbbbcbbb", "bcbbacbaabaabbbacbbbcbbb", "bbcbbbcbabacbcbaacabaabaabaabbbaaacccbbabbbb", false},
			{
				"baababbabbababbaaababbbbbbbbbbbaabaabaaaabaaabbaaabaaaababaabaaabaabbbbaabbaabaabbbbabbbababbaaaabab",
				"aababaaabbbababababaabbbababaababbababbbbabbbbbababbbabaaaaabaaabbabbaaabbababbaaaababaababbbbabbbbb",
				"babbabbabbababbaaababbbbaababbaabbbbabbbbbaaabbabaababaabaaabaabbbaaaabbabbaaaaabbabbaabaaaabbbbababbbababbabaabababbababaaaaaabbababaaabbaabbbbaaaaabbbaaabbbabbbbaaabaababbaabababbbbababbaaabbbabbbab",
				false
			},
			{"aa", "ab", "aaba", true},
			{"a", "b", "a", false}
		};
		return Arrays.asList(data);
	}
	
	@Test
	public void testInterleave() {
		Solution solution = new Solution();
		Assert.assertEquals(solution.isInterleave(s1, s2, s3), isInterleaved);
	}
}