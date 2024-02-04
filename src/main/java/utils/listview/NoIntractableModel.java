package utils.listview;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.MultipleSelectionModel;

public class NoIntractableModel<T> extends MultipleSelectionModel<T> {

    @Override
    public ObservableList<Integer> getSelectedIndices() {
        return FXCollections.emptyObservableList();
    }

    @Override
    public ObservableList<T> getSelectedItems() {
        return FXCollections.emptyObservableList();
    }

    @Override
    public void selectIndices(int index, int... indices) {
        //Is empty on propose to make the list do nothing
    }

    @Override
    public void selectAll() {
        //Is empty on propose to make the list do nothing
    }

    @Override
    public void selectFirst() {
        //Is empty on propose to make the list do nothing
    }

    @Override
    public void selectLast() {
        //Is empty on propose to make the list do nothing
    }

    @Override
    public void clearAndSelect(int index) {
        //Is empty on propose to make the list do nothing
    }

    @Override
    public void select(int index) {
        //Is empty on propose to make the list do nothing
    }

    @Override
    public void select(T obj) {
        //Is empty on propose to make the list do nothing
    }

    @Override
    public void clearSelection(int index) {
        //Is empty on propose to make the list do nothing
    }

    @Override
    public void clearSelection() {
        //Is empty on propose to make the list do nothing
    }

    @Override
    public boolean isSelected(int index) {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return true;
    }

    @Override
    public void selectPrevious() {
        //Is empty on propose to make the list do nothing
    }

    @Override
    public void selectNext() {
        //Is empty on propose to make the list do nothing
    }
}
