/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author shan
 */
public class Role {
    
    private int id;
    private String name;
    private String metacode;

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the metacode
     */
    public String getMetacode() {
        return metacode;
    }

    /**
     * @param metacode the metacode to set
     */
    public void setMetacode(String metacode) {
        this.metacode = metacode;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    
    
    
    
}
