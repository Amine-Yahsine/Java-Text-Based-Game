import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

public class Location {

    private String name;
    private String description;
    private ArrayList<Item> myList;
    private TreeMap<String, Location> connections;

    public Location(String name, String description){
        this.name = name;
        this.description = description;
        myList = new ArrayList<Item>();
        connections = new TreeMap<String, Location>(String.CASE_INSENSITIVE_ORDER);
    }

    public String getName(){
        return name;
    }

    public String getDescription(){
        return description;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public void addItem(Item newObject){
         myList.add(newObject);
    }

    public boolean hasItem(String itemName)
    {
        for(int i = 0; i<myList.size(); i++)
        {
            String x= myList.get(i).getName();
            if(itemName.equalsIgnoreCase(x))
            {
                return true;
            }
           
        }
        return false;
    }

    public Item getItem(String itemName){
        for(int i = 0; i<myList.size(); i++){
            if(itemName.equalsIgnoreCase(myList.get(i).getName())){
                return myList.get(i);
            }
        }
        return null;
    }

    public Item getItem(int newIndex){
        if(newIndex >= 0 && newIndex < myList.size()){
        Item x = myList.get(newIndex);
        return x;
    }
        else{
        return null;
        }
    }

    public int numItems(){
        return myList.size();
    }

    public Item removeItem(String itemName){
      if(hasItem(itemName)){
        for(int i = 0; i<myList.size(); i++){
            if(itemName.equalsIgnoreCase(myList.get(i).getName())){
                myList.remove(i);
            }
        }
    }
      else{
          return null;
      }
        return null;
    }

    public void connect(String direction, Location place){
        this.connections.put(direction, place);
    }

    public boolean canMove(String direction){
            if(connections.containsKey(direction)){
            return true;
            }        
        return false;
    }

    public Location getLocation(String direction){
            if(connections.containsKey(direction)){
                return connections.get(direction);
            }        
        return null;
    }

}
