package sdu.mmmi.softwareengineering.osgispritetest;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import sdu.mmmi.softwareengineering.osgicommon.services.IEntityProcessingService;
import sdu.mmmi.softwareengineering.osgicommon.services.IGamePluginService;

public class Activator implements BundleActivator {

    public void start(BundleContext context) throws Exception {
        context.registerService(IGamePluginService.class, new SpritePlugin(), null);
        context.registerService(IEntityProcessingService.class, new SpriteProcessing(), null);
    }

    public void stop(BundleContext context) throws Exception {
        // TODO add deactivation code here
    }

}
