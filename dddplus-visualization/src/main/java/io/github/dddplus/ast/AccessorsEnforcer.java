package io.github.dddplus.ast;

import io.github.dddplus.model.encapsulation.Accessors;

import java.io.File;

/**
 * 确保代码符合{@link Accessors}规范.
 */
public class AccessorsEnforcer {
    private File[] dirs;

    public AccessorsEnforcer scan(File... dirs) {
        this.dirs = dirs;
        return this;
    }

    public void enforce(FileWalker.Filter filter) {
        FileWalker.Filter actualFilter = new DomainModelAnalyzer.ActualFilter(filter);
        for (File dir : dirs) {
            AccessorsAstNodeVisitor accessorsAstNodeVisitor = new AccessorsAstNodeVisitor();

            // 1. parse annotated methods
            accessorsAstNodeVisitor.parseMethodDeclaration = true;
            new FileWalker(actualFilter, (level, path, file) -> {
                accessorsAstNodeVisitor.visit(FileWalker.silentParse(file), null);
            }).walkFrom(dir);

            // 2. parse method calls
            accessorsAstNodeVisitor.parseMethodDeclaration = false;
            new FileWalker(actualFilter, (level, path, file) -> {
                accessorsAstNodeVisitor.visit(FileWalker.silentParse(file), null);
            }).walkFrom(dir);
        }
    }
}
