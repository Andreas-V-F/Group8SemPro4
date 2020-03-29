package dk.sdu.mmmi.cbse.osgienemy;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import sdu.mmmi.softwareengineering.osgicommon.services.IEntityProcessingService;
import sdu.mmmi.softwareengineering.osgicommon.services.IGamePluginService;

public class Activator implements BundleActivator {

    public void start(BundleContext context) throws Exception {
        context.registerService(IGamePluginService.class, new EnemyPlugin(), null);
        context.registerService(IEntityProcessingService.class, new EnemyProcessor(), null);
    }

    public void stop(BundleContext context) throws Exception {
        // TODO add deactivation code here
    }

}
