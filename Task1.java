package spaceshapes.views;

import java.util.ArrayList;
import java.util.List;

import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

import spaceshapes.CarrierShape;
import spaceshapes.Shape;
import spaceshapes.ShapeModel;



public class Task1 implements TreeModel{
	ShapeModel _model;
	List<TreeModelListener> _treeModelListener;
	
	
	public Task1(ShapeModel model) {
		_model = model;
		_treeModelListener = new ArrayList<TreeModelListener> ();
	}

	@Override
	public Object getRoot() {
		return _model.root();
	}

	@Override
	public Object getChild(Object parent, int index) {
		if (parent instanceof CarrierShape) {
			CarrierShape carrierShape = (CarrierShape)parent;
			try {
			Shape shape = carrierShape.shapeAt(index);
			return shape;
			}
			catch (IndexOutOfBoundsException e){
				return null;
			}
		}
		return null;
	}

	@Override
	public int getChildCount(Object parent) {
		if (parent instanceof CarrierShape) {
			CarrierShape carrierShape = (CarrierShape)parent;
			int count = carrierShape.shapeCount();
			return count;
		}
		return 0;
	}

	@Override
	public boolean isLeaf(Object shape) {
		if (shape instanceof CarrierShape) {
		return false;
		}
		return true;
	}

	@Override
	public void valueForPathChanged(TreePath path, Object newValue) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getIndexOfChild(Object parent, Object child) {
		if (parent instanceof CarrierShape && child instanceof Shape) {
			CarrierShape carriershape = (CarrierShape)parent;
			Shape shape = (Shape)child;
			int index = carriershape.indexOf(shape);
			return index;
		}
		return -1; 
	}

	@Override
	public void addTreeModelListener(TreeModelListener l) {
		_treeModelListener.add(l);
	}

	@Override
	public void removeTreeModelListener(TreeModelListener l) {
		_treeModelListener.remove(l);
		
	}

}
