package spaceshapes.views;

import javax.swing.JTree;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreePath;

import spaceshapes.CarrierShape;
import spaceshapes.Shape;
import spaceshapes.ShapeModel;
import spaceshapes.ShapeModelEvent;
import spaceshapes.ShapeModelEvent.EventType;
import spaceshapes.ShapeModelListener;

public class Task2 extends Task1 implements ShapeModelListener, TreeModelListener{
	JTree _jtree;
	private EventType _addShape = EventType.ShapeAdded;
	private EventType _moveShape = EventType.ShapeMoved;
	private EventType _removeShape = EventType.ShapeRemoved;
	TreePath _treePath;
	public Task2(ShapeModel model) {
		super(model);
		_jtree = new JTree(this);
	}

	@Override
	public void update(ShapeModelEvent event) {
		CarrierShape parent = event.parent();
		  
		if(parent != null) {
			Object[] treePath = parent.path().toArray();
			_treePath = new TreePath (treePath);
		} else {
			ShapeModel child = event.source();
			_treePath = new TreePath(child);
		}

		int [] index = new int[1];
		index[0] = event.index();
		Shape[] children = new Shape[1];
		children[0] = event.operand();
		//Shape[] children = event.operand();


		if (event.eventType().equals(_addShape)) {
			this.treeNodesInserted(new TreeModelEvent(this, _treePath, index, children));
		}
		else if (event.eventType().equals(_moveShape)) {
			this.treeNodesChanged(new TreeModelEvent(this, _treePath, index, children));
		}
		else if (event.eventType().equals(_removeShape)) {
			this.treeNodesRemoved(new TreeModelEvent(this, _treePath, index, children));
		}
		
	}

	@Override
	public void treeNodesChanged(TreeModelEvent e) {
		for(TreeModelListener listener: _treeModelListener) {
			listener.treeNodesChanged(e);
		}
	}

	@Override
	public void treeNodesInserted(TreeModelEvent e) {
		for(TreeModelListener listener: _treeModelListener) {
			listener.treeNodesInserted(e);
		}
		
	}

	@Override
	public void treeNodesRemoved(TreeModelEvent e) {
		for(TreeModelListener listener: _treeModelListener) {
			listener.treeNodesRemoved(e);
		}
	}

	@Override
	public void treeStructureChanged(TreeModelEvent e) {
		for(TreeModelListener listener: _treeModelListener) {
			listener.treeStructureChanged(e);
		}
		
	}

}
