package sudoku;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://oj.leetcode.com/problems/sudoku-solver/
 * 
 * Write a program to solve a Sudoku puzzle by filling the empty cells.
 * 
 * Empty cells are indicated by the character '.'.
 * You may assume that there will be only one unique solution.
 *
 */
public class Solution {
	
	// board[3][3]
	//   0 1 2 3 4 5
	// 0 
	// 1
	// 2
	// 3
	// 4
	// 5
	private int n = 0;
	
	private void makeMove(List<Move> moves, Move move) {
		moves.add(move);
		nbFreeCells--;
		board[move.y][move.x] = move.val;
	}
	
	private void unmakeMove(List<Move> moves, Move move) {
		moves.remove(move);
		nbFreeCells++;
		board[move.y][move.x] = EMPTY_CELL;
	}
	
	private boolean isSolution(List<Move> moves) {
		return (nbFreeCells == 0);
	}
	
	private void processSolution(List<Move> moves) {
		//System.out.println(moves);
		isFinished = true;
	}
	
	private int[] findEmptyCell() {
		// implementation 1: find first available cell
//		for (int i = 0; i < n; i++) {
//			for (int j = 0; j < n; j++) {
//				if (board[i][j] == EMPTY_CELL) {
//					return new int[]{j, i}; // x, y
//				}
//			}
//		}
		
		// implementation 2: find most constrained free cell
		int nbPossibleValues = 9;
		int xmin = -1;
		int ymin = -1;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (board[i][j] == EMPTY_CELL) {
					int nb = getPossibleMoves(new int[]{j,i}).size();
					if (nb < nbPossibleValues) {
						xmin = j;
						ymin = i;
						nbPossibleValues = nb;
					}
					if (nb == 1) {
						break;
					}
				}
				if (nbPossibleValues == 1) break;
			}
		}		
		return new int[]{xmin, ymin};
	}
	
	// given a cell, what are the possible values
	private List<Move> getPossibleMoves(int[] cell) {
		// find all integers which are not already present
		List<Character> values = new ArrayList<Character>(Arrays.asList('1','2','3','4','5','6','7','8','9')); ;
		
		int x = cell[0];
		int y = cell[1];
		
		// horizontally
		for (int i = 0; i < n; i++) {
			values.remove(Character.valueOf(board[y][i]));
		}
		
		// vertically
		for (int j = 0; j < n; j++) {
			values.remove(Character.valueOf(board[j][x]));
		}
		
		// or in the 3x3 area containing the cell
		int areax = (int)Math.floor((double)x / 3);
		int areay = (int)Math.floor((double)y / 3);
		
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				values.remove(Character.valueOf(board[3 * areay + i][3 * areax + j]));
			}
		}
		
		List<Move> moves = new ArrayList<Move>();
		for (Character val: values) {
			Move move = new Move();
			move.x = x;
			move.y = y;
			move.val = val;
			moves.add(move);
		}
		
		return moves;
	}
	
	private List<Move> buildCandidates(List<Move> moves) {
		// find an empty cell
		int[] cell = findEmptyCell();
		
		// find possible values for cell
		List<Move> possibleMoves = getPossibleMoves(cell);
		
		return possibleMoves;
	}
	
	private void printBoard() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	private void backtrackDFS(List<Move> moves) {
//		System.out.println();
//		printBoard();
		if (isSolution(moves)) {
			processSolution(moves);
			return;
		}
		
		List<Move> candidates = buildCandidates(moves);

//		System.out.println(candidates);
		
		for (Move candidate: candidates) {
			makeMove(moves, candidate);
			backtrackDFS(moves);
			if (isFinished) break;
			unmakeMove(moves, candidate);
		}
	}
		
	char EMPTY_CELL = '.';
	
	class Move {
		int x;
		int y;
		char val;
		public String toString() {return "'" + val + "' at " + x + ", " + y; }
	}
	
	char[][] board;
	int nbFreeCells;
	boolean isFinished;
	
	public void solveSudoku(char[][] board) {
		this.board = board;
		this.n = board.length;
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (board[i][j] == EMPTY_CELL) {
					nbFreeCells++;
				}
			}
		}
		
		List<Move> moves = new ArrayList<Move>();
		backtrackDFS(moves);
	}
}
