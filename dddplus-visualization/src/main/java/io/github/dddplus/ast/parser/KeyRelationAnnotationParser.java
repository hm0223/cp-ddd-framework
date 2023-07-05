/*
 * Copyright DDDplus Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */
package io.github.dddplus.ast.parser;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.expr.AnnotationExpr;
import com.github.javaparser.ast.expr.MemberValuePair;
import com.github.javaparser.ast.expr.NormalAnnotationExpr;
import io.github.dddplus.ast.model.KeyRelationEntry;
import lombok.Getter;

/**
 * {@link io.github.dddplus.dsl.KeyRelation}
 */
@Getter
public class KeyRelationAnnotationParser {
    private final ClassOrInterfaceDeclaration leftClassDeclaration;

    public KeyRelationAnnotationParser(ClassOrInterfaceDeclaration classDeclaration) {
        this.leftClassDeclaration = classDeclaration;
    }

    public KeyRelationEntry parse(AnnotationExpr keyRelation) {
        KeyRelationEntry entry = new KeyRelationEntry();
        entry.setJavadoc(JavaParserUtil.javadocFirstLineOf(leftClassDeclaration));
        entry.setLeftClass(leftClassDeclaration.getNameAsString());
        entry.setLeftClassPackageName(JavaParserUtil.packageName(leftClassDeclaration));

        NormalAnnotationExpr normalAnnotationExpr = (NormalAnnotationExpr) keyRelation;
        for (MemberValuePair memberValuePair : normalAnnotationExpr.getPairs()) {
            switch (memberValuePair.getNameAsString()) {
                case "type":
                    entry.setTypeInString(AnnotationFieldParser.stringFieldValue(memberValuePair));
                    break;

                case "remark":
                    entry.setRemark(AnnotationFieldParser.stringFieldValue(memberValuePair));
                    break;

                case "contextual":
                    entry.setContextual(true);
                    break;

                case "whom":
                    entry.setRightClass(AnnotationFieldParser.stringFieldValue(memberValuePair));
                    break;
            }
        }

        return entry;
    }
}
