import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import jsonexample.Staff;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.*;
import java.math.BigDecimal;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class App 
{
    public static void main( String[] args )
    {
        Gson gson = new Gson();
        Staff staff = createStaffObj();
        //sınıf nesnesini gson yardımıyla json a dönderme
        String json = gson.toJson(staff);

        // bir json array oluşturdum
        JSONArray employeeList = new JSONArray();

        //bir  jsonobject nesnesi oluşturdum ve etiket isimlerini ve değerlerini girdim.
        JSONObject employee = new JSONObject();
        employee.put("firstName", "fatih");
        employee.put("lastName", "Aptil");
        employee.put("website", "deneme123.com");

        //bu oluşturduğum jsonobjesi ile employee nesnesini yeni oluşturduğum  Jsonobject nesnesinin employee etiketinin altına ekledim.
        JSONObject employeeObject = new JSONObject();
        employeeObject.put("employee", employee);



        JSONObject employeeObject2 = new JSONObject();
        employeeObject2.put("staff", json);// json string ini staff ın  altına ekledim.


        write(employeeObject,employeeList);
        write(employeeObject2,employeeList);

        read();

    }




    private static Staff createStaffObj() {

        Staff staff = new Staff();
        staff.setName("muharrem");
        staff.setAge(23);
        staff.setPosition(new String[]{"ahmet","fatih","eyup","emir","süleyman"});
        staff.setSkills(Arrays.asList("yüzme", "bilardo", "tersten konusabilme"));

        Map<String , BigDecimal> map = new HashMap()
        {
            {
                put("bir",new BigDecimal(1000));
                put("iki",new BigDecimal(2000));
                put("üc" , new BigDecimal(3000));            }
        };
        staff.setSalary(map);



        return staff;
    }

    private  static void write(JSONObject jsonObject, JSONArray employeeList)
    {
        // json array ine ekleme
        employeeList.add(jsonObject);

        //json dosyasına yazma
        try (FileWriter file = new FileWriter("staff.json")) {

            file.write(employeeList.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static  void read()
    {

        //elemente dönüştürme (json dosyası okuma)
        Gson gson2= new GsonBuilder().setPrettyPrinting().create();

        try (Reader reader = new FileReader("staff.json")) {

            // json ı json element edönüştürüyor.
            JsonElement json2 = gson2.fromJson(reader, JsonElement.class);

            String jsonInString = gson2.toJson(json2);

            System.out.println(jsonInString);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
