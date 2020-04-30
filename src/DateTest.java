import java.util.Calendar;
import java.util.Locale;
import java.util.Scanner;
import java.util.regex.*;

public class DateTest {
	
	public static final int YEAR_MIN = 1900, YEAR_MAX = 2100;	//年の範囲
	public static final int MONTH_MIN = 1, MONTH_MAX = 12;		//月の範囲
	public static final String[] DoW = {"日", "月", "火", "水", "木", "金", "土"};
	
	static Scanner scanner = new Scanner(System.in);	//キーボード入力受付用
	static String str;	//キーボード入力文字列格納
	static String regex = "^-?[0-9]*$";	//半角数字列の正規表現
	static Pattern p = Pattern.compile(regex);
	static Matcher m;
	static int year, month;	//年月
	
	public static void main(String[] args) {
		InputYear();	//年入力受付（下に定義した関数参照）
		InputMonth();	//月入力受付（　　　　〃　　　　　）
		
		month --;
		Calendar start = Calendar.getInstance(), last = Calendar.getInstance();
		start.clear();
		last.clear();
		
		start.set(year, month, 1);	//入力された年月の1日目

		if(month != 11){
			last.set(year, month + 1, 1);	//入力された年月の翌月の1日目
		}else{
			last.set(year + 1, 0, 1);	//startが12月だったら翌月初めは次の年の正月
		}

		for(Calendar current = (Calendar)start.clone(); !current.equals(last); current.add(Calendar.DATE, 1)){
			System.out.print(current.get(Calendar.DATE) + "(" + DoW[current.get(Calendar.DAY_OF_WEEK) - 1] + ")");
			
			//これでもできます
			//System.out.print(current.get(Calendar.DATE) + "(" + current.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale.JAPANESE) + ")");
			
			if(current.get(Calendar.DAY_OF_WEEK) == 7){
				System.out.println("");
			}
		}
	}
	
	//年入力用関数
	static void InputYear(){
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
	static void InputMonth(){
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
