public class Zombie extends Character{
//  private inventory inventory = new inventory();

  public Zombie(){
    name = "Zombie";
//    setHealth();
    inventory.add("Hat");
  }

  public Zombie(String name){

  }

  
  public static void main(String[] args) {
    Zombie z = new Zombie("Zombie");
    z.inventory.add("Hat1");
    System.out.println(z.name);
    System.out.println(z.health);
  }

}
