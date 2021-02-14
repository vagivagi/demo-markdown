package com.example.demo;

import com.vladsch.flexmark.ext.gfm.strikethrough.StrikethroughExtension;
import com.vladsch.flexmark.ext.tables.TablesExtension;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.data.MutableDataSet;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;

@Controller
public class DocumentController {
    private final DocumentService documentService;

    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @GetMapping
    @ResponseBody
    String get() {
        return this.documentService.createMd();
    }

    @GetMapping(params = "md")
    String getMarkdownView(Model model) {
        model.addAttribute("body", this.documentService.createMd());
        return "template";
    }

    @GetMapping(params = "html")
    @ResponseBody
    String getHtml() {
        MutableDataSet options = new MutableDataSet();
        options.set(Parser.EXTENSIONS, Arrays.asList(TablesExtension.create(), StrikethroughExtension.create()));
        Parser parser = Parser.builder(options).build();
        HtmlRenderer renderer = HtmlRenderer.builder(options).build();
        StringBuilder htmlBuilder = new StringBuilder();
        for (String line : this.documentService.createMd().split("\n")) {
            Node document = parser.parse(line);
            htmlBuilder.append(renderer.render(document));
        }
        return htmlBuilder.toString();
    }
}
