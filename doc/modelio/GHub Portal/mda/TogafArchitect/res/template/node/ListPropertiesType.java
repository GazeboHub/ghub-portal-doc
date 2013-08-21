package com.modeliosoft.togaf.template;


import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;

import com.modeliosoft.documentpublisher.engine.generation.Styles;
import com.modeliosoft.templateeditor.newNodes.ITemplateNodeSelectionManager;
import com.modeliosoft.templateeditor.newNodes.gui.NewEditorImageManager;
import com.modeliosoft.templateeditor.newNodes.model.DefaultNodeGUI;
import com.modeliosoft.templateeditor.newNodes.model.DefaultNodeType;
import com.modeliosoft.templateeditor.newNodes.model.INodeBehavior;
import com.modeliosoft.templateeditor.newNodes.model.NodeInstance;
import com.modeliosoft.templateeditor.newNodes.production.ListNode.ListType;
import com.modeliosoft.templateeditor.utils.EditorImageManager;

public class ListPropertiesType extends DefaultNodeType
{
  private final String NAME = "ListItemSmart";
  private final String LABEL = "ListItemSmart";

  public ListPropertiesType()
  {
    addPossibleParent(ListType.class);

    setDefaultParameter("characterStyle", Styles.character.None.toString());
    setDefaultParameter("paragraphStyle", Styles.paragraph.Normal.toString());
    setDefaultParameter("text", "$Name");
    setDefaultParameter("value", "value");
    setDefaultParameter(ListPropertiesDefinition.TARGETSTEREOTYPE, "");
    setDefaultParameter("depends", Boolean.valueOf(true));
    setDefaultParameter("impacted", Boolean.valueOf(true));
    setDefaultParameter("listLevel", Integer.valueOf(1));
  }

  public DefaultNodeGUI getNodeGUI(NodeInstance instance, ITemplateNodeSelectionManager manager, Composite parent, int style)
  {
    return new ListPropertiesGUI(instance, manager, parent, style);
  }

  public INodeBehavior getNodeBehavior()
  {
    return new ListPropertiesBehavior(this.context);
  }

  public Object decodeParameter(String key, String value)
  {
    return null;
  }

  public String encodeParameter(String key, Object value)
  {
    return null;
  }

  public NodeInstance getParentList(NodeInstance instance) {
    NodeInstance p = instance.getParent();
    while (p != null) {
      if ((p.getNodeType() instanceof ListType)) {
        return p;
      }
      p = p.getParent();
    }
    return null;
  }

  public boolean isValid(NodeInstance instance)
  {
    NodeInstance parent = getParentList(instance);
    return (parent != null) && (super.isValid(instance));
  }

  public String getLabel()
  {
    return "ListItemSmart";
  }

  public String getDefaultName()
  {
    return "ListItemSmart";
  }

  public String getDescription()
  {
    return null;
  }

  public Image getIcon()
  {
    return NewEditorImageManager.getInstance().getIcon(EditorImageManager.TemplateNodeIcon.ListItemNode);
  }
}