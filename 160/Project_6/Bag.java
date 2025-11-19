import java.util.Arrays;

class Bag {
    private int[] data;
    private int size;
    private static final int CAPACITY = 10;
    public Bag() {
        this.data = new int[this.CAPACITY];
        this.size = 0;
    }

    public int[] getData() {
        return this.data;
    }
    
    public int getSize() {
        return this.size;
    }

    public void add(int value) {
        if (this.size == this.data.length) {
            int[] newData = new int[this.data.length * 2];
            ArrayUtils.copyArray(this.data, newData, this.data.length);
            this.data = newData;
        }
        this.data[this.size] = value;
        this.size++;
    }

    public int pop() {
        if (this.size==0) {
            throw new java.util.NoSuchElementException("Bag is empty");
        }
        int temp = this.data[this.size-1];
        this.data[this.size-1] = 0;
        this.size--;
        if (this.size <= this.data.length / 4 && this.data.length > this.CAPACITY) {
            int newCapacity = this.data.length / 2;
            int[] newData = new int[newCapacity];
            ArrayUtils.copyArray(this.data, newData, this.size);
            this.data = newData;
        }
        return temp;
    }

    public String toString() {
        String s = Arrays.toString(ArrayUtils.slice(this.data, 0, this.size));
        StringBuilder sb = new StringBuilder(s);
        sb.setCharAt(0, '{');
        sb.setCharAt(sb.length() - 1, '}');
        return sb.toString();
    }

    public boolean equals(Object other) {
        if (!(other instanceof Bag)) {
            // Bag can only equal Bag
            return false;
        }
        Bag o = (Bag) other;
        if (this.size != o.size) {
            return false;
        }
        int[] thisData = this.data;
        int[] oData = o.getData();
        Arrays.sort(thisData, 0, this.size);
        Arrays.sort(oData, 0, o.getSize());
        return Arrays.equals(thisData, oData);
    }
}