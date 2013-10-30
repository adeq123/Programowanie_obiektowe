package board;

import java.util.Random;
import java.util.regex.Pattern;

import dictionary.CwEntry;
import dictionary.CwEntry.Direction;
import dictionary.Entry;

public class ConcretStrategy extends Strategy {

	@Override
	public CwEntry findEntry(Crossword cw) {
		// TODO Auto-generated method stub
		Random rand = new Random();

		Entry entry;
		CwEntry cwentry = null;
		if (cw.entries.isEmpty() == true){//    cw.getROEntryIter().hasNext() == false) {
			System.out.println("LLL");
			do {
				entry = cw.getCwDB().getRandom();
			} while (entry.getWord().contains("y") == true);

			cwentry = new CwEntry(entry.getWord(), entry.getClue(), 0, 0,
					Direction.HORIZ);
			System.out.println(entry.getWord());
		} else {
			//int lengthMainWord = cw.entries.get(0).getWord().length();
			int amountOfWords = cw.entries.size();
			System.out.println(amountOfWords);
			String currentPattern = cw.b.createPattern(0, amountOfWords-1, rand.nextInt(5) + 3, amountOfWords-1);
		//	System.out.println("222" + currentPattern.toString() + "222");
			System.out.println("222");
			//System.out.println(currentPattern);
			System.out.println("222");
			if (currentPattern != null){
			entry = cw.getCwDB().getRandom(currentPattern);
			cwentry = new CwEntry(entry.getWord(), entry.getClue(), 0, amountOfWords,
					Direction.VERT);
			
			System.out.println(entry.getWord() + "SSSSSSSSS");
			}
		}

		return cwentry;

	}

	@Override
	public void updateBoard(Board b, CwEntry e) {
		// TODO Auto-generated method stub

		char[] contentOfString = new char[e.getWord().length()];
		contentOfString = e.getWord().toCharArray();
		
		
		
		

		if (e.getDir() == Direction.VERT) {
			for (int i = 0; i < e.getWord().length(); i++) {
System.out.println(contentOfString[i] + "lll");
				
				BoardCell boardcell = new BoardCell();
				boardcell.setContent(new String(contentOfString, i, 1));
				
				
				
				boardcell.setAbility(boardcell.VERT, boardcell.START, false);
				boardcell.setAbility(boardcell.VERT, boardcell.END, false);
				boardcell.setAbility(boardcell.VERT, boardcell.INNER, false);

				if (e.getY() + 1 < b.height)
					boardcell
							.setAbility(boardcell.HORIZ, boardcell.START, true);
				else
					boardcell.setAbility(boardcell.HORIZ, boardcell.START,
							false);
				if (e.getY() - 1 > 0)
					boardcell.setAbility(boardcell.HORIZ, boardcell.END, true);
				else
					boardcell.setAbility(boardcell.HORIZ, boardcell.END, false);
				if ((e.getY() + 1 < b.height) && (e.getY() - 1 > 0))
					boardcell
							.setAbility(boardcell.HORIZ, boardcell.INNER, true);
				else
					boardcell.setAbility(boardcell.HORIZ, boardcell.INNER,
							false);
				
				b.setCell(e.getX() , e.getY()+i, boardcell);
			}
		} else {
			for (int i = 0; i < e.getWord().length(); i++) {
				
				System.out.println(contentOfString[i] + "kkk");
				
				BoardCell boardcell = new BoardCell();
				boardcell.setContent(new String(contentOfString, i, 1));
				
				
				
				
				
				boardcell.setAbility(boardcell.HORIZ, boardcell.START, false);
				boardcell.setAbility(boardcell.HORIZ, boardcell.END, false);
				boardcell.setAbility(boardcell.HORIZ, boardcell.INNER, false);

				if (e.getX() + 1 < b.width)
					boardcell.setAbility(boardcell.VERT, boardcell.START, true);
				else
					boardcell
							.setAbility(boardcell.VERT, boardcell.START, false);
				if (e.getX() - 1 > 0)
					boardcell.setAbility(boardcell.VERT, boardcell.END, true);
				else
					boardcell.setAbility(boardcell.VERT, boardcell.END, false);
				if ((e.getX() + 1 < b.width) && (e.getX() - 1 > 0))
					boardcell.setAbility(boardcell.VERT, boardcell.INNER, true);
				else
					boardcell
							.setAbility(boardcell.VERT, boardcell.INNER, false);
				b.setCell(e.getX() + i, e.getY(), boardcell);
			}

		}
	}
}
