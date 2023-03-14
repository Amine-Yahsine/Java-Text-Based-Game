import java.util.ArrayList;

public class ContainerItem extends Item{
    private ArrayList<Item> items;

    public ContainerItem(String xName,String xType,String xDescription){
         super(xName,xType,xDescription);
         items = new ArrayList<>();
    }

    public void addItem(Item newItem){
         items.add(newItem);
    }

    public boolean hasItem(String itemName){
        for(int i = 0; i<items.size(); i++){
            if(items.get(i).getName().equalsIgnoreCase(itemName)){
                return true;
            }
        }
        return false;
    }

    public Item removeItem(String itemName){
        Item foo = null;
        for(int i = 0; i<items.size(); i++){
            if(items.get(i).getName().equalsIgnoreCase(itemName)){
              foo = items.remove(i);
            }          
        }
        return foo;
    }

    @Override
    public String toString(){
        String temp = super.getName() + "[" + super.getType() + "] :"  + super.getDescription() + "\n";
        String foo = "";
        for(int i = 0; i<items.size(); i++){
            foo += "+ " + items.get(i).getName() + "\n";
        }
        return temp + foo;
    }

}
