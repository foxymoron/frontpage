package edu.ethz.frontpage;

import edu.stanford.nlp.util.CoreMap;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author jait
 */
public class OutputFileWriter {
    
    void writeHeadlineToFile(Path inputFile, String headline) throws Exception {
        // output for the first part of mid-term assignment
        //Path output = Paths.get("/home/jait/projects/nlp/data/eval2/peers/1/" + 
        //                        inputFile.getName(6).toString().replace("t", "").toUpperCase() + 
        //                        ".P.10.T.1." + inputFile.getFileName().toString());
        // output for the first part of mid-term assignment
        Path output = Paths.get("/home/jait/projects/nlp/data/eval3/peers/1/" + 
                                inputFile.getName(6).toString().replace("t", "").toUpperCase() + 
                                ".P.10.T.1." + inputFile.getFileName().toString());
        
        Charset charset = Charset.forName("UTF-8");
        try(BufferedWriter writer = Files.newBufferedWriter(output, charset)) {
            writer.write(headline);
        } catch (IOException ex) {
            System.err.format("IOException: %s%n", ex);
        }
    }
}
