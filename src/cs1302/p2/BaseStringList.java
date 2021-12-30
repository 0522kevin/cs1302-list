package cs1302.p2;

import cs1302.adt.StringList;
import cs1302.adt.FancyStringList;

/**
 * The class for StringList with no types.
 */
public abstract class BaseStringList implements FancyStringList {

    protected int size;

    /**
     * The default constructor that creates an object of {@code BaseStringList}.
     */
    public BaseStringList() {
        size = 0;
    } // Default constructor for BaseStringList

    /**
     * This method overrides the method in {@link StringList}.
     *
     * {@inheritDoc}
     */
    @Override
    public boolean append(String item) {
        return add(size(), item);
    } // append

    /**
     * This method overrides the method in {@link StringList}.
     *
     * {@inheritDoc}
     */
    @Override
    public boolean isEmpty() {

        if (size() == 0) {
            return true;
        } else {
            return false;
        } // if

    } // isEmpty

    /**
     * This method overrides the method in {@link StringList}.
     *
     * {@inheritDoc}
     */
    @Override
    public String makeString(String start, String sep, String end) {

        String returned = start;

        if (size() == 0) {
            returned = returned + end;
            return returned;
        } // if

        for (int i = 0; i < size(); i++) {

            if (i != size() - 1) {
                returned = returned + get(i) + sep;
            } else {
                returned = returned + get(i) + end;
            } // if

        } // for

        return returned;

    } // makeString

    /**
     * This method overrides the method in {@link StringList}.
     *
     * {@inheritDoc}
     */
    @Override
    public boolean prepend(String item) {
        return add(0, item);
    } // prepend

    /**
     * This method overrides the method in {@link StringList}.
     *
     * {@inheritDoc}
     */
    @Override
    public int size() {
        return size;
    } // size

    /**
     * This method overrides the method in {@link StringList}.
     *
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return makeString("[", ", ", "]");
    } // toString

    /**
     * This method overrides the method in {@link FancyStringList}.
     *
     * {@inheritDoc}
     */
    @Override
    public boolean add(int index, StringList items) {

        if (items == null) {
            throw new NullPointerException();
        } else if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException();
        } // if

        int indexCount = 0;
        String[] copyItems = new String[items.size()];

        try {

            for (int i = 0; i < copyItems.length; i++) {
                copyItems[i] = items.get(i);
            } // for

            for (int i = 0; i < copyItems.length; i++) {
                add(index, copyItems[indexCount]);
                indexCount++;
                index++;
            } // for

        } catch (NullPointerException npe) {
            System.err.println("Items is null.");
        } catch (IndexOutOfBoundsException ioobe) {
            System.err.println("Index is out of range.");
        } // try

        return !items.isEmpty();

    } // add with StringList

    /**
     * This method overrides the method in {@link FancyStringList}.
     *
     * {@inheritDoc}
     */
    @Override
    public boolean append(StringList items) {

        if (items == null) {
            throw new NullPointerException();
        } // if

        try {
            add(size(), items);
        } catch (NullPointerException npe) {
            System.err.println("Items is null.");
        } // try

        return !items.isEmpty();

    } // append with StringList

    /**
     * This method overrides the method in {@link FancyStringList}.
     *
     * {@inheritDoc}
     */
    @Override
    public boolean contains(int start, String target) {

        for (int i = start; i < size(); i++) {

            if (get(i).contains(target)) {
                return true;
            } // if

        } // for

        return false;
    } // contains

    /**
     * This method overrides the method in {@link FancyStringList}.
     *
     * {@inheritDoc}
     */
    @Override
    public int indexOf(int start, String target) {

        for (int i = start; i < size(); i++) {

            if (get(i).equals(target)) {
                return i;
            } // if

        } // for

        return -1;
    } // indexOf

    /**
     * This method overrides the method in {@link FancyStringList}.
     *
     * {@inheritDoc}
     */
    @Override
    public boolean prepend(StringList items) {
        if (items == null) {
            throw new NullPointerException();
        } // if

        try {
            add(0, items);
        } catch (NullPointerException npe) {
            System.err.println("Items is null.");
        } // try

        return !items.isEmpty();

    } // prepend with StringList

} // BaseStringList
