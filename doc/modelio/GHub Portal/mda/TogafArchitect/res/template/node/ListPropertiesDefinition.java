package com.modeliosoft.togaf.template;

import com.modeliosoft.documentpublisher.engine.generation.Styles;
import com.modeliosoft.templateeditor.newNodes.model.NodeInstance;
import com.modeliosoft.templateeditor.newNodes.model.NodeParameterDefinition;

public class ListPropertiesDefinition extends NodeParameterDefinition
{
  public static final String CHARACTER_STYLE = "characterStyle";
  public static final String PARAGRAPH_STYLE = "paragraphStyle";
  public static final String TEXT = "text";
  public static final String VALUE = "value";
  public static final String DEPENDS = "depends";
  public static final String IMPACTED = "impacted";
  public static final String LIST_LEVEL = "listLevel";
  public static String TARGETSTEREOTYPE = "targetstereotype";

  public static Styles.character getCharacterStyle(NodeInstance instance) { String style = instance.getStringParameter("characterStyle");
    Styles.character ret;
    try { return Styles.character.valueOf(style);
    } catch (Exception e) {
      ret = Styles.character.Custom;
      ret.setCustomValue(style);
    }return ret;
  }

  public static int getListLevel(NodeInstance instance)
  {
    return instance.getIntegerParameter("listLevel");
  }
  public static Styles.paragraph getParagraphStyle(NodeInstance instance) { String style = instance.getStringParameter("paragraphStyle");
    Styles.paragraph ret;
    try { return Styles.paragraph.valueOf(style);
    } catch (Exception e) {
      ret = Styles.paragraph.Custom;
      ret.setCustomValue(style);
    }return ret;
  }

  public static String getText(NodeInstance instance)
  {
    return instance.getStringParameter("text");
  }

  public static void setCharacterStyle(NodeInstance instance, String characterStyle) {
    instance.setParameter("characterStyle", characterStyle);
  }

  public static void setListLevel(NodeInstance instance, int listLevel) {
    instance.setParameter("listLevel", Integer.valueOf(listLevel));
  }

  public static void setParagraphStyle(NodeInstance instance, String paragraphStyle) {
    instance.setParameter("paragraphStyle", paragraphStyle);
  }

  public static void setText(NodeInstance instance, String text) {
    instance.setParameter("text", text);
  }

  public static void setValue(NodeInstance instance, String value) {
    instance.setParameter("value", value);
  }

  public static String getValue(NodeInstance instance) {
    return instance.getStringParameter("value");
  }

  public static void setTargetStereotype(NodeInstance instance, String value)
  {
    instance.setParameter(TARGETSTEREOTYPE, value);
  }

  public static String getTargetStereotype(NodeInstance instance) {
    return instance.getStringParameter(TARGETSTEREOTYPE);
  }

  public static void setDepends(NodeInstance instance, boolean value)
  {
    instance.setParameter("depends", Boolean.valueOf(value));
  }

  public static boolean getDepends(NodeInstance instance) {
    return instance.getBooleanParameter("depends");
  }

  public static void setImpacted(NodeInstance instance, boolean value) {
    instance.setParameter("impacted", Boolean.valueOf(value));
  }

  public static boolean getImpacted(NodeInstance instance) {
    return instance.getBooleanParameter("impacted");
  }
}