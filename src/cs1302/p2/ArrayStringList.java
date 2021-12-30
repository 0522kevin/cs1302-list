package cs1302.p2;

import cs1302.adt.StringList;
import cs1302.adt.FancyStringList;

/**
 * The class for StringList with Array types.
 */
public class ArrayStringList extends BaseStringList {

    private String[] items;

    /**
     * The default constructor that creates an object of {@code ArrayStringList}.
     */
    public ArrayStringList() {

        super();
        items = new String[10];

    } // Default constructor for ArrayStringList

    /**
     * This method overrides the method in {@link StringList}.
     *
     * {@inheritDoc}
     */
    @Override
    public boolean add(int index, String item) {

        if (item == null) {
            throw new NullPointerException();
        } else if (item.equals("")) {
            throw new IllegalArgumentException();
        } else if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException();
        } // if

        String temp;
        boolean didItHappen = false;

        try {

            if ((size() + 1) == items.length) {
                biggerArray();
            } // if that determines if items[] should get bigger

            String[] copyItems = new String[items.length];

            for (int i = 0; i < items.length; i++) {
                if (i < index) {
                    copyItems[i] = items[i];
                } else if (i == index) {
                    copyItems[i] = item;
                } else if (i > index) {
                    copyItems[i] = items[i - 1];
                } // if
            } // for loop that fills copyItems[] the correct item

            size++;
            items = copyItems;
            didItHappen = true;

        } catch (NullPointerException npe) {
            System.err.println("The item to be added is null.");
        } catch (IllegalArgumentException iae) {
            System.err.println("The item to be added is empty.");
        } catch (IndexOutOfBoundsException ioobe) {
            System.err.println("The index the item is to be added is out of range.");
        } // try

        return didItHappen;

    } // add

    /**
     * Method that expands the array items[].
     */
    private void biggerArray() {

        String[] copyItems = new String[items.length + 5];

        for (int i = 0; i < items.length; i++) {
            copyItems[i] = items[i];
        } // for that copies items[] over to copyItems[]

        items = copyItems;

    } // biggerArray

    /**
     * This method overrides the method in {@link StringList}.
     *
     * {@inheritDoc}
     */
    @Override
    public void clear() {

        for (int i = 0; i < items.length; i++) {
            items[i] = null;
        } // for loop that sets every item in items[] to be null

        size = 0;

    } // clear

    /**
     * This method overrides the method in {@link StringList}.
     *
     * {@inheritDoc}
     */
    @Override
    public String get(int index) {

        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException();
        } // if

        String tempString = null;

        try {
            tempString = items[index];
        } catch (IndexOutOfBoundsException ioobe) {
            System.err.println("Index is out of range.");
        } // try

        return tempString;

    } // get

    /**
     * This method overrides the method in {@link StringList}.
     *
     * {@inheritDoc}
     */
    @Override
    public String remove(int index) {

        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException();
        } // if

        String removed = null;
        String[] copyItems = new String[items.length];

        try {

            removed = get(index);

            for (int i = 0; i < items.length; i++) {
                if (i < index) {
                    copyItems[i] = items[i];
                } else if (i > index) {
                    copyItems[i - 1] = items[i];
                } // if
            } // for loop that shifts over the items after index

            size--;

        } catch (IndexOutOfBoundsException ioobe) {
            System.err.println("Index is out of range.");
        } // try

        return removed;

    } // remove

    /**
     * This method overrides the method in {@link StringList}.
     *
     * {@inheritDoc}
     */
    @Override
    public StringList slice(int start, int stop) {

        if (start < 0 || stop > size() || start > stop) {
            throw new IndexOutOfBoundsException();
        } // if

        int indexCount = 0;
        StringList sliced = new ArrayStringList();

        try {

            for (int i = start; i < stop; i++) {
                sliced.add(indexCount, get(start));
                indexCount++;
                start++;
            } // for loop that adds up the item from start to stop

        } catch (IndexOutOfBoundsException ioobe) {
            System.err.println("Illegal endpoint index value.");
        } // try

        return sliced;

    } // slice

    /**
     * The constructor that creates a new {@code ArrayStringList} object
     * that adds a preexisting {@code StringList} object to the end.
     *
     * @param other  StringList object that gets attached to the original.
     */
    public ArrayStringList(StringList other) {

        super();

        if (other == null) {
            throw new NullPointerException();
        } // if

        try {

            items = new String[other.size()];

            for (int i = 0; i < other.size(); i++) {

                add(i, other.get(i));

            } // for

        } catch (NullPointerException npe) {
            System.err.println("Other is null.");
        } // try

    } // ArrayStringList with StringList

    /**
     * This method overrides the method in {@link FancyStringList}.
     *
     * {@inheritDoc}
     */
    @Override
    public FancyStringList slice(int start, int stop, int step) {

        if (start < 0 || stop > size() || start > stop || step < 1) {
            throw new IndexOutOfBoundsException();
        } // if

        int indexCount = 0;
        FancyStringList sliced = new ArrayStringList();

        try {

            for (int i = start; i < stop; i += step) {

                sliced.add(indexCount, get(i));
                indexCount++;

            } // for loop that adds up the item from start to stop

        } catch (IndexOutOfBoundsException ioobe) {
            System.err.println("Index is out of bounds.");
        } // try

        return sliced;

    } // slice with StringList

    /**
     * This method overrides the method in {@link FancyStringList}.
     *
     * {@inheritDoc}
     */
    @Override
    public FancyStringList reverse() {

        int indexCount = 0;
        FancyStringList reversed = new ArrayStringList();

        for (int i = (size() - 1); i >= 0; i--) {

            reversed.add(indexCount, get(i));
            indexCount++;

        } // for

        return reversed;

    } // reverse

} // ArrayStringList
