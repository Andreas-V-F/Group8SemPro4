package sdu.mmmi.softwareengineering.osgiai;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import sdu.mmmi.softwareengineering.osgicommon.services.IEntityProcessingService;
import sdu.mmmi.softwareengineering.osgicommon.services.IGamePluginService;

public class Activator implements BundleActivator {

    public void start(BundleContext context) throws Exception {
//        context.registerService(IGamePluginService.class, new AIPlugin(), null);
        context.registerService(IEntityProcessingService.class, new AIProcessor(), null);
    }

    public void stop(BundleContext context) throws Exception {
        // TODO add deactivation code here
    }

}
