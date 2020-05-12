/*********
パターンマッチングの手法をちょっと変えていますが
こういうのもある，というだけですので
Stringのmatches()でも全然大丈夫です
*********/

import java.util.Locale;
import java.util.Scanner;
import java.util.regex.*;
import java.time.LocalDate;
import java.time.DayOfWeek;
import java.time.format.TextStyle;



public class DateTest {
	
	public static final int YEAR_MIN = 1900, YEAR_MAX = 2100;	//年の範囲
	public static final int MONTH_MIN = 1, MONTH_MAX = 12;		//月の範囲
	
	static String str;
	static String regex = "^-?[0-9]*$";	//半角数字列の正規表現
	static Pattern p = Pattern.compile(regex);
	static Matcher m;
	static int year, month;
	
	public static void main(String[] args) {
		try(Scanner scanner = new Scanner(System.in)){	//try-with-resource文によりscannerを自動的に閉じてくれる
			InputYear(scanner);
			InputMonth(scanner);
		}
		
		LocalDate startDate = LocalDate.of(year, month, 1);	//対象月はじめ
		LocalDate nextDate = startDate.plusMonths(1);	//翌月はじめ
		
		//日にちを一日ずつずらしていって，次の月に行ったら（最終日を実行したら）終了
		for(LocalDate date = startDate; !date.equals(nextDate); date = date.plusDays(1)){
			//日付表示．getDisplayName()で日本語フォーマットを指定
			System.out.print(date.getDayOfMonth() + "(" + 
					date.getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.JAPANESE) + ")");
			
			if(date.getDayOfWeek() == DayOfWeek.SATURDAY){	//週末（土曜）なら改行
				System.out.println("");
			}
		}
	}
	
	//年入力用関数
	static void InputYear(Scanner scanner){
		while(true){
			System.out.print("カレンダーの年(半角数字)を入力して下さい > ");
			str = scanner.next();
			m = p.matcher(str);
			if(!m.find()){
				System.out.println("[Error]数字を入力して下さい > ");
				continue;
			}
			
			year = Integer.parseInt(str);
			if(!(YEAR_MIN <= year && year <= YEAR_MAX)){
				System.out.println("[Error]" + YEAR_MIN + "から" + YEAR_MAX + "の範囲で入力して下さい");
			}else{
				break;
			}
		}
		return;
	}
	
	//月入力用関数
	static void InputMonth(Scanner scanner){
		while(true){
			System.out.print("カレンダーの月(半角数字)を入力して下さい > ");
			str = scanner.next();
			m = p.matcher(str);
			if(!m.find()){
				System.out.println("[Error]数字を入力して下さい > ");
				continue;
			}
			
			month = Integer.parseInt(str);
			if(!(MONTH_MIN <= month && month <= MONTH_MAX)){
				System.out.println("[Error]" + MONTH_MIN + "から" + MONTH_MAX + "の範囲で入力して下さい");
			}else{
				break;
			}
		}
		return;
	}
}
