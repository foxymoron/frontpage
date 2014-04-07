package edu.ethz.frontpage;

import edu.stanford.nlp.ling.CoreAnnotations.PartOfSpeechAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.SentencesAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TextAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TokensAnnotation;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;
import java.nio.file.Path;
import java.util.List;
import java.util.Properties;

/**
 *
 * @author jait
 */
public class HeadlineGenerator {

    void generateHeadline(Path inputFile, String text) throws Exception {
        Properties props = new Properties();
        props.put("annotators", "tokenize, ssplit, pos, lemma");
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
        
        // create an empty notation with just the text
        Annotation document = new Annotation(text);
        
        // run all annotators on this text
        pipeline.annotate(document);
        
        // these are all the sentences in this document
        List<CoreMap> sentences = document.get(SentencesAnnotation.class);
        
        StringBuilder headline = new StringBuilder();
        
        CoreMap sentence = sentences.get(0);
        for(CoreLabel token: sentence.get(TokensAnnotation.class)) {
            String word = token.get(TextAnnotation.class);
            String pos = token.get(PartOfSpeechAnnotation.class);
            
            if(pos.matches("CD") || pos.matches("CC") || pos.matches("DT") || 
               pos.matches("EX") || pos.matches("IN") || pos.matches("LS") ||
               pos.matches("MD") || pos.matches("PDT") || pos.matches("POS") ||
               pos.matches("PRP") || pos.matches("PRP$") || pos.matches("RP") ||
               pos.matches("TO") || pos.matches("UH") || pos.matches("WDT") ||
               pos.matches("WP") || pos.matches("WP$") || pos.matches("WRB"))
                continue;
            else {
                if(!word.matches(".")) {
                    headline.append(word);                
                    headline.append(" ");
                } else {
                    headline.deleteCharAt(headline.length() - 1);
                    headline.append(word);
                }
                 
            }
        }
        
        // write the headline to a file - for the first part of mid-term
        OutputFileWriter writer = new OutputFileWriter();
        //writer.writeHeadlineToFile(inputFile, sentences.get(0).toString());
        writer.writeHeadlineToFile(inputFile, headline.toString());
    }
    
}
