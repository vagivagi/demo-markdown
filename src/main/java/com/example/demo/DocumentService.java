package com.example.demo;

import com.vladsch.flexmark.ast.Emphasis;
import com.vladsch.flexmark.ast.Text;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.ast.NodeVisitor;
import com.vladsch.flexmark.util.ast.VisitHandler;
import net.steppschuh.markdowngenerator.text.emphasis.BoldText;
import net.steppschuh.markdowngenerator.text.emphasis.ItalicText;
import net.steppschuh.markdowngenerator.text.emphasis.StrikeThroughText;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

@Service
public class DocumentService {
    private static final Logger log = LoggerFactory.getLogger(DocumentService.class);

    public String createMd() {
        StringBuilder sb = new StringBuilder()
                .append(new Text("I am normal")).append("\n")
                .append(new BoldText("I am bold")).append("\n")
                .append(new ItalicText("I am italic")).append("\n")
                .append(new StrikeThroughText("I am strike-through"));
        return sb.toString();
    }

    public List<String> parseSample() throws IOException {
        Parser parser = Parser.builder().build();
        Resource markdownResource = new ClassPathResource("/static/sample.md");
        String fileContent = StreamUtils.copyToString(markdownResource.getInputStream(), Charset.defaultCharset());
        StrengthWordVisitor strengthWordVisitor = new StrengthWordVisitor();
        NodeVisitor visitor = new NodeVisitor(
                new VisitHandler<>(Emphasis.class, strengthWordVisitor::visit));
        visitor.visit(parser.parse(fileContent));
        return strengthWordVisitor.strengthWords;
    }

}
