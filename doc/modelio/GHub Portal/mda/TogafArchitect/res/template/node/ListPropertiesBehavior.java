package com.modeliosoft.togaf.template;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import com.modeliosoft.documentpublisher.engine.generation.AbstractDocument;
import com.modeliosoft.documentpublisher.engine.generation.ActivationContext;
import com.modeliosoft.documentpublisher.engine.generation.IterationContext;
import com.modeliosoft.modelio.api.model.analyst.IPropertyValue;
import com.modeliosoft.modelio.api.model.uml.infrastructure.IDependency;
import com.modeliosoft.modelio.api.model.uml.infrastructure.IElement;
import com.modeliosoft.modelio.api.model.uml.infrastructure.IModelElement;
import com.modeliosoft.templateeditor.newNodes.model.DefaultProductionBehavior;
import com.modeliosoft.templateeditor.newNodes.model.NodeInstance;
import com.modeliosoft.templateeditor.nodes.utils.MacroReplacer;

public class ListPropertiesBehavior extends DefaultProductionBehavior
{
  private boolean additem;

  public ListPropertiesBehavior()
  {
      
  }

  public void beginProduction(NodeInstance instance, IElement elt, int index, int maxIndex, IterationContext ctx)
    throws Exception
  {
    this.additem = false;
    AbstractDocument myDocument = getContext().getCurrentOutput();

    String value = ListPropertiesDefinition.getValue(instance);
    String targetStereotype = ListPropertiesDefinition.getTargetStereotype(instance);

    List Values = new ArrayList();
    if ((elt instanceof IModelElement)) {
      IModelElement elem = (IModelElement)elt;
      if (ListPropertiesDefinition.getDepends(instance)) {
        List<IDependency> dependends = elem.getDependsOnDependency();
        for (IDependency dep : dependends) {
          if ((!dep.isStereotyped(value)) || (
            (!targetStereotype.equals("")) && (!dep.getDependsOn().isStereotyped(targetStereotype)))) continue;
          Values.add(dep.getDependsOn());
        }

      }

      if (ListPropertiesDefinition.getImpacted(instance)) {
        List<IDependency> dependends = elem.getImpactedDependency();
        for (IDependency dep : dependends) {
          if ((!dep.isStereotyped(value)) || (
            (!targetStereotype.equals("")) && (!dep.getImpacted().isStereotyped(targetStereotype)))) continue;
          Values.add(dep.getImpacted());
        }

      }

    }

    if (Values.size() == 0) {
      return;
    }
    this.additem = true;

    myDocument.createListItem("", ListPropertiesDefinition.getParagraphStyle(instance), ListPropertiesDefinition.getCharacterStyle(instance), ListPropertiesDefinition.getListLevel(instance));
    Vector baseText = MacroReplacer.splitText(ListPropertiesDefinition.getText(instance));

    for (int i = 0; i < baseText.size(); i++) {
      String base = (String)baseText.get(i);

      if (base.startsWith("$")) {
        String replacedText = MacroReplacer.macroReplacement(elt, (String)baseText.get(i));

        if (base.startsWith("$")) {
          String commentZone = MacroReplacer.createCommentFromElement(elt, base);

          if (replacedText.equals("")) {
            if ((elt instanceof IPropertyValue))
              replacedText = "";
            else {
              replacedText = "";
            }
          }

          myDocument.appendCharacters(replacedText, ListPropertiesDefinition.getCharacterStyle(instance), commentZone);
        }
        else {
          myDocument.appendCharacters(replacedText, ListPropertiesDefinition.getCharacterStyle(instance), null);
        }
      } else {
        myDocument.appendCharacters(base, ListPropertiesDefinition.getCharacterStyle(instance), null);
      }
    }

    for (int i = 0; i < Values.size(); i++) {
      if (i == 0) {
        myDocument.appendReference(((IModelElement)Values.get(i)).getName(), "_" + ((IModelElement)Values.get(i)).getIdentifier());
      } else {
        myDocument.appendCharacters(", ", ListPropertiesDefinition.getCharacterStyle(instance), null);
        myDocument.appendReference(((IModelElement)Values.get(i)).getName(), "_" + ((IModelElement)Values.get(i)).getIdentifier());
      }

    }

    appendBookmark(instance, myDocument, elt);
  }

  public boolean endProduction(NodeInstance instance, IterationContext ctx) throws Exception
  {
    AbstractDocument myDocument = getContext().getCurrentOutput();

    if (this.additem) {
      myDocument.appendListItem();
    }

    return true;
  }

  public ListPropertiesBehavior(ActivationContext context) {
    this();
    setContext(context);
  }
}