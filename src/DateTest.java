import java.util.Calendar;
import java.util.Scanner;
import java.util.regex.*;

public class DateTest {
	
	public static final int YEAR_MIN = 1900, YEAR_MAX = 2100;	//年の範囲
	public static final int MONTH_MIN = 1, MONTH_MAX = 12;		//月の範囲
	public static final String[] DoW = {"日", "月", "火", "水", "木", "金", "土"};
	
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
        Calendar start = Calendar.getInstance(), last = Calendar.getInstance();
        start.clear();
        last.clear();
        
        start.set(year, month, 1);

        if(month != 11){
        	last.set(year, month + 1, 1);
        }else{
        	last.set(year + 1, 0, 1);
        }
        
        System.out.println("start:"+start.get(Calendar.MONTH)+"/"+start.get(Calendar.DATE));
        System.out.println("last:"+last.get(Calendar.MONTH)+"/"+last.get(Calendar.DATE));
        
        for(Calendar current = (Calendar)start.clone(); !current.equals(last); current.add(Calendar.DATE, 1)){
        	System.out.print(current.get(Calendar.DATE) + "(" + current.get(Calendar.DAY_OF_WEEK) + ")");
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
