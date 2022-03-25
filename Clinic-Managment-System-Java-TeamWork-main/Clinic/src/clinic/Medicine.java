/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinic;

/**
 *
 * @author aa
 */
public class Medicine {
    private int barcode;
    private String name;
    private float cost;
    private int number_pieces;

    public Medicine(int barcode, String name, float cost, int number_pieces) {
        this.barcode = barcode;
        this.name = name;
        this.cost = cost;
        this.number_pieces = number_pieces;
    }

    
    public int getBarcode() {
        return barcode;
    }

    public void setBarcode(int barcode) {
        this.barcode = barcode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public int getNumber_pieces() {
        return number_pieces;
    }

    public void setNumber_pieces(int number_pieces) {
        this.number_pieces = number_pieces;
    }
    
    
    
}
