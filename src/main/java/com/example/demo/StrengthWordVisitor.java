package com.example.demo;

import com.vladsch.flexmark.ast.Emphasis;
import com.vladsch.flexmark.util.ast.Visitor;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class StrengthWordVisitor implements Visitor<Emphasis> {
    List<String> strengthWords = new ArrayList<>();

    @Override
    public void visit(@NotNull Emphasis emphasis) {
        strengthWords.add(emphasis.getText().toString());
    }
}
