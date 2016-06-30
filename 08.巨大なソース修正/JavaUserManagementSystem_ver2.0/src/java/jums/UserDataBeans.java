package jums;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * ページで入出力されるユーザー情報を持ちまわるJavaBeans。DTOと違い画面表示系への結びつきが強い
 * DTOへの変換メソッド、入力チェックリストを出力するメソッドも準備されている←ちょっと仕事しすぎかも
 * @author hayashi-s
 */
public class UserDataBeans implements Serializable{
    private int userID;
    private String name;
    private int year;
    private int month;
    private int day;
    private String tell;
    private int type ;
    private String comment;
    
    public UserDataBeans(){
        this.userID = 0;
        this.name = "";
        this.year = 0;
        this.month = 0;
        this.day = 0;
        this.tell = "";
        this.type = 0;
        this.comment = "";
    }
    
    public int getUserID() {
        return userID;
    }
    public void setUserID(String userID) {
        if(userID.equals("")){
            this.userID = 0;
        }else{
            this.userID = Integer.parseInt(userID);
        }
    }
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        //空文字(未入力)の場合空文字をセット
        if(name.trim().length()==0){
            this.name = "";
        }else{
            this.name = name;
        }
    }

    public int getYear() {
        return year;
    }
    public void setYear(String year) {
        //初期選択状態の場合0をセット
        if(year.equals("")){
            this.year = 0;
        }else{
            this.year = Integer.parseInt(year);
        }
    }

    public int getMonth() {
        return month;
    }
    public void setMonth(String month) {
        if(month.equals("")){
            this.month = 0;
        }else{
            this.month = Integer.parseInt(month);
        }
    }

    public int getDay() {
        return day;
    }
    public void setDay(String day) {
        if(day.equals("")){
            this.day = 0;
        }else{
            this.day = Integer.parseInt(day);
        }
    }

    public String getTell() {
        return tell;
    }
    public void setTell(String tell) {
        if(tell.trim().length()==0){
            this.tell = "";
        }else{
            this.tell = tell;
        }
    }

    public int getType() {
        return type;
    }
    public void setType(String type) {
        if(type == null){
            this.type = 0;
        }else{
            this.type = Integer.parseInt(type);
        }

    }

    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        if(comment.trim().length()==0){
            this.comment = "";
        }else{
            this.comment = comment;
        }
    }
    
    public ArrayList<String> chkproperties(){
        ArrayList<String> chkList = new ArrayList<String>();
        if(this.name.equals("")){
            chkList.add("name");
        }
        if(this.year == 0){
            chkList.add("year");
        }
        if(this.month == 0){
            chkList.add("month");
        }
        if(this.day == 0){
            chkList.add("day");
        }
        if(this.tell.equals("")){
            chkList.add("tell");
        }
        if(this.type == 0){
            chkList.add("type");
        }
        if(this.comment.equals("")){
            chkList.add("comment");
        }
        
        return chkList;
    }

    public void UD2DTOMapping(UserDataDTO udd){
        udd.setUserID(this.userID);
        udd.setName(this.name);
        if(this.year != 0 || this.month != 0 || this.day != 0){
            Calendar birthday = Calendar.getInstance();
            if(this.month == 0 || this.day == 0){
                birthday.set(this.year,0,1);
            }else{
                birthday.set(this.year,(this.month)-1,this.day);
            }
            udd.setBirthday(birthday.getTime());
        }else{
            udd.setBirthday(null);
        }
        udd.setTell(this.tell);
        udd.setType(this.type);
        udd.setComment(this.comment);
    }
    
    //DTO→Beans
    public void UDBMapping(UserDataDTO udd) {
        //Date型をCalendar型に変換
        Date date = new Date();
        date = udd.getBirthday();
        Calendar birth = Calendar.getInstance();
        birth.setTime(date);
        //Calendar型から値を取得してString型に変換
        String Year = String.valueOf(birth.get(Calendar.YEAR));
        String Month = String.valueOf(birth.get(Calendar.MONTH)+1);
        String Day = String.valueOf(birth.get(Calendar.DATE));
        //int型をString型に変換
        String UserID = String.valueOf(udd.getUserID());
        String Type = String.valueOf(udd.getType());
                
        //DTOの値をUserDataBeansに格納
        UserDataBeans udb = new UserDataBeans();
        this.setUserID(UserID);
        this.setName(udd.getName());
        this.setYear(Year);
        this.setMonth(Month);
        this.setDay(Day);
        this.setType(Type);
        this.setTell(udd.getTell());
        this.setComment(udd.getComment());
    }
}
