import java.util.Calendar;
import java.util.Scanner;
import java.util.regex.*;

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
        
        month --;
        Calendar cal = Calendar.getInstance();
        cal.set(year, month, 1);
        
        int startDay = cal.get(Calendar.DAY_OF_WEEK);
        
        cal.add(Calendar.MONTH, 1);
        cal.add(Calendar.DATE, -1);
        int lastDay = cal.get(Calendar.DAY_OF_WEEK);
        int lastDate = cal.get(Calendar.DATE);
        
        System.out.println(Calendar.MONDAY);
        
        for(int date = 1; date <= lastDate; date++){
        	System.out.print(date + " ");
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
