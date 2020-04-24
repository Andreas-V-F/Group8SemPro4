/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.mmmi.softwareengineering.osgicommon.bullet;


import sdu.mmmi.softwareengineering.osgicommon.data.Entity;

/**
 *
 * @author andre
 */
public class Bullet extends Entity{
    private String shooterID;

    public Bullet(String shooterID) {
        this.shooterID = shooterID;
    }

    public String getShooterID() {
        return shooterID;
    }

    public void setShooterID(String shooterID) {
        this.shooterID = shooterID;
    }
    
    
}
