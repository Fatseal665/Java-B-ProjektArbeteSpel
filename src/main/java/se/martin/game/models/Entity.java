package se.martin.game.models;

public abstract class Entity {
    private int health;
    private int damage;
    private String role;


    public Entity(int health, int damage, String role) {
        this.health = health;
        this.damage = damage;
        this.role = role;
    }


    public String getRole() {
        return role;
    }


    public int getHealth() {
        return health;
    }


    public int getDamage() {
        return damage;
    }


    public void setDamage(int damage) {
        this.damage = damage;
    }


    public void takeHit(int damage) {
        health = health - damage;
    }


    public void punch(Entity toPunch) {
        toPunch.takeHit(this.damage);
    }


    public boolean isConscious(){
        if (health > 0) {
            return true;
        } else {
            return false;
        }
    }
}