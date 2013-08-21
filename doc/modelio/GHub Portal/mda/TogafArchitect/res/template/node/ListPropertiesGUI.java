package com.modeliosoft.togaf.template;

import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;

import com.modeliosoft.documentpublisher.engine.generation.Styles;
import com.modeliosoft.documentpublisher.engine.generation.StylesLabelProvider;
import com.modeliosoft.documentpublisher.gui.swt.models.contentProvider.ArrayListContentProvider;
import com.modeliosoft.templateeditor.i18n.Messages;
import com.modeliosoft.templateeditor.newNodes.ITemplateNodeSelectionManager;
import com.modeliosoft.templateeditor.newNodes.gui.attributefield.MetaAttributeField;
import com.modeliosoft.templateeditor.newNodes.model.DefaultNodeGUI;
import com.modeliosoft.templateeditor.newNodes.model.NodeInstance;
import com.modeliosoft.templateeditor.newNodes.utils.style.StylesManager;

public class ListPropertiesGUI extends DefaultNodeGUI
  implements Listener
{
  private MetaAttributeField metaAttributeField;
  private ComboViewer characterStyleCombo;
  private Text listLevelText;
  private ComboViewer paragraphStyleCombo;
  private Text textText;
  private Text textProperty;
  private Button isBookmarButton;
  private Button dependsCheck;
  private Button impactedCheck;
  private Text textTarget;

  public ListPropertiesGUI(NodeInstance instance, ITemplateNodeSelectionManager manager, Composite parent, int style)
  {
    super(instance, manager, parent, style);

    createContent();

    loadDataFromModel();
  }

  private void createContent() {
    GridLayout gridLayout = new GridLayout();
    gridLayout.numColumns = 1;
    setLayout(gridLayout);

    this.isBookmarButton = new Button(this, 32);
    this.isBookmarButton.setText(Messages.getString("node.ListItem.isBookmark"));
    this.isBookmarButton.addListener(13, this);

    Label ListItemStyleLabel = new Label(this, 0);
    ListItemStyleLabel.setText(Messages.getString("node.ListItem.paragrapheStyle"));

    this.paragraphStyleCombo = new ComboViewer(this, 8);
    GridData gd_paragraphSCombo = new GridData(4, 16777216, true, false);
    this.paragraphStyleCombo.getCombo().setLayoutData(gd_paragraphSCombo);
    this.paragraphStyleCombo.setContentProvider(new ArrayListContentProvider());
    this.paragraphStyleCombo.setLabelProvider(new StylesLabelProvider());
    this.paragraphStyleCombo.addSelectionChangedListener(new ISelectionChangedListener()
    {
      public void selectionChanged(SelectionChangedEvent evt)
      {
        StructuredSelection selection = (StructuredSelection)evt.getSelection();
        Object data = selection.getFirstElement();
        if (data != null)
          ListPropertiesDefinition.setParagraphStyle(ListPropertiesGUI.this.instance, data.toString());
      }
    });
    this.paragraphStyleCombo.setInput(StylesManager.getInstance().getParagraphStyles());

    Label characterStyleLabel = new Label(this, 0);
    characterStyleLabel.setText(Messages.getString("node.ListItem.characterStyle"));

    this.characterStyleCombo = new ComboViewer(this, 8);
    GridData gd_characterStyleCombo = new GridData(4, 16777216, true, false);
    this.characterStyleCombo.getCombo().setLayoutData(gd_characterStyleCombo);
    this.characterStyleCombo.setContentProvider(new ArrayListContentProvider());
    this.characterStyleCombo.setLabelProvider(new StylesLabelProvider());
    this.characterStyleCombo.addSelectionChangedListener(new ISelectionChangedListener()
    {
      public void selectionChanged(SelectionChangedEvent evt)
      {
        StructuredSelection selection = (StructuredSelection)evt.getSelection();
        Object data = selection.getFirstElement();
        if (data != null)
          ListPropertiesDefinition.setCharacterStyle(ListPropertiesGUI.this.instance, data.toString());
      }
    });
    this.characterStyleCombo.setInput(StylesManager.getInstance().getCharacterStyles());

    Label listLevelLabel = new Label(this, 0);
    listLevelLabel.setText(Messages.getString("node.ListItem.listLevel"));

    this.listLevelText = new Text(this, 2114);
    GridData gd_listLevelText = new GridData(4, 16777216, true, false, 2, 1);
    this.listLevelText.setLayoutData(gd_listLevelText);
    this.listLevelText.addListener(24, this);

    Label textLabel = new Label(this, 0);
    textLabel.setText(Messages.getString("node.ListItem.text"));

    this.textText = new Text(this, 2114);
    GridData gd_textText = new GridData(4, 4, true, true, 2, 1);
    this.textText.setLayoutData(gd_textText);
    this.textText.addListener(24, this);

    Label propertyLabel = new Label(this, 0);
    propertyLabel.setText("Dependency Stereotype");

    this.textProperty = new Text(this, 2112);
    GridData gd_textProperty = new GridData(4, 16777216, true, false, 2, 1);
    this.textProperty.setLayoutData(gd_textProperty);
    this.textProperty.addListener(24, this);

    Label targetLabel = new Label(this, 0);
    targetLabel.setText("Target Stereotype");

    this.textTarget = new Text(this, 2112);
    GridData gd_textTarget = new GridData(4, 16777216, true, false, 2, 1);
    this.textTarget.setLayoutData(gd_textTarget);
    this.textTarget.addListener(24, this);

    Label dependsLabel = new Label(this, 0);
    dependsLabel.setText("DependsOn");

    this.dependsCheck = new Button(this, 32);
    GridData gd_dependsCheck = new GridData(4, 16777216, true, false, 1, 1);
    this.dependsCheck.setLayoutData(gd_dependsCheck);
    this.dependsCheck.addListener(13, this);

    Label impactedLabel = new Label(this, 0);
    impactedLabel.setText("Impacted");

    this.impactedCheck = new Button(this, 32);
    GridData gd_impactedCheck = new GridData(4, 16777216, true, false, 1, 1);
    this.impactedCheck.setLayoutData(gd_impactedCheck);
    this.impactedCheck.addListener(13, this);

    Label metaattributesLabel = new Label(this, 0);
    metaattributesLabel.setText(Messages.getString("node.ListItem.attributes"));

    this.metaAttributeField = new MetaAttributeField(this, this.textText);
    this.metaAttributeField.setLayoutData(new GridData(4, 16777216, true, false));
  }

  public void handleEvent(Event event) {
    if (event.widget == this.textText) {
      ListPropertiesDefinition.setText(this.instance, this.textText.getText());
    } else if (event.widget == this.textProperty) {
      ListPropertiesDefinition.setValue(this.instance, this.textProperty.getText());
    } else if (event.widget == this.textTarget) {
      ListPropertiesDefinition.setTargetStereotype(this.instance, this.textTarget.getText());
    } else if (event.widget == this.impactedCheck) {
      ListPropertiesDefinition.setImpacted(this.instance, this.impactedCheck.getSelection());
    } else if (event.widget == this.dependsCheck) {
      ListPropertiesDefinition.setDepends(this.instance, this.dependsCheck.getSelection());
    } else if (event.widget == this.listLevelText) {
      try {
        ListPropertiesDefinition.setListLevel(this.instance, Integer.parseInt(this.listLevelText.getText()));
      } catch (Exception localException) {
      }
    }
    else if (event.widget == this.isBookmarButton) {
      boolean enabled = this.isBookmarButton.getSelection();
      this.instance.setBookmark(enabled);
    }
    this.selectionManager.refresh(this.instance);
  }

  private void loadDataFromModel() {
    Styles.paragraph paragraphStyle = ListPropertiesDefinition.getParagraphStyle(this.instance);
    if (paragraphStyle == null) {
      paragraphStyle = Styles.paragraph.Normal;
    }

    StructuredSelection s = new StructuredSelection(paragraphStyle.toString());
    this.paragraphStyleCombo.setSelection(s);

    Styles.character characterStyle = ListPropertiesDefinition.getCharacterStyle(this.instance);
    if (characterStyle == null) {
      characterStyle = Styles.character.None;
    }

    s = new StructuredSelection(characterStyle.toString());
    this.characterStyleCombo.setSelection(s);
    this.textText.setText(ListPropertiesDefinition.getText(this.instance));
    this.textProperty.setText(ListPropertiesDefinition.getValue(this.instance));
    this.dependsCheck.setSelection(ListPropertiesDefinition.getDepends(this.instance));
    this.impactedCheck.setSelection(ListPropertiesDefinition.getImpacted(this.instance));
    this.metaAttributeField.setInput(this.instance.getParentMetaclass());
    this.listLevelText.setText(Integer.toString(ListPropertiesDefinition.getListLevel(this.instance)));
    this.isBookmarButton.setSelection(this.instance.isBookmark());
    this.textTarget.setText(ListPropertiesDefinition.getTargetStereotype(this.instance));
  }
}