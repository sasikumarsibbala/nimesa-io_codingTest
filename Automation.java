package nimesa;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.print.DocFlavor.STRING;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;

public class Automation {

	public static void main(String[] args) throws IOException, JSONException {
		 
			  
	try {	   
	 String url = "https://samples.openweathermap.org/data/2.5/forecast/hourly?q=London,us&appid=b6907d289e10d714a6e88b30761fae22";
     URL obj = new URL(url);
     HttpURLConnection con = (HttpURLConnection) obj.openConnection();
     
     con.setRequestMethod("GET");
    
     con.setRequestProperty("User-Agent", "Mozilla/5.0");
     BufferedReader in = new BufferedReader(
             new InputStreamReader(con.getInputStream()));
     String inputLine;
     StringBuffer response = new StringBuffer();
     while ((inputLine = in.readLine()) != null) {
     	response.append(inputLine);
     }
     in.close();
	  
	  
	}
	 catch (Exception e) {
				  System.out.println(e);
	}//calling the test cases from
		 NoOfDaysIs4();
		 ForecastInTheHourlyInterval();
		 TempCheck();
		 WeatherIdIs500DescriptionShouldBeLightRain();
		 WeatherIdIs800TheDescriptionShouldClearSky();

}  //first testcase=is the response contains 4 days of data
	public static void  NoOfDaysIs4() throws IOException, JSONException {
		String url = "https://samples.openweathermap.org/data/2.5/forecast/hourly?q=London,us&appid=b6907d289e10d714a6e88b30761fae22";
	     URL obj = new URL(url);
	     HttpURLConnection con = (HttpURLConnection) obj.openConnection();
	     
	     con.setRequestMethod("GET");
	     
	     con.setRequestProperty("User-Agent", "Mozilla/5.0");
	     BufferedReader in = new BufferedReader(
	             new InputStreamReader(con.getInputStream()));
	     String inputLine;
	     StringBuffer response = new StringBuffer();
	     while ((inputLine = in.readLine()) != null) {
	     	response.append(inputLine);
	     }
	     in.close();
	     
	     
	     JSONObject myResponse = new JSONObject(response.toString());
	
		Set<Integer> set = new HashSet<Integer>();
	
		JSONArray list=(JSONArray)myResponse.get("list");
		
		
		for(int i=0;i<list.length();i++) {
			JSONObject dt=(JSONObject)list.get(i);
			  String day=(String)dt.get("dt_txt");
			int n = Integer. valueOf(day.substring(8, 11));
			
			if(set.contains(n)==false) {
				set.add(n);
			}
			if(set.size()==4)
				System.out.println(true);
			else
				System.out.println(false);
			
			}
	}
	
	
	
	//second test case=Is all the forecast in the hourly interval ( no hour should be missed )
	public static void ForecastInTheHourlyInterval () throws JSONException, IOException {
		String url = "https://samples.openweathermap.org/data/2.5/forecast/hourly?q=London,us&appid=b6907d289e10d714a6e88b30761fae22";
	     URL obj = new URL(url);
	     HttpURLConnection con = (HttpURLConnection) obj.openConnection();
	     
	     con.setRequestMethod("GET");
	    
	     con.setRequestProperty("User-Agent", "Mozilla/5.0");
	     BufferedReader in = new BufferedReader(
	             new InputStreamReader(con.getInputStream()));
	     String inputLine;
	     StringBuffer response = new StringBuffer();
	     while ((inputLine = in.readLine()) != null) {
	     	response.append(inputLine);
	     }
	     in.close();
	     
	     System.out.println(response.toString());
	    
	     JSONObject myResponse = new JSONObject(response.toString());
		int a[]={1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24};
		ArrayList<Integer> al=new ArrayList<Integer>();
		Set<Integer> set=new HashSet<Integer>();
		JSONArray list=(JSONArray)myResponse.get("list");
		  for(int i=0;i<list.length();i++) {
			  JSONObject dt=(JSONObject)list.get(i);
			  String date=(String)dt.get("dt_txt");
				
			  int m=Integer.valueOf(date.substring(10, 13));
			  for(int j=0;i<a.length;j++) {
				  if(a[j]==m)
				  set.add(m);
			  else
				  al.add(m);
			  }
			if(al.isEmpty()==true)
				System.out.println(true);
			else
				System.out.println(false);
			}
			
			
		  
		  
	}
	
	
	//third test case=For all 4 days, the temp should not be less than temp_min and not more than temp_max
	public static void TempCheck() throws IOException, JSONException {
		String url = "https://samples.openweathermap.org/data/2.5/forecast/hourly?q=London,us&appid=b6907d289e10d714a6e88b30761fae22";
	     URL obj = new URL(url);
	     HttpURLConnection con = (HttpURLConnection) obj.openConnection();
	    
	     con.setRequestMethod("GET");
	     
	     con.setRequestProperty("User-Agent", "Mozilla/5.0");
	     BufferedReader in = new BufferedReader(
	             new InputStreamReader(con.getInputStream()));
	     String inputLine;
	     StringBuffer response = new StringBuffer();
	     while ((inputLine = in.readLine()) != null) {
	     	response.append(inputLine);
	     }
	     in.close();
	     
	     
	    
	     JSONObject myResponse = new JSONObject(response.toString());
		JSONArray list=(JSONArray)myResponse.get("list");
		 
		  for(int x=0;x<list.length();) {
			 JSONObject main=(JSONObject)list.get(x);
			 JSONArray temp=(JSONArray)main.get("temp");
			 JSONArray temp_min=(JSONArray)main.get("temp_min");
			 JSONArray temp_max=(JSONArray)main.get("temp_max");
			 
			 
			  int normal_temp=Integer. valueOf(temp.getInt(x));
			  int max_temp=Integer. valueOf(temp_max.getInt(x));;
			  int min_temp=Integer. valueOf(temp_min.getInt(x));
			  if(normal_temp>=min_temp&&normal_temp<=max_temp)
			  {
				  x++;
			  }
			  else
			  System.out.println(false);
			  break;
		  }
		  System.out.println(true);
		
	}
	
	
	//fourth test case=If the weather id is 500, the description should be light rain
	public static void WeatherIdIs500DescriptionShouldBeLightRain() throws IOException, JSONException {
		String url = "https://samples.openweathermap.org/data/2.5/forecast/hourly?q=London,us&appid=b6907d289e10d714a6e88b30761fae22";
	     URL obj = new URL(url);
	     HttpURLConnection con = (HttpURLConnection) obj.openConnection();
	     
	     con.setRequestMethod("GET");
	     
	     con.setRequestProperty("User-Agent", "Mozilla/5.0");
	     BufferedReader in = new BufferedReader(
	             new InputStreamReader(con.getInputStream()));
	     String inputLine;
	     StringBuffer response = new StringBuffer();
	     while ((inputLine = in.readLine()) != null) {
	     	response.append(inputLine);
	     }
	     in.close();
	     
	     
	     JSONObject myResponse = new JSONObject(response.toString());
		String OrgDescription="light rain";
		String ValueFromProgram="";
		int value=500;
		JSONArray list=(JSONArray)myResponse.get("list");
		
		
		for(int i=0;i<list.length();i++) {
			JSONArray weather=(JSONArray)list.get(i);
			JSONObject id=(JSONObject)weather.get(i);
			String str=(String)id.get("id");
			int n=Integer.valueOf(str);
					
			JSONObject desc=(JSONObject)weather.get(i);
			String dep=(String)desc.get("description");
			         
			if(n==value) {
				 ValueFromProgram=dep;
			}
			if( OrgDescription.equals(ValueFromProgram)) {
				System.out.println(true);
			}
			else
				System.out.println(false);
			}
		
			}
	
	
	//fifth test case= If the weather id is 800, the description should be a clear sky

	
	public static void WeatherIdIs800TheDescriptionShouldClearSky() throws IOException, JSONException {
		String url = "https://samples.openweathermap.org/data/2.5/forecast/hourly?q=London,us&appid=b6907d289e10d714a6e88b30761fae22";
	     URL obj = new URL(url);
	     HttpURLConnection con = (HttpURLConnection) obj.openConnection();
	     // optional default is GET
	     con.setRequestMethod("GET");
	     //add request header
	     con.setRequestProperty("User-Agent", "Mozilla/5.0");
	     int responseCode = con.getResponseCode();
	     System.out.println("\nSending 'GET' request to URL : " + url);
	     System.out.println("Response Code : " + responseCode);
	     BufferedReader in = new BufferedReader(
	             new InputStreamReader(con.getInputStream()));
	     String inputLine;
	     StringBuffer response = new StringBuffer();
	     while ((inputLine = in.readLine()) != null) {
	     	response.append(inputLine);
	     }
	     in.close();
	     //print in String
	     System.out.println(response.toString());
	     //Read JSON response and print
	     JSONObject myResponse = new JSONObject(response.toString());
		
		String OrgDescription="clear sky";
		String ValueFromProgram="";
		int value=800;
    JSONArray list=(JSONArray)myResponse.get("list");
		
		
		for(int i=0;i<list.length();i++) {
			JSONArray weather=(JSONArray)list.get(i);
			JSONObject id=(JSONObject)weather.get(i);
			String str=(String)id.get("id");
			int n=Integer.valueOf(str);
					
			JSONObject desc=(JSONObject)weather.get(i);
			String dep=(String)desc.get("description");
			         
			if(n==value) {
				 ValueFromProgram=dep;
			}
			if( OrgDescription.equals(ValueFromProgram)) {
				System.out.println(true);
			}
			else
				System.out.println(false);
		
				
		
	}
	}
}
