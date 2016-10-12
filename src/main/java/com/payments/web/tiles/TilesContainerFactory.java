package com.payments.web.tiles;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.apache.tiles.TilesApplicationContext;
import org.apache.tiles.context.TilesRequestContextFactory;
import org.apache.tiles.factory.BasicTilesContainerFactory;


public class TilesContainerFactory extends BasicTilesContainerFactory {
    
    private static final Logger logger = Logger.getLogger(TilesContainerFactory.class);
    private static final String TILES_CONFIGURATION = "/WEB-INF/tiles/tiles-scheme.xml";
    
    @Override
    protected List<URL> getSourceURLs(TilesApplicationContext applicationContext, TilesRequestContextFactory contextFactory) 
    {
        List<URL> urls = new ArrayList<URL>();
        try {
            urls.add(applicationContext.getResource(TILES_CONFIGURATION));
        } 
        catch (IOException e) {
            logger.error(e.getMessage(), e);
            throw new IllegalStateException(e.getMessage());
        }
        return urls;
    }
    
}
