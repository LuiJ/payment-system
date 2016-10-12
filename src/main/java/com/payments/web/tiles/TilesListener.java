package com.payments.web.tiles;

import org.apache.tiles.TilesApplicationContext;
import org.apache.tiles.factory.AbstractTilesContainerFactory;
import org.apache.tiles.startup.AbstractTilesInitializer;
import org.apache.tiles.startup.TilesInitializer;
import org.apache.tiles.web.startup.AbstractTilesListener;


public class TilesListener extends AbstractTilesListener {

    @Override
    protected TilesInitializer createTilesInitializer() {
        return new TilesListenerInitializer();
    }

    private static class TilesListenerInitializer extends AbstractTilesInitializer 
    {
        @Override
        protected AbstractTilesContainerFactory createContainerFactory(TilesApplicationContext context) 
        {
            return new TilesContainerFactory();
        }
    }
}
