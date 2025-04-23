public class Move {
    private String name, type;
    private int damage, accuracy, priority;
    private boolean recoil, physical, contact;

    public Move(){}
    public Move(String inName, String inType, int inDamage, int inAccuracy, int inPriority, boolean inRecoil, boolean inPhysical, boolean inContact){
        name = inName;
        type = inType;
        damage = inDamage;
        accuracy = inAccuracy;
        priority = inPriority;
        recoil = inRecoil;
        physical = inPhysical;
        contact = inContact;
    }

    public String getName(){ return name; }
    public String getType(){ return type; }
    public int getDamage(){ return damage; }
    public int getAccuracy(){ return accuracy; }
    public int getPriority(){ return priority; }
    public boolean getRecoil(){ return recoil; }
    public boolean getPhysical(){ return physical; }
    public boolean getContact(){ return contact; }

    public void setDamage(int inDamage){ damage = inDamage; }

}
