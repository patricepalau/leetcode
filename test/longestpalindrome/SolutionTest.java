package longestpalindrome;

import org.junit.Assert;
import org.junit.Test;

public class SolutionTest {
	@Test
	public void test1() {
		Solution solution = new Solution();
		String s = "RADAR";
		Assert.assertEquals("RADAR", solution.longestPalindrome(s));
	}
	
	@Test
	public void test2() {
		Solution solution = new Solution();
		String s = "ACRADARFFGRWW";
		Assert.assertEquals("RADAR", solution.longestPalindrome(s));
	}
	
	@Test
	public void test3() {
		Solution solution = new Solution();
		String s = "a";
		Assert.assertEquals("a", solution.longestPalindrome(s));
	}
	
	@Test
	public void test4() {
		Solution solution = new Solution();
		String s = "bb";
		Assert.assertEquals("bb", solution.longestPalindrome(s));
	}
	
	@Test
	public void test5() {
		Solution solution = new Solution();
		String s = "abb";
		Assert.assertEquals("bb", solution.longestPalindrome(s));
	}	
	
	@Test
	public void test6() {
		Solution solution = new Solution();
		String s = "lqlvciwekzxapmjxyddlaoqhfhwphamsyfwjinkfvciucjhdgwodvmnpkibumexvlsxxumvrznuuyqfavmgwfnsvfbrvqhlvhpxaqehsiwxzlvvtxsnmlilbnmvnxyxitxwoahjricdjdncvartepfpdfndxqoozkfpdmlpvshzzffsspdjzlhmamqooooor";
		Assert.assertEquals("ooooo", solution.longestPalindrome(s));
	}	
	
	@Test
	public void test7() {
		Solution solution = new Solution();
		String s = "daomdukomcayjwgmmetypncdeixarhbectjcwftjjtwjrctixtrsehegwlfotpljeeqhntacfihecdjysgfmxxbjfeskvvfqdoedfxriujnoeteleftsjgdsagqvcgcdjbxgmguunhbjxyajutohgbdwqtjghdftpvidkbftqbpeahxbfyamonazvubzirhqseetaneptnpjbtrtitttxsyjckjvwtrmuwgidkofvobrwrffzrpnxbectsydbvswstfiqranjzhsebjnmprjoirqkgttahrivkcjtitdcpohwwerwgrdivqbltfnigavydxpmitttjjzyrmpaptkczzgnsovebvxezkkovgqegctxacvjfqwtiycnhartzczcgosiquhmdbljjzyrnmffcwnkyzytnsvyzayrieqyrfpxintbbiyrawxlguzaisedwabpzvevlejadztuclcpwvonehkhknicdksdcnjfaoeaqhcdkdtywilwewadcnaprcasccdcnvdgjdqirrsqwzhqqorlhbgpelpupdvuynzybcqkffnnpocidkkigimsa";
		Assert.assertEquals("tjjt", solution.longestPalindrome(s));
	}
}
