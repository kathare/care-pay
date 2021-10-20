Please provide review-comments for the code below:

```
@Component
public class MyAction {
    public boolean debug = true;
    @Autowired
    public DataSource ds;

    //R: I suggest that SQLException to be caught in a try catch block 
    public Collection getCustomers(String firstName, String lastName, String address, String zipCode, String city) throws SQLException {
        Connection conn = ds.getConnection();
        String query = new String("SELECT * FROM customers where 1=1");
        if (firstName != null) {
            query = query + " and first_name = '" + firstName + "'";
        }
        if (firstName != null) {//R:lastName
            query = query + " and last_name = '" + firstName + "'";
        }
        if (firstName != null) {//R:address
            query = query + " and address = '" + address + "'";
        }
        if (firstName != null) {//R:zipCode
            query = query + " and zip_code = '" + zipCode + "'";
        }
        if (firstName != null) {//R: city
            query = query + " and city = '" + city + "'";
        }
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        List customers = new ArrayList();
        while (rs.next()) {
            Object[] objects = new Object[] { rs.getString(1), rs.getString(2) };
            if (debug) print(objects, 4);
            customers.add(new Customer(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
        }
        return customers; //R: return type is expected to be a collection. Provided ia a list
    }
    //R: Check print function should not return void otherwise nothing will be printed
    //I suggest the function should return a string
    
    public void print(Object[] s, int indent) {
        for (int i=0; i<=indent; i++) System.out.print(' ');
        printUpper(s);
    }
       //R: No need of making this function static, we are using it directly within the class.
      // the function should return a String
       //NIT:We need a StringBuilder defined to build up the string 
      
    public static void printUpper(Object [] words){
        int i = 0;
        try {
            while (true){//R: this will cause an infinite loop
                if (words[i].getClass() == String.class) {
                    String so = (String)words[i];; // Unnecessary semicolon 
                    so = so.toUpperCase();
                    System.out.println(so); //not necessary
                }
                i++;
            }
        } catch (IndexOutOfBoundsException e) {
            //iteration complete
        }
    }
}
```
