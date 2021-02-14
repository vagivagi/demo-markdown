package com.example.demo;

import net.steppschuh.markdowngenerator.text.Text;
import net.steppschuh.markdowngenerator.text.emphasis.BoldText;
import net.steppschuh.markdowngenerator.text.emphasis.ItalicText;
import net.steppschuh.markdowngenerator.text.emphasis.StrikeThroughText;
import org.springframework.stereotype.Service;

@Service
public class DocumentService {
    public String createMd() {
        StringBuilder sb = new StringBuilder()
                .append(new Text("I am normal")).append("\n")
                .append(new BoldText("I am bold")).append("\n")
                .append(new ItalicText("I am italic")).append("\n")
                .append(new StrikeThroughText("I am strike-through"));
        return sb.toString();
    }
}
