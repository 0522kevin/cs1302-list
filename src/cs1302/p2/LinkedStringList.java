package cs1302.p2;

import cs1302.adt.Node;
import cs1302.adt.StringList;
import cs1302.adt.FancyStringList;

/**
 * The class for StringList with {@link Node} types.
 */
public class LinkedStringList extends BaseStringList {

    private Node head;

    /**
     * This default constructor creates a {@code LinkedStringList} object.
     */
    public LinkedStringList() {

        super();
        head = new Node(null);

    } // Default constructor for LinkedStringList

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

        boolean didItHappen = false;
        Node tempNode = head;
        Node anotherTempNode = null;

        try {

            if (index == 0) {

                if (tempNode.getItem() != null) {
                    anotherTempNode = tempNode;
                    head = new Node(item);
                    head.setNext(anotherTempNode);
                } else {
                    tempNode.setItem(item);
                } // if when index == 0

            } else {

                for (int i = 0; i < index - 1; i++) {
                    tempNode = tempNode.getNext();
                } // for loop that takes the tempNode to the node one before index

                if (tempNode.hasNext()) {
                    anotherTempNode = tempNode.getNext();
                    tempNode.setNext(new Node(item));
                    tempNode.getNext().setNext(anotherTempNode);
                } else {
                    tempNode.setNext(new Node(item));
                } // if when tempNode has a next node or not

            } // if when index != 0

            size++;
            didItHappen = true;

        } catch (NullPointerException npe) {
            System.err.println("Item is null.");
        } catch (IllegalArgumentException iae) {
            System.err.println("Item is empty.");
        } catch (IndexOutOfBoundsException ioobe) {
            System.err.println("Index is out of range.");
        } // try

        return didItHappen;

    } // add

    /**
     * This method overrides the method in {@link StringList}.
     *
     * {@inheritDoc}
     */
    @Override
    public void clear() {

        head = new Node(null);
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

        String toBeReturned = null;
        Node tempNode = head;

        try {

            for (int i = 0; i < index; i++) {
                tempNode = tempNode.getNext();
            } // for loop that takes tempNode to index

            toBeReturned = tempNode.getItem();

        } catch (IndexOutOfBoundsException ioobe) {
            System.err.println("Index is out of range.");
        } // try

        return toBeReturned;

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

        String removedString = null;
        Node tempNode = head;
        Node anotherTempNode;

        try {

            if (index == 0) {

                if (tempNode.hasNext()) {
                    anotherTempNode = tempNode.getNext();
                    removedString = tempNode.getItem();
                    head = anotherTempNode;
                } else {
                    removedString = tempNode.getItem();
                    tempNode.setItem(null);
                } // if when index == 0

            } else {

                for (int i = 0; i < index - 1; i++) {
                    tempNode = tempNode.getNext();
                } // for loop that takes tempNode to (index - 1)

                if (tempNode.getNext().hasNext()) {
                    removedString = tempNode.getNext().getItem();
                    anotherTempNode = tempNode.getNext().getNext();
                    tempNode.setNext(anotherTempNode);
                } else {
                    removedString = tempNode.getNext().getItem();
                    tempNode.setNext(null);
                } // if when tempNode has a next Node

            } // if when index != 0

        } catch (IndexOutOfBoundsException ioobe) {
            System.err.println("Index is out of range.");
        } // try

        return removedString;

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

        int indexCount = start;
        Node tempNode = head;
        StringList sliced = new LinkedStringList();

        try {

            for (int i = 0; i < start; i++) {
                tempNode = tempNode.getNext();
            } // for loop that takes tempNode to start

            for (int i = 0; i < (stop - start); i++) {
                sliced.add(i, get(indexCount));
                indexCount++;
            } // for loop that adds get() to sliced

        } catch (IndexOutOfBoundsException ioobe) {
            System.err.println("Illegal endpoint index value.");
        } // try

        return sliced;

    } // slice

    /**
     * This constructor creates a new {@code LinkedStringList} object
     * that adds a preexisting {@code StringList} object to the end.
     *
     * @param other  StringList object that gets attched to the original.
     */
    public LinkedStringList(StringList other) {

        super();

        if (other == null) {
            throw new NullPointerException();
        } // if

        try {

            head = new Node(null);

            for (int i = 0; i < other.size(); i++) {

                add(i, other.get(i));

            } // for

        } catch (NullPointerException npe) {
            System.err.println("Other is null.");
        } // try


    } // LinkedStringList with StringList

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
        FancyStringList sliced = new LinkedStringList();

        try {

            for (int i = start; i < stop; i += step) {

                sliced.add(indexCount, get(i));
                indexCount++;

            } // for

        } catch (IndexOutOfBoundsException ioobe) {
            System.err.println("Index is out of bounds.");
        } // try

        return sliced;

    } // slice with FancyStringList

    /**
     * This method overrides the method in {@link FancyStringList}.
     *
     * {@inheritDoc}
     */
    @Override
    public FancyStringList reverse() {

        int indexCount = 0;
        FancyStringList reversed = new LinkedStringList();

        for (int i = (size() - 1); i >= 0; i--) {

            reversed.add(indexCount, get(i));
            indexCount++;

        } // for

        return reversed;

    } // reverse

} // LinkedStringList
