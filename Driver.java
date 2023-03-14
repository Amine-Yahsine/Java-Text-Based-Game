//By Mohamed Amine Yahsine and Daniyal Iqbal

import java.util.Scanner;

public class Driver {

    private static Location currLocation;
    private static ContainerItem myInventory; 

    public static void main(String[] args){

        myInventory = new ContainerItem("Inventory", "Container", "Item used to store your loot.");   
        Scanner foo = new Scanner(System.in);
        String command;
        Driver.createWorld();
        
         loop:while(true){

            System.out.println("Enter command: ");
            command = foo.nextLine();
            String[] spaceCommand = command.split(" ");
            switch(spaceCommand[0]){

                case "quit":{
                    break loop;
                }
                case "look":{
                    System.out.println(currLocation.getName() + " - " + currLocation.getDescription());
                    for(int i = 0; i<currLocation.numItems(); i++){
                        System.out.println(currLocation.getItem(i).getName());
                    }
                    break;
                }
                
                case "examine":{
                    if(spaceCommand.length == 2){
                     if(currLocation.hasItem(spaceCommand[1])){
                         System.out.println(currLocation.getItem(spaceCommand[1]).toString());
                     }
                     else{
                         System.out.println("This item does not exist");
                     }
                     break;
                    }

                    else if(spaceCommand.length <=1){
                        System.out.println("Examine what?");
                        break;
                    }

                    else{
                        System.out.println("Try again");
                        break;
                    }
            }

                case "go":{
                    if(spaceCommand.length == 2){
                        if(currLocation.canMove(spaceCommand[1])){
                            currLocation = currLocation.getLocation(spaceCommand[1]);
                            System.out.println("You are now in the " + currLocation.getName());
                        }
                        else{
                            System.out.println("This location does not exist");
                        }
                        break;
                    }
                    else if(spaceCommand.length <= 1){
                        System.out.println("Go where?");
                        break;
                    }
                    else{
                        System.out.println("Try again");
                        break;
                    }
                }    

                case "inventory":{
                    System.out.println(myInventory);
                    break;
                }

                case "take":{
                    if(spaceCommand.length == 2){
                        if(currLocation.hasItem(spaceCommand[1])){
                            myInventory.addItem(currLocation.getItem(spaceCommand[1]));
                            currLocation.removeItem(spaceCommand[1]);
                            System.out.println("Item added to inventory.");
                        }
                        else{
                            System.out.println("Cannot find that item.");
                        }
                        break;
                    }
                    else if(spaceCommand.length <= 1){
                        System.out.println("Take what?");
                        break;
                    }
                    
                    else if(spaceCommand.length == 3 && spaceCommand[2].equalsIgnoreCase("from")){
                        System.out.println("From what?");
                        break;
                    }

                    else if(spaceCommand.length == 3){
                        System.out.println("Try again");
                        break;
                    }

                    if(spaceCommand.length == 4 && spaceCommand[2].equalsIgnoreCase("from")){
                        if(currLocation.hasItem(spaceCommand[3]) && currLocation.getItem(spaceCommand[3]) instanceof ContainerItem){
                         ContainerItem x = (ContainerItem) currLocation.getItem(spaceCommand[3]);

                         if(x.hasItem(spaceCommand[1])){
                             myInventory.addItem(x.removeItem(spaceCommand[1]));
                             System.out.println(spaceCommand[1]+ " taken from " + spaceCommand[3]);
                             break;
                         }

                         else{
                             System.out.println("This item does not exist in this container");
                             break;
                         }

                     }
                    
                     else if(currLocation.getItem(spaceCommand[3]) instanceof ContainerItem == false){
                        System.out.println("This item is not a container");
                        break;
                    }

                        else{
                            System.out.println("This container does not exist");
                            break;
                        }
                     }

                    else{
                        System.out.println("Try again");
                        break;
                    }
                }

                case "drop":{
                    if(spaceCommand.length == 2){
                        if(myInventory.hasItem(spaceCommand[1])){
                            Item temp = myInventory.removeItem(spaceCommand[1]);
                            currLocation.addItem(temp);
                            System.out.println(spaceCommand[1]+ " dropped from inventory.");
                        }
                        else{
                            System.out.println("Cannot find that item.");
                        }
                        break;
                    }
                    else if(spaceCommand.length <= 1){
                        System.out.println("Drop what?");
                        break;
                    }
                    else{
                        System.out.println("Try again,type help for available commands");
                        break;
                    }
                }

                case "help":{
                    System.out.println(helper());
                    break;
                }

                case "put":{
                  if(spaceCommand.length == 4 && spaceCommand[2].equalsIgnoreCase("in")){
                      if(myInventory.hasItem(spaceCommand[1]) && currLocation.getItem(spaceCommand[3]) instanceof ContainerItem){
                          ContainerItem x = (ContainerItem) currLocation.getItem(spaceCommand[3]);
                          x.addItem(myInventory.removeItem(spaceCommand[1]));
                          System.out.println(spaceCommand[1] + " added to " + spaceCommand[3]);
                          break;
                      }

                      else if(currLocation.getItem(spaceCommand[3]) instanceof ContainerItem == false){
                          System.out.println("This item is not a container");
                          break;
                      }

                      else if(myInventory.hasItem(spaceCommand[1]) == false){
                          System.out.println("You do not have this item in your inventory");
                          break;
                      }
                  }

                  else if(spaceCommand.length == 3){
                      System.out.println("Put item where?");
                      break;
                  }

                  else{
                      System.out.println("Try again, you can enter help to see the commands available");
                      break;
                  }
                }

                default:{
                    System.out.println("This command is not supported at this time, type help for available commands");
                    break;
                }
            }            
        }
    }

    public static void createWorld(){
       Location basement = new Location("Basement", "Used to store random stuff. Contains:");
       Location hallway = new Location("Hallway", "Connects the different rooms in the gouse. Contains:");
       Location bedroom = new Location("Master bedroom", "You can get some rest here. Contains:");
       Location garden = new Location("Garden", "Where plants grow. Contains:");

       hallway.connect("north", bedroom);
       bedroom.connect("south", hallway);
       
       bedroom.connect("west", garden);
       garden.connect("east", bedroom);

       garden.connect("south", basement);
       basement.connect("north", garden);

       basement.connect("east", hallway);
       hallway.connect("west", basement);

       basement.addItem(new Item("Table", "Furniture", "Used to put stuff on"));
       hallway.addItem(new Item("Lamp", "Furniture", "Can also be used as a weapon"));
       garden.addItem(new Item("Tree", "Plant", "Leaves have fallen from this tree due to fall season"));
       bedroom.addItem(new Item("Computer", "Electronics", "Basic computer, a little outdated"));

       basement.addItem(new Item("Pantry", "Furniture", "Used to store food"));
       hallway.addItem(new Item("Vase", "Furniture", "Artsy vase, has flowers in it"));
       garden.addItem(new Item("Grill", "Furniture", "Makes good barbecues"));
       bedroom.addItem(new Item("Shoes", "Clothing", "Running shoes"));

       ContainerItem chest = new ContainerItem("Chest", "Furniture", "Used to store all sorts of stuff");
       chest.addItem(new Item("Hoodie", "Clothing", "Keeps you warm in cold weather"));
       chest.addItem(new Item("Ring", "Accessory", "Family heirloom"));
       chest.addItem(new Item("Rock", "Miscelleanous", "Just a rock. Nobody knows what it's doing here"));

       ContainerItem desk = new ContainerItem("Desk", "Furniture", "Platform to work on");
       desk.addItem(new Item("Book", "Written", "Piece of knowledge"));
       desk.addItem(new Item("Printer", "Electronics", "To print different things"));
       desk.addItem(new Item("Remote", "Accessory", "Used to control electronics"));

       bedroom.addItem(desk);
       hallway.addItem(chest);

       currLocation = hallway;

    }

    public static String helper(){
        String spe = "look - you see what is around you";
        String spo = "examine - you look at the items around you";
        String spa = "go - you move to a different location";
        String spi = "inventory - you look at your bag and see what items you have";
        String spu = "take - you take an item from the location you are in";
        String spy = "drop - you drop an item from your inventory";
        String spc = "put - you put an item from your inventory in the container you choose";
        return spe + "\n" + spo + "\n" + spa + "\n" + spi + "\n" + spu + "\n" + spy + "\n" + spc;
    }
    
}
