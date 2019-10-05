public class Cell {
    private int x, y, gcost, hcost, fcost;
    private Cell parent;

    public Cell() {

    }

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setXY(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int hashCode() {
        //System.out.println("hashing");
        //return super.hashCode();
        return ((x*11) + (y*17)) * (x-y) + (x+y);
    }

    @Override
    public boolean equals(Object o) {
        //System.out.println("equating");
        //return super.equals(o);
        return this.hashCode() == o.hashCode();
    }

    public int getX() { return x; }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getGcost() {
        return gcost;
    }

    public void setGcost(int gcost) {
        this.gcost = gcost;
    }

    public int getHcost() {
        return hcost;
    }

    public void setHcost(int hcost) {
        this.hcost = hcost;
    }

    public int getFcost() {
        return fcost;
    }

    public void setFcost(int fcost) {
        this.fcost = fcost;
    }

    public Cell getParent() {
        return parent;
    }

    public void setParent(Cell parent) {
        this.parent = parent;
    }
}
