import java.util.Locale;
import java.util.Scanner;
import java.util.regex.*;
import java.time.LocalDate;
import java.time.format.TextStyle;

public class DateTest {
	
	public static final int YEAR_MIN = 1900, YEAR_MAX = 2100;	//年の範囲
	public static final int MONTH_MIN = 1, MONTH_MAX = 12;		//月の範囲
	
	static Scanner scanner = new Scanner(System.in);
	static String str;
	static String regex = "^-?[0-9]*$";	//半角数字列の正規表現
    static Pattern p = Pattern.compile(regex);
    static Matcher m;
    static int year, month;
    
	public static void main(String[] args) {
        InputYear();
        InputMonth();
        
        LocalDate startDate = LocalDate.of(year, month, 1);
        LocalDate lastDate = startDate.plusMonths(1).minusDays(1);
        
        for(LocalDate date = startDate; date.getDayOfMonth() <= lastDate.getDayOfMonth(); date = date.plusDays(1)){
        	System.out.println(date.getDayOfMonth() + "" + 
        			date.getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.JAPANESE));
        }
        
//        for(int date = 1; date <= lastDate; date++){
//        	System.out.print(date + " ");
//        }
        
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
				System.out.println(YEAR_MIN + "から" + YEAR_MAX + "の範囲で入力して下さい");
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
				System.out.println(MONTH_MIN + "から" + MONTH_MAX + "の範囲で入力して下さい");
			}else{
				break;
			}
		}
		return;
	}
}
