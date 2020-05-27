package sdu.mmmi.softwareengineering.osgicommon.data;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author theun
 */
public class Index {

    public int x;
    public int y;

    public Index(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Index)) {
            return false;
        }
        Index index = (Index) o;
        return x == index.x && y == index.y;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }
}
