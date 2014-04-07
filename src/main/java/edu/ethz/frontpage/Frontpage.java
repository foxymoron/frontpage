package edu.ethz.frontpage;

import java.nio.file.FileVisitResult;
import static java.nio.file.FileVisitResult.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jait
 */
public class Frontpage {
    public static void main(String[] args) throws Exception{
        
        if(args.length != 2) {
            System.out.println("Usage: path/to/input/folder");
        }
        
        Path input = Paths.get(args[0]);
        Path output = Paths.get(args[1]);
        ParseFiles parser = new ParseFiles();
        Files.walkFileTree(input, parser);
    }
}

class ParseFiles extends SimpleFileVisitor<Path> {
    SummaryFileParser fp = new SummaryFileParser();
    HeadlineGenerator hg = new HeadlineGenerator();
    
    @Override
    public FileVisitResult visitFile(Path input, BasicFileAttributes attr) {
        if (attr.isRegularFile()) {
            try {
                // Printing the text to stdout for the time being
                hg.generateHeadline(input, fp.parse(input));
            } catch (Exception ex) {
                System.out.println(ex);
            }
        
        }
        return CONTINUE;
    }
}