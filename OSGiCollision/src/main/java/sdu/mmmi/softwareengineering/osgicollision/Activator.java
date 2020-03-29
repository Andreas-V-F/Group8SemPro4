/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.mmmi.softwareengineering.osgicollision;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import sdu.mmmi.softwareengineering.osgicommon.services.IPostEntityProcessingService;

/**
 *
 * @author andre
 */
public class Activator implements BundleActivator{


    public void start(BundleContext context) throws Exception {
        context.registerService(IPostEntityProcessingService.class, new Collision(), null);
        
    }

    public void stop(BundleContext context) throws Exception {
        // TODO add deactivation code here
    }

}


