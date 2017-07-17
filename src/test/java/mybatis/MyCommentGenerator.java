package mybatis;

import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.api.dom.xml.XmlElement;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

/**
 * Created by hjl on 2016/12/6.
 */
public class MyCommentGenerator implements CommentGenerator {
	private Properties properties = new Properties();
	private boolean suppressDate = false;
	private boolean suppressAllComments = false;
	private boolean addRemarkComments = false;
	private SimpleDateFormat dateFormat;

	public MyCommentGenerator() {
	}

	public void addJavaFileComment(CompilationUnit compilationUnit) {
	}

	public void addComment(XmlElement xmlElement) {
//		if(!this.suppressAllComments) {
//			xmlElement.addElement(new TextElement("<!--"));
//			StringBuilder sb = new StringBuilder();
//			sb.append("  WARNING - ");
//			sb.append("@mbg.generated");
//			xmlElement.addElement(new TextElement(sb.toString()));
//			xmlElement.addElement(new TextElement("  This element is automatically generated by MyBatis Generator, do not modify."));
//			String s = this.getDateString();
//			if(s != null) {
//				sb.setLength(0);
//				sb.append("  This element was generated on ");
//				sb.append(s);
//				sb.append('.');
//				xmlElement.addElement(new TextElement(sb.toString()));
//			}
//
//			xmlElement.addElement(new TextElement("-->"));
//		}
	}

	public void addRootComment(XmlElement rootElement) {
	}

	public void addConfigurationProperties(Properties properties) {
//		this.properties.putAll(properties);
//		this.suppressDate = StringUtility.isTrue(properties.getProperty("suppressDate"));
//		this.suppressAllComments = StringUtility.isTrue(properties.getProperty("suppressAllComments"));
//		this.addRemarkComments = StringUtility.isTrue(properties.getProperty("addRemarkComments"));
//		String dateFormatString = properties.getProperty("dateFormat");
//		if(StringUtility.stringHasValue(dateFormatString)) {
//			this.dateFormat = new SimpleDateFormat(dateFormatString);
//		}

	}

	protected void addJavadocTag(JavaElement javaElement, boolean markAsDoNotDelete) {
//		javaElement.addJavaDocLine(" *");
//		StringBuilder sb = new StringBuilder();
//		sb.append(" * ");
//		sb.append("@mbg.generated");
//		if(markAsDoNotDelete) {
//			sb.append(" do_not_delete_during_merge");
//		}
//
//		String s = this.getDateString();
//		if(s != null) {
//			sb.append(' ');
//			sb.append(s);
//		}
//
//		javaElement.addJavaDocLine(sb.toString());
	}

	protected String getDateString() {
		return this.suppressDate?null:(this.dateFormat != null?this.dateFormat.format(new Date()):(new Date()).toString());
	}

	public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable) {
//		if(!this.suppressAllComments) {
//			StringBuilder sb = new StringBuilder();
//			innerClass.addJavaDocLine("/**");
//			innerClass.addJavaDocLine(" * This class was generated by MyBatis Generator.");
//			sb.append(" * This class corresponds to the database table ");
//			sb.append(introspectedTable.getFullyQualifiedTable());
//			innerClass.addJavaDocLine(sb.toString());
//			this.addJavadocTag(innerClass, false);
//			innerClass.addJavaDocLine(" */");
//		}
	}

	public void addModelClassComment(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
//		if(!this.suppressAllComments && this.addRemarkComments) {
//			StringBuilder sb = new StringBuilder();
//			topLevelClass.addJavaDocLine("/**");
//			String remarks = introspectedTable.getRemarks();
//			if(this.addRemarkComments && StringUtility.stringHasValue(remarks)) {
//				topLevelClass.addJavaDocLine(" * Database Table Remarks:");
//				String[] remarkLines = remarks.split(System.getProperty("line.separator"));
//				String[] var6 = remarkLines;
//				int var7 = remarkLines.length;
//
//				for(int var8 = 0; var8 < var7; ++var8) {
//					String remarkLine = var6[var8];
//					topLevelClass.addJavaDocLine(" *   " + remarkLine);
//				}
//			}
//
//			topLevelClass.addJavaDocLine(" *");
//			topLevelClass.addJavaDocLine(" * This class was generated by MyBatis Generator.");
//			sb.append(" * This class corresponds to the database table ");
//			sb.append(introspectedTable.getFullyQualifiedTable());
//			topLevelClass.addJavaDocLine(sb.toString());
//			this.addJavadocTag(topLevelClass, true);
//			topLevelClass.addJavaDocLine(" */");
//		}
	}

	public void addEnumComment(InnerEnum innerEnum, IntrospectedTable introspectedTable) {
//		if(!this.suppressAllComments) {
//			StringBuilder sb = new StringBuilder();
//			innerEnum.addJavaDocLine("/**");
//			innerEnum.addJavaDocLine(" * This enum was generated by MyBatis Generator.");
//			sb.append(" * This enum corresponds to the database table ");
//			sb.append(introspectedTable.getFullyQualifiedTable());
//			innerEnum.addJavaDocLine(sb.toString());
//			this.addJavadocTag(innerEnum, false);
//			innerEnum.addJavaDocLine(" */");
//		}
	}

	public void addFieldComment(Field field, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
		if(!this.suppressAllComments) {

			String remarks = introspectedColumn.getRemarks();
			if(remarks != null) {
				field.addJavaDocLine("/**");
				StringBuilder sb = new StringBuilder();
				sb.append(introspectedColumn.getRemarks());
				field.addJavaDocLine(sb.toString());
				field.addJavaDocLine(" */");
			}

		}
	}

	public void addFieldComment(Field field, IntrospectedTable introspectedTable) {
		if(!this.suppressAllComments) {
			StringBuilder sb = new StringBuilder();
			field.addJavaDocLine("/**");
			sb.append(introspectedTable.getRemarks());
			field.addJavaDocLine(sb.toString());
			field.addJavaDocLine(" */");
		}
	}

	public void addGeneralMethodComment(Method method, IntrospectedTable introspectedTable) {
//		if(!this.suppressAllComments) {
//			StringBuilder sb = new StringBuilder();
//			method.addJavaDocLine("/**");
//			method.addJavaDocLine(" * This method was generated by MyBatis Generator.");
//			sb.append(" * This method corresponds to the database table ");
//			sb.append(introspectedTable.getFullyQualifiedTable());
//			method.addJavaDocLine(sb.toString());
//			this.addJavadocTag(method, false);
//			method.addJavaDocLine(" */");
//		}
	}

	public void addGetterComment(Method method, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
//		if(!this.suppressAllComments) {
//			StringBuilder sb = new StringBuilder();
//			method.addJavaDocLine("/**");
//			method.addJavaDocLine(" * This method was generated by MyBatis Generator.");
//			sb.append(" * This method returns the value of the database column ");
//			sb.append(introspectedTable.getFullyQualifiedTable());
//			sb.append('.');
//			sb.append(introspectedColumn.getActualColumnName());
//			method.addJavaDocLine(sb.toString());
//			method.addJavaDocLine(" *");
//			sb.setLength(0);
//			sb.append(" * @return the value of ");
//			sb.append(introspectedTable.getFullyQualifiedTable());
//			sb.append('.');
//			sb.append(introspectedColumn.getActualColumnName());
//			method.addJavaDocLine(sb.toString());
//			this.addJavadocTag(method, false);
//			method.addJavaDocLine(" */");
//		}
	}

	public void addSetterComment(Method method, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
//		if(!this.suppressAllComments) {
//			StringBuilder sb = new StringBuilder();
//			method.addJavaDocLine("/**");
//			method.addJavaDocLine(" * This method was generated by MyBatis Generator.");
//			sb.append(" * This method sets the value of the database column ");
//			sb.append(introspectedTable.getFullyQualifiedTable());
//			sb.append('.');
//			sb.append(introspectedColumn.getActualColumnName());
//			method.addJavaDocLine(sb.toString());
//			method.addJavaDocLine(" *");
//			Parameter parm = (Parameter)method.getParameters().get(0);
//			sb.setLength(0);
//			sb.append(" * @param ");
//			sb.append(parm.getName());
//			sb.append(" the value for ");
//			sb.append(introspectedTable.getFullyQualifiedTable());
//			sb.append('.');
//			sb.append(introspectedColumn.getActualColumnName());
//			method.addJavaDocLine(sb.toString());
//			this.addJavadocTag(method, false);
//			method.addJavaDocLine(" */");
//		}
	}

	public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable, boolean markAsDoNotDelete) {
//		if(!this.suppressAllComments) {
//			StringBuilder sb = new StringBuilder();
//			innerClass.addJavaDocLine("/**");
//			innerClass.addJavaDocLine(" * This class was generated by MyBatis Generator.");
//			sb.append(" * This class corresponds to the database table ");
//			sb.append(introspectedTable.getFullyQualifiedTable());
//			innerClass.addJavaDocLine(sb.toString());
//			this.addJavadocTag(innerClass, markAsDoNotDelete);
//			innerClass.addJavaDocLine(" */");
//		}
	}
}
