package com.thanglv.hrfreelancer.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.lucene.analysis.charfilter.HTMLStripCharFilterFactory;
import org.apache.lucene.analysis.core.LowerCaseFilterFactory;
import org.apache.lucene.analysis.icu.ICUNormalizer2FilterFactory;
import org.apache.lucene.analysis.icu.segmentation.ICUTokenizerFactory;
import org.apache.lucene.analysis.miscellaneous.ASCIIFoldingFilterFactory;
import org.apache.lucene.analysis.ngram.EdgeNGramFilterFactory;
import org.hibernate.search.backend.lucene.analysis.LuceneAnalysisConfigurationContext;
import org.hibernate.search.backend.lucene.analysis.LuceneAnalysisConfigurer;

public class MyAnalysisConfigurer implements LuceneAnalysisConfigurer {

    private final Logger logger = LogManager.getLogger();

	@Override
    public void configure(LuceneAnalysisConfigurationContext context) {
        logger.info("Init LuceneAnalysisConfigurer");
        context.analyzer("customAnalyzer").custom()
                .tokenizer(ICUTokenizerFactory.class)
                .tokenFilter(ICUNormalizer2FilterFactory.class)
                .tokenFilter(LowerCaseFilterFactory.class)
                .tokenFilter(ASCIIFoldingFilterFactory.class)
                .tokenFilter(EdgeNGramFilterFactory.class)
                .param("minGramSize", "2")
                .param("maxGramSize", "10")
                .charFilter(HTMLStripCharFilterFactory.class);
    }

}
