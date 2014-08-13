package validsudoku;

import java.util.HashSet;
import java.util.Set;

public class Solution {
	private char EMPTY_CELL = '.';
	
	public boolean isValidSudoku(char[][] board) {
		return checkRows(board) && checkColumns(board) && checkSubboxes(board);
    }
	
	private boolean checkRows(char[][] board) {
		boolean isValid = true;
		for (int i = 0; i < board.length && isValid; i++) {
			isValid = !hasDuplicates(board[i]);
		}
		return isValid;
	}
	
	// [ [ /* 1st row */ ], [ /* 2nd row*/ ], [col1, col2, ...] 
	private boolean checkColumns(char[][] board) {
		boolean isValid = true;
		for (int i = 0; i < board.length && isValid; i++) {
			char[] column = new char[board.length];
			for (int j = 0; j < board.length; j++) {
				column[j] = board[j][i];
			}
			isValid = !hasDuplicates(column);
		}
		
		return isValid;
	}
	
	private boolean checkSubboxes(char[][] board) {
		boolean isValid = true;
		for (int i = 0; i < 3 && isValid; i++) {
			for (int j = 0; j < 3 && isValid; j++) {
				// check subbox i,j
				char[] a = new char[board.length];
				int index = 0;
				for (int row = 3*i; row < 3*i + 3; row++) {
					for (int col = 3*j; col < 3*j + 3; col++) {
						a[index] = board[row][col];
						index++;
					}
				}
				isValid = !hasDuplicates(a);
			}
		}
		
		return isValid;
	}
	
	private boolean hasDuplicates(char[] a) {
		boolean hasDuplicate = false;
		// HashSet will tell us if we try to insert
		// a character which is already in the set
		Set<Character> temp = new HashSet<Character>();
		for (int i = 0; i < a.length && !hasDuplicate; i++) {
			if (a[i] == EMPTY_CELL) continue;
			hasDuplicate = !temp.add(a[i]);
		}
		return hasDuplicate;
	}
}
